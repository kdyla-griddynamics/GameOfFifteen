import java.util.List;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertNotNull;

public class BoardTest {

  @Test
  public void createsReadyToPlayBoard() {
    //given
    Board board = new Board();
    //when
    List<Integer> gameBoard = board.shuffleBoard(board.getGameBoard());
    //then
    assertNotNull("Board does not exist", gameBoard);
    assertThat(gameBoard, everyItem(isIn(board.getGameBoard())));
  }

}