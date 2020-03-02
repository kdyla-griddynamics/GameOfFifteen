package gof;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Puzzle {

  public static final int ROWLENGTH = 4;
  private List<Integer> currentState = new ArrayList<>();
  private List<String> path = new ArrayList<>();
  private List<List<Integer>> previousStates = new ArrayList<>();
  private int manhattanDistance = 0;
  private int estimatedMinimumCost = Integer.MAX_VALUE;
  private int previousMove = ROWLENGTH * ROWLENGTH;

  public Puzzle(String boardSource) throws PuzzleNotCompleteException {
    setCurrentState(TilesLoader.load(boardSource));
    this.manhattanDistance = countManhattanDistance();
  }

  public Puzzle(Puzzle puzzle) {
    this.currentState.addAll(puzzle.getCurrentState());
    this.path.addAll(puzzle.getPath());
    this.previousStates.addAll(puzzle.getPreviousStates());
    this.previousMove = puzzle.findEmptyTile();
    this.manhattanDistance = countManhattanDistance();
  }

  public int findEmptyTile() {
    for (int i = 0; i < currentState.size(); i++) {
      if (currentState.get(i) == 0) {
        return i;
      }
    }
    return -1;
  }

  public int isSolved() {
    if (currentState.get(currentState.size() - 1) != 0) {
      return -1;
    }
    for (int i = currentState.size() - 2; i >= 0; i--) {
      if (currentState.get(i) != i + 1) {
        return -1;
      }
    }
    return previousStates.size();
  }

  public int countManhattanDistance() {
    int sum = 0;
    for (int i = 0; i < currentState.size(); i++) {
      if (currentState.get(i) != 0) {
        int cost = (Math.abs(currentState.get(i) - i - 1) / ROWLENGTH)
            + (Math.abs(currentState.get(i) - i - 1) % ROWLENGTH);
        sum += cost;
      }
    }
    return sum;
  }
}
