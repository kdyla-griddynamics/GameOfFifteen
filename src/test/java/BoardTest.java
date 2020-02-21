import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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