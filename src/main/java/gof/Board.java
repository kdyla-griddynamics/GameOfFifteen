package gof;

import java.util.ArrayList;
import java.util.Collections;
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

  private List<Integer> board = new ArrayList<>();
  public static final int ROWLENGTH = 4;

  public List<Integer> getGameBoard() {
    if (board.isEmpty()) {
      for (int i = 0; i < 16; i++) {
        board.add(i);
      }
    }
    return board;
  }

  public List<Integer> shuffleBoard(List<Integer> board) {
    Collections.shuffle(board);
    return board;
  }
}
