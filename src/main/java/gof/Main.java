package gof;


public class Main {
  public static void main(String[] args) throws BoardNotCompleteException {

    Board board = new Board("6");
    Solver solver = new Solver();
    Board solvedBoard = solver.solve(board);
    System.out.println(solvedBoard.getPath().toString());
    System.out.println(solvedBoard.getParents().toString());
    ResultWriter.writeToFile(solvedBoard, solver);
  }
}
