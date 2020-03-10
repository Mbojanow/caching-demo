package pl.sdacademy.caching.exception;

public class SdaException extends RuntimeException {
  public SdaException(final String message) {
    super(message);
  }

  public SdaException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
