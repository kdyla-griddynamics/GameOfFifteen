package gof;

public class PuzzleNotCompleteException extends Exception {

  public PuzzleNotCompleteException(String errorMessage) {
    super(errorMessage);
  }
}
