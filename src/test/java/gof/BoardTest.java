package gof;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;

public class BoardTest {

  Board board = new Board();

  @Test
  public void createsReadyToPlayBoard() {
    //given
    List<Integer> orderedBoard = board.getGameBoard();
    //when
    List<Integer> shuffledBoard = board.shuffleBoard(board.getGameBoard());
    //then
    Assertions.assertNotNull(shuffledBoard, "gof.Board does not exist");
    assertThat("Shuffled board does not contain every tile", shuffledBoard, everyItem(isIn(orderedBoard)));
  }


}