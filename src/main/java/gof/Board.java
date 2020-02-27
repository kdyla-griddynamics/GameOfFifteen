package gof;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

  public Board(String boardSource) throws BoardNotCompleteException {
    setBoard(TilesLoader.load(boardSource));
  }

  public Board(Board board) {
    this.board.addAll(board.getBoard());
    this.path.addAll(board.getPath());
    this.parents.addAll(board.getParents());
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
}
