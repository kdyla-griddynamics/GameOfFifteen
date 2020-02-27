package gof;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TilesLoaderTest {

  @Test
  public void checkIfPresentFilesAreLoadedProperly() throws BoardNotCompleteException {
    //given
    List<Integer> loadedBoard = TilesLoader.load("1");
    //when
    boolean isEmpty = loadedBoard.isEmpty();
    //then
    Assertions.assertNotNull(loadedBoard, "The loaded board is empty");
  }

  @Test
  public void checkIfTooShortBoardCausesException() {
    Assertions.assertThrows(BoardNotCompleteException.class, () -> TilesLoader.load("tooshort"));
  }
}
