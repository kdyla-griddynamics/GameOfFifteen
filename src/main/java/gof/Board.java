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
  public static final int ROWLENGTH = 3;

  public Board(Board board) {
    Collections.copy(board.getGameBoard(), this.board);
  }

//  public Board getNewBoard(Board oldBoard) {
//    if (this.getGameBoard().equals(oldBoard.getGameBoard())) {
//      return oldBoard;
//    } else {
//      return new Board(oldBoard);
//    }
//  }

  public List<Integer> getGameBoard() {
    if (board.isEmpty()) {
      for (int i = 0; i < 9; i++) {
        board.add(i);
      }
    }
    return board;
  }

  public List<Integer> shuffleBoard() {
    do {
      Collections.shuffle(getGameBoard());
    } while (!isSolvable());
    return getGameBoard();
  }

  public boolean isSolvable() {
    int inversionCount = 0;

    for (int i = 0; i < board.size() - 1; i++) {
      for (int j = 0; j < i; j++) {
        if (board.get(j) > board.get(i)) {
          inversionCount++;
        }
      }
    }
    return inversionCount % 2 == 0;
  }

  public boolean isSolved() {
    if (board.get(board.size() - 1) != 0) {
      return false;
    }
    for (int i = board.size() - 2; i >= 0; i--) {
      if (board.get(i) != i + 1) {
        return false;
      }
    }
    return true;
  }
}
