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
  private List<Integer> initial = new ArrayList<>();

  public Solver(Board board) {
    this.board = board;
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
    return new ArrayList<>(indexesToSwapWithEmptyTile);
  }

  public Board solve(Board boardToSolve) {
    initial = boardToSolve.getBoard();
    queue.add(boardToSolve);
    System.out.println(boardToSolve.getBoard().toString());
    while (!queue.isEmpty()) {
      Board board = queue.remove();
      if (board.isSolved() >= 0) {
        System.out.println("Gameboard is solved");
        return board;
      } else if (board.getParents().size() > 12) {
        System.out.println("Gameboard is not solved");
        return board;
      }
      for (Board b : queue) {
        if (b.isSolved() >= 0) {
          System.out.println("Gameboard is solved");
          return b;
        }
      }
      alreadyChecked.add(board.getBoard());
      List<Integer> correctMoves = findThePossibleMoves(board, board.findEmptyTile());
      for (int i = 0; i < correctMoves.size(); i++) {
        Board newBoard = new Board(board);
        move(newBoard, correctMoves.get(i), newBoard.findEmptyTile());
        if (!alreadyChecked.contains(newBoard.getBoard())) {
          queue.add(newBoard);
        }
      }
    }
    return null;
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

