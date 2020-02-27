package gof;


public class Main {
  public static void main(String[] args) {

    try {
      Board board = new Board("10");
      Solver solver = new Solver();
      Board solvedBoard = solver.solve(board);
      ResultWriter.writeToFile(solvedBoard, solver);
    } catch (BoardNotCompleteException e) {
      System.err.println(e.getMessage());
    }
  }
}
