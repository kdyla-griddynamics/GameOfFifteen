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

  private List<Integer> initial = new ArrayList<>();
  public Set<List<Integer>> alreadyChecked = new HashSet<>();

  public Solver(Board board) {
    this.initial = board.getBoard();
  }

  public List<Integer> findThePossibleMoves(Board board, int emptyTileIndex) {
    Set<Integer> indexesToSwapWithEmptyTile = new HashSet<>();
    for (int i = 0; i < board.getBoard().size(); i++) {
      if (emptyTileIndex % Board.ROWLENGTH == 0) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
        if (emptyTileIndex >= Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
        if (emptyTileIndex < board.getBoard().size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
      } else if (emptyTileIndex % Board.ROWLENGTH == Board.ROWLENGTH - 1) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
        if (emptyTileIndex > (Board.ROWLENGTH - 1)) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Board.ROWLENGTH);
        }
        if (emptyTileIndex < board.getBoard().size() - Board.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Board.ROWLENGTH);
        }
      } else {
        if (emptyTileIndex < board.getBoard().size() - Board.ROWLENGTH) {
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
    if (board.getParents().size() > 0) {
      indexesToSwapWithEmptyTile.remove(board.getPreviousMove());
    }
    return new ArrayList<>(indexesToSwapWithEmptyTile);
  }

  public Board solve(Board boardToSolve, int cost, int threshold) {
    int estimatedCost = boardToSolve.getManhattanDistance() + cost;
    if (boardToSolve.isSolved() >= 0 || estimatedCost > threshold) {
      boardToSolve.setEstimatedMinimumCost(estimatedCost);
      return boardToSolve;
    }
    int minimumCost = Integer.MAX_VALUE;
    List<Integer> correctMoves = findThePossibleMoves(boardToSolve, boardToSolve.findEmptyTile());
    for (int i = 0; i < correctMoves.size(); i++) {
      Board newBoard = new Board(boardToSolve);
      move(newBoard, correctMoves.get(i), newBoard.findEmptyTile());
      Board solvedBoard = solve(newBoard, cost + 1, threshold);
      if (solvedBoard.isSolved() >= 0) {
        return solvedBoard;
      }
      if (solvedBoard.getEstimatedMinimumCost() < minimumCost) {
        minimumCost = solvedBoard.getEstimatedMinimumCost();
      }
    }
    boardToSolve.setEstimatedMinimumCost(minimumCost);
    return boardToSolve;
  }

  public void move(Board board, int correctMove, int emptyFileIndex) {
    Collections.swap(board.getBoard(), correctMove, emptyFileIndex);
    if (correctMove == emptyFileIndex + 1) {
      board.getPath().add("Right");
      board.getParents().add(board.getBoard());
    } else if (correctMove == emptyFileIndex - 1) {
      board.getPath().add("Left");
      board.getParents().add(board.getBoard());
    } else if (correctMove == emptyFileIndex + Board.ROWLENGTH) {
      board.getPath().add("Down");
      board.getParents().add(board.getBoard());
    } else if (correctMove == emptyFileIndex - Board.ROWLENGTH) {
      board.getPath().add("Up");
      board.getParents().add(board.getBoard());
    }
  }
}

