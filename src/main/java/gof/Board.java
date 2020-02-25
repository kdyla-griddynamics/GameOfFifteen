package gof;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    this.board.addAll(board.getGameBoard());
  }

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
  public int findEmptyTile() {
    for (int i = 0; i < board.size(); i++) {
      if (board.get(i) == 0) {
        return i;
      }
    }
    return -1;
  }

  public boolean isSolvable() {
    int inversionCount = 0;

    for (int i = 0; i < board.size() - 1; i++) {
      for (int j = i+1; j < board.size(); j++) {
        if ((board.get(j) < board.get(i)) && board.get(j)!=0) {
          inversionCount++;
        }
      }
    }
    System.out.println(inversionCount);
    int emptyTile = findEmptyTile();
    if(inversionCount > 0 && inversionCount < 4 && emptyTile>=board.size()-ROWLENGTH) {
      return inversionCount % 2 == 0;
    } else return false;
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
