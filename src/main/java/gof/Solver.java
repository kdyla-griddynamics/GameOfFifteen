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
  private int movesCount = 0;

  public Solver(Board board) {
    this.board = board;
  }

  public boolean isSolvable(List<Integer> gameBoard) {
    int inversionCount = 0;

    for (int i = 0; i < gameBoard.size() - 1; i++) {
      for (int j = 0; j < i; j++) {
        if (gameBoard.get(j) > gameBoard.get(i)) {
          inversionCount++;
        }
      }
    }
    return inversionCount % 2 == 0;
  }

  public int findEmptyTile(List<Integer> gameBoard) {
    for (int i = 0; i < gameBoard.size(); i++) {
      if (gameBoard.get(i) == 0) {
        return i;
      }
    }
    return -1;
  }

  public List<Integer> findThePossibleMoves(List<Integer> gameBoard, int emptyTileIndex) {
    Set<Integer> indexesToSwapWithEmptyTile = new HashSet<>();
    for (int i = 0; i < gameBoard.size(); i++) {
      if (emptyTileIndex % Board.ROWLENGTH == 0) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
        if (emptyTileIndex >= Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
        if (emptyTileIndex < gameBoard.size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
      } else if (emptyTileIndex % Board.ROWLENGTH == Board.ROWLENGTH - 1) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
        if (emptyTileIndex > (Board.ROWLENGTH - 1)) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
        if (emptyTileIndex < gameBoard.size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
      } else {
        if (emptyTileIndex < gameBoard.size() - Board.ROWLENGTH) {
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

  public List<Integer> solve(List<Integer> gameBoard) {
    if (isSolved(gameBoard)) {
      System.out.println("gameboard is solved");
      return gameBoard;
    }
    return gameBoard;
  }

  public boolean isSolved(List<Integer> gameBoard) {
    if (gameBoard.get(gameBoard.size() - 1) != 0) {
      return false;
    }
    for (int i = gameBoard.size() - 2; i >= 0; i--) {
      if (gameBoard.get(i) != i + 1) {
        return false;
      }
    }
    return true;
  }

  public List<Integer> move(List<Integer> gameBoard, int correctMove, int emptyFileIndex) {
    Collections.swap(gameBoard, correctMove, emptyFileIndex);
    return gameBoard;
  }
}
