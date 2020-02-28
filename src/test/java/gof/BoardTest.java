package gof;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;

public class BoardTest {

  Board board = new Board();
  Solver solver = new Solver(board);

  @Test
  public void createsReadyToPlayBoard() throws BoardNotCompleteException {
    //given
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    List<Integer> orderedBoard = Arrays.asList(arr);
    //when
    List<Integer> shuffledBoard = TilesLoader.load("1");
    //then
    Assertions.assertNotNull(shuffledBoard, "Board does not exist");
    assertThat("Shuffled board does not contain every tile", shuffledBoard, everyItem(isIn(orderedBoard)));
  }

  @Test
  public void findsThePositionOfEmptyTile() throws BoardNotCompleteException {
    //given
    List<Integer> shuffleBoard = TilesLoader.load("1");
    board.setBoard(shuffleBoard);
    //when
    int emptyTileIndex = board.findEmptyTile();
    //then
    Assertions.assertEquals(0, (int) shuffleBoard.get(emptyTileIndex), "This '" + emptyTileIndex + "' is not the empty field index");
  }

  @Test
  public void checkIfGameBoardIsSolved() throws BoardNotCompleteException {
    //given
    List<Integer> shuffleBoard = TilesLoader.load("1");
    board.setBoard(shuffleBoard);
    //when
    Board solvedBoard = solver.solve(board, 0, 50);
    //then
    Assertions.assertNotEquals(-1, solvedBoard.isSolved(), "The gameboard is not solved");
  }


}