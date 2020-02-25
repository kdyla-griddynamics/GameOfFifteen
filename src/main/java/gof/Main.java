package gof;

import java.util.List;

public class Main {
  public static void main(String[] args) throws InterruptedException {

    Board board = new Board();
    List<Integer> shuffleBoard = board.shuffleBoard();

    System.out.println(shuffleBoard.toString());
    Solver solver = new Solver();
    System.out.println(board.isSolvable());
    System.out.println(solver.solve(board));
    System.out.println(board.isSolved());
    System.out.println(solver.findEmptyTile(board));
    System.out.println(solver.findThePossibleMoves(board,
        solver.findEmptyTile(board)).toString());


  }
}
