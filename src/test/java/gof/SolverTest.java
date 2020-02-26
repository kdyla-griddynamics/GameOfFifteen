package gof;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SolverTest {

  Board board = new Board();
  Solver solver = new Solver();

  @ParameterizedTest
  @MethodSource("correctMoves")
  public void checkIfTheMoveRestrictionAreCorrect(int emptyTile, List<Integer> expectedPossibleMoves) {
    //given
    List<Integer> gameBoard = board.getGameBoard();
    //when
    List<Integer> actualPossibleMoves = solver.findThePossibleMoves(board, emptyTile);
    //then
    Assertions.assertEquals(expectedPossibleMoves, actualPossibleMoves, "The moves are not correct");
  }

  static Stream<Arguments> correctMoves() {
    return Stream.of(
        Arguments.of(0, Arrays.asList(1, 4)),
        Arguments.of(1, Arrays.asList(0, 2, 5)),
        Arguments.of(2, Arrays.asList(1, 3, 6)),
        Arguments.of(3, Arrays.asList(2, 7)),
        Arguments.of(4, Arrays.asList(0, 5, 8)),
        Arguments.of(5, Arrays.asList(1, 4, 6, 9)),
        Arguments.of(6, Arrays.asList(2, 5, 7, 10)),
        Arguments.of(7, Arrays.asList(3, 6, 11)),
        Arguments.of(8, Arrays.asList(4, 9, 12)),
        Arguments.of(9, Arrays.asList(5, 8, 10, 13)),
        Arguments.of(10, Arrays.asList(6, 9, 11, 14)),
        Arguments.of(11, Arrays.asList(7, 10, 15)),
        Arguments.of(12, Arrays.asList(8, 13)),
        Arguments.of(13, Arrays.asList(9, 12, 14)),
        Arguments.of(14, Arrays.asList(10, 13, 15)),
        Arguments.of(15, Arrays.asList(11, 14))
    );
  }

}