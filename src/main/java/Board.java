import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Board {

  private List<Integer> board = new ArrayList<>();

  public List<Integer> getGameBoard() {
    if (board.isEmpty()) {
      for (int i = 0; i < 16; i++) {
        board.add(i);
      }
    }
    return board;
  }

  public List<Integer> shuffleBoard(List<Integer> board) {
    System.out.println(board.toString());
    Collections.shuffle(board);
    System.out.println(board.toString());
    return board;
  }
}
