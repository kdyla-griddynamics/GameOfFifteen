package gof;


public class Main {
  public static void main(String[] args) {

    findSolutionAndWriteResults("38k");
  }

  public static void findSolutionAndWriteResults(String boardSourceFileName) {
    try {
      Board board = new Board(boardSourceFileName);
      Solver solver = new Solver(board);
      int threshold = board.getManhattanDistance();
      System.out.println("Board's manhattan distance: " + threshold);
      while (board.isSolved() < 0) {
        board = solver.solve(board, 0, threshold);
        if (board.isSolved() < 0) {
          threshold = board.getEstimatedMinimumCost();
        }
      }
      ResultWriter.writeToFile(board, solver);
    } catch (BoardNotCompleteException e) {
      System.err.println(e.getMessage());
    }
  }
}
