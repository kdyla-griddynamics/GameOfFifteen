package gof;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TilesLoaderTest {

   @Test
   public void checkIfPresentFilesAreLoadedProperly() throws InvalidPuzzleException {
      //given
      List<Integer> loadedPuzzle = TilesLoader.load("1");
      //when
      boolean isEmpty = loadedPuzzle.isEmpty();
      //then
      assertThat("The puzzle has not loaded properly", isEmpty, equalTo(Boolean.FALSE));
   }

   @Test
   public void checkIfTooShortOrTooLongBoardCausesException() {
      Assertions.assertThrows(InvalidPuzzleException.class, () -> TilesLoader.load("tooshort"));
   }
}
