package gof;

import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Solver {

  private Board board;
  private final Queue<Board> queue = new LinkedList<>();
  private Set<List<Integer>> alreadyChecked = new HashSet<>();

  public Solver(Board board) {
    this.board = board;
  }

  public List<Integer> findThePossibleMoves(Board board, int emptyTileIndex) {
    Set<Integer> indexesToSwapWithEmptyTile = new HashSet<>();
    for (int i = 0; i < board.getGameBoard().size(); i++) {
      if (emptyTileIndex % Board.ROWLENGTH == 0) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
        if (emptyTileIndex >= Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
        if (emptyTileIndex < board.getGameBoard().size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
      } else if (emptyTileIndex % Board.ROWLENGTH == Board.ROWLENGTH - 1) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
        if (emptyTileIndex > (Board.ROWLENGTH - 1)) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
        if (emptyTileIndex < board.getGameBoard().size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
      } else {
        if (emptyTileIndex < board.getGameBoard().size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
        if (emptyTileIndex >= Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
      }
    }
    return new ArrayList<>(indexesToSwapWithEmptyTile);
  }

  public Board solve(Board boardToSolve) {
    queue.add(boardToSolve);
    System.out.println(boardToSolve.getGameBoard().toString());
    while (!queue.isEmpty()) {
      System.out.println(queue.toString());
      Board board = queue.remove();
      for (Board b : queue) {
        if (b.isSolved()) {
          System.out.println("Gameboard is solved");
          return board;
        }
      }
      alreadyChecked.add(board.getGameBoard());
      List<Integer> correctMoves = findThePossibleMoves(board, board.findEmptyTile());
      for (int i = correctMoves.size() - 1; i >= 0; i--) {
        Board newBoard = new Board(board);
        move(newBoard, correctMoves.get(i), newBoard.findEmptyTile());
        if (!alreadyChecked.contains(newBoard.getGameBoard())) {
          queue.add(newBoard);
        }
      }
    }
    return null;
  }

  public void move(Board board, int correctMove, int emptyFileIndex) {
    Collections.swap(board.getGameBoard(), correctMove, emptyFileIndex);
  }
}

