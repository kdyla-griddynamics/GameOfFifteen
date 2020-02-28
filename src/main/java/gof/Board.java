package gof;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {

  public static final int ROWLENGTH = 4;
  private List<Integer> board = new ArrayList<>();
  private List<String> path = new ArrayList<>();
  private List<List<Integer>> parents = new ArrayList<>();
  private int manhattanDistance = 0;
  private int estimatedMinimumCost = Integer.MAX_VALUE;
  private int previousMove = ROWLENGTH * ROWLENGTH;

  public Board(String boardSource) throws BoardNotCompleteException {
    setBoard(TilesLoader.load(boardSource));
    this.manhattanDistance = countManhattanDistance();
  }

  public Board(Board board) {
    this.board.addAll(board.getBoard());
    this.path.addAll(board.getPath());
    this.parents.addAll(board.getParents());
    this.previousMove = board.findEmptyTile();
    this.manhattanDistance = countManhattanDistance();
  }

  public int findEmptyTile() {
    for (int i = 0; i < board.size(); i++) {
      if (board.get(i) == 0) {
        return i;
      }
    }
    return -1;
  }

  public int isSolved() {
    if (board.get(board.size() - 1) != 0) {
      return -1;
    }
    for (int i = board.size() - 2; i >= 0; i--) {
      if (board.get(i) != i + 1) {
        return -1;
      }
    }
    return parents.size();
  }

  public int countManhattanDistance() {
    int sum = 0;
    for (int i = 0; i < board.size(); i++) {
      if (board.get(i) != 0) {
        int cost = (Math.abs(board.get(i) - i - 1) / ROWLENGTH)
            + (Math.abs(board.get(i) - i - 1) % ROWLENGTH);
        sum += cost;
      }
    }
    return sum;
  }
}
