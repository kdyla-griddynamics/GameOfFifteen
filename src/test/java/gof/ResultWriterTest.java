package gof;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import org.junit.jupiter.api.Test;


public class ResultWriterTest {

  Puzzle puzzle = new Puzzle();
  Solver solver = new Solver();

  @Test
  public void checkIfResultFileIsCreated() throws InvalidPuzzleException {
    //given
    String puzzleSourceFileName = "1";
    List<Integer> shuffledPuzzle = TilesLoader.load(puzzleSourceFileName);
    puzzle.setCurrentState(shuffledPuzzle);
    //when
    ResultWriter.writeToFile(puzzle, shuffledPuzzle);
    //then
    assertThat(ResultWriter.result.exists(), equalTo(Boolean.TRUE));
  }

}
