package gof;

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
  public void createsReadyToPlayBoard() {
    //given
    List<Integer> orderedBoard = board.getGameBoard();
    //when
    List<Integer> shuffledBoard = board.shuffleBoard();
    //then
    Assertions.assertNotNull(shuffledBoard, "Board does not exist");
    assertThat("Shuffled board does not contain every tile", shuffledBoard, everyItem(isIn(orderedBoard)));
  }

  @Test
  public void findsThePositionOfEmptyTile() {
    //given
    List<Integer> shuffleBoard = board.shuffleBoard();
    //when
    int emptyTileIndex = board.findEmptyTile();
    //then
    Assertions.assertEquals(0, (int) shuffleBoard.get(emptyTileIndex), "This '" + emptyTileIndex + "' is not the empty field index");
  }

  @Test
  public void checkIfTheGameboardIsSolvable() {
    //given
    List<Integer> shuffleBoard = board.shuffleBoard();
    //when
    boolean isSolvable = board.isSolvable();
    //then
    Assertions.assertTrue(isSolvable, "This board is nor solvable");
  }

  @Test
  public void checkIfGameBoardIsSolved() {
    //given
    board.shuffleBoard();
    //when
    Board solvedBoard = solver.solve(board);
    //then
    Assertions.assertTrue(solvedBoard.isSolved(), "The gameboard is not solved");
  }


}