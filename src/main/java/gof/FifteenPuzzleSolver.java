package gof;

/**
 * Main class that is used to instantiate the Solver class and invoke 'solveGivenPuzzle'
 * method which accepts file name of the specific puzzle board in string format.
 * Puzzle boards are located in resource folder.
 */
public class FifteenPuzzleSolver {
  public static void main(String[] args) {
    Solver solver = new Solver();
    solver.solveGivenPuzzle("28");
  }
}
