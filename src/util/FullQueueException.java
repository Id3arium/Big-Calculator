package cisc187.util;


/**
 * Thrown by the enqueue method of a
 * {@link Queue} when the Queue is full.
 *
 */
public class FullQueueException extends RuntimeException {

  public FullQueueException(String message) {
    super(message);
  }

  public FullQueueException() {
    super("Error: queue is full - nothing more can be added.");
  }
}
