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
//    System.out.println(board.isSolved());
    System.out.println(board.findEmptyTile());
    System.out.println(solver.findThePossibleMoves(board,
        board.findEmptyTile()).toString());


  }
}
