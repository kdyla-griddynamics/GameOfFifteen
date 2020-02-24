package gof;

import java.util.List;

public class Main {
  public static void main(String[] args) {

    Board board = new Board();
    List<Integer> shuffleBoard = board.shuffleBoard(board.getGameBoard());
    System.out.println(shuffleBoard);
    Solver solver = new Solver();
    System.out.println(solver.findEmptyTile(shuffleBoard));
    System.out.println(solver.findThePossibleMoves(shuffleBoard,
        solver.findEmptyTile(shuffleBoard)).toString());


  }
}
