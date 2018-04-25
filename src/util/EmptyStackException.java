package cisc187.util;


/**
 * Thrown by the {@link Stack#peek() peek} method of a
 * {@link Stack} when there is nothing in the Stack to observe.
 *
 */
public
class EmptyStackException extends RuntimeException {
  private static final long serialVersionUID = 6778635250639411880L;

  /**
   * Constructs a {@code EmptyStackException} with {@code null}
   * as its error message string.
   */
  public EmptyStackException() {
    super();
  }

  /**
   * Constructs a  {@code EmptyStackException}, saving a reference
   * to the error message string {@code s} for later retrieval by the
   * <tt>getMessage</tt> method.
   *
   * @param   s   the message associated with the exception.
   */
  public EmptyStackException(String s) {
    super(s);
  }
}
