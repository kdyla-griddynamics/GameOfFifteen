package gof;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PuzzleTest {

   Puzzle puzzle = new Puzzle();
   Solver solver = new Solver();

   @Test
   public void createsReadyToPlayPuzzle() throws InvalidPuzzleException {
      //given
      Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
      List<Integer> orderedPuzzle = Arrays.asList(arr);
      //when
      List<Integer> shuffledPuzzle = TilesLoader.load("1");
      //then
      Assertions.assertNotNull(shuffledPuzzle, "Puzzle does not exist");
      assertThat("Shuffled puzzle does not contain every tile", shuffledPuzzle,
           everyItem(isIn(orderedPuzzle)));
   }

   @Test
   public void findsThePositionOfEmptyTile() throws InvalidPuzzleException {
      //given
      List<Integer> shuffledPuzzle = TilesLoader.load("1");
      puzzle.setCurrentState(shuffledPuzzle);
      //when
      int emptyTileIndex = puzzle.findEmptyTile();
      //then
      Assertions.assertEquals(0, (int) shuffledPuzzle.get(emptyTileIndex),
           "This '" + emptyTileIndex + "' is not the empty field index");
   }

   @Test
   public void checkIfPuzzleIsSolved() {
      //given
      Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
      List<Integer> orderedPuzzle = Arrays.asList(arr);
      //when
      Puzzle solvedPuzzle = solver.solveGivenPuzzle("1");
      List<Integer> tilesOfSolvedPuzzle = solvedPuzzle.getCurrentState();
      //then
      assertThat("The puzzle is not solved", tilesOfSolvedPuzzle, equalTo(orderedPuzzle));
   }


}