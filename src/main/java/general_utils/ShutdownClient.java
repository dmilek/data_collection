package general_utils;

import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ShutdownClient {
    private static final Logger logger = LogManager.getLogger(ShutdownClient.class);
    private ShutdownClient() {
    }

    // Properly shutdown OkHttpClient's ExecutorService and connection pool
    protected static void shutdownClient(OkHttpClient client) {
        // Using try-with-resources to ensure proper shutdown of ExecutorService
        try (ExecutorService executorService = client.dispatcher().executorService()) {
            executorService.shutdown(); // Disable new tasks from being submitted

            // Wait for existing tasks to terminate
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                logger.warn("Executor did not terminate in the specified time.");
                executorService.shutdownNow(); // Cancel currently executing tasks

                // Wait for tasks to respond to being cancelled
                if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                    logger.error("Executor service did not terminate.");
                }
            }
        } catch (InterruptedException e) {
            logger.error("Executor termination interrupted.", e);
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }

        // Evict all connections from the pool
        client.connectionPool().evictAll();
        logger.info("OkHttpClient has been properly shut down.");
    }
}