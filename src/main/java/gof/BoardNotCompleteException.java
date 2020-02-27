package gof;

public class BoardNotCompleteException extends Exception {

  public BoardNotCompleteException(String errorMessage) {
    super(errorMessage);
  }
}
