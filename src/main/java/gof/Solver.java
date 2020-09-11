package gof;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Solver {

  private List<Integer> initial = new ArrayList<>();

  List<Integer> findThePossibleMoves(Puzzle puzzle, int emptyTileIndex) {
    Set<Integer> indexesToSwapWithEmptyTile = new HashSet<>();
    for (int i = 0; i < puzzle.getCurrentState().size(); i++) {
      if (emptyTileIndex % Puzzle.ROWLENGTH == 0) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
        if (emptyTileIndex >= Puzzle.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Puzzle.ROWLENGTH);
        }
        if (emptyTileIndex < puzzle.getCurrentState().size() - Puzzle.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Puzzle.ROWLENGTH);
        }
      } else if (emptyTileIndex % Puzzle.ROWLENGTH == Puzzle.ROWLENGTH - 1) {
        indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
        if (emptyTileIndex > (Puzzle.ROWLENGTH - 1)) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Puzzle.ROWLENGTH);
        }
        if (emptyTileIndex < puzzle.getCurrentState().size() - Puzzle.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Puzzle.ROWLENGTH);
        }
      } else {
        if (emptyTileIndex < puzzle.getCurrentState().size() - Puzzle.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex + Puzzle.ROWLENGTH);
        }
        if (emptyTileIndex >= Puzzle.ROWLENGTH) {
          indexesToSwapWithEmptyTile.add(emptyTileIndex + 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex - 1);
          indexesToSwapWithEmptyTile.add(emptyTileIndex - Puzzle.ROWLENGTH);
        }
      }
    }
    if (puzzle.getPreviousMove() < puzzle.getCurrentState().size()) {
      indexesToSwapWithEmptyTile.remove(puzzle.getPreviousMove());
    }
    return new ArrayList<>(indexesToSwapWithEmptyTile);
  }

  Puzzle solveGivenPuzzle(String puzzleSourceFileName) {
    try {
      Puzzle puzzle = new Puzzle(puzzleSourceFileName);
      initial = puzzle.getCurrentState();
      if (puzzle.isSolvable()) {
        int threshold = puzzle.getManhattanDistance();
        System.out.println("Puzzle's manhattan distance: " + threshold);
        System.out.println("Searching...");
        while (puzzle.isSolved() < 0) {
          puzzle = searchWithIda(puzzle, 0, threshold);
          if (puzzle.isSolved() < 0) {
            threshold = puzzle.getEstimatedMinimumCost();
          }
        }
        System.out.println("Puzzle is solved.");
      } else {
        System.out.println("Puzzle is not solvable");
      }
      ResultWriter.writeToFile(puzzle, initial);
      return puzzle;
    } catch (InvalidPuzzleException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  Puzzle searchWithIda(Puzzle puzzleToSolve, int cost, int threshold) {
    int estimatedCost = puzzleToSolve.getManhattanDistance() + cost;
    if (puzzleToSolve.isSolved() >= 0 || estimatedCost > threshold) {
      puzzleToSolve.setEstimatedMinimumCost(estimatedCost);
      return puzzleToSolve;
    }
    int minimumCost = Integer.MAX_VALUE;
    List<Integer> correctMoves = findThePossibleMoves(puzzleToSolve, puzzleToSolve.findEmptyTile());
    for (Integer correctMove : correctMoves) {
      Puzzle newPuzzle = new Puzzle(puzzleToSolve);
      move(newPuzzle, correctMove, newPuzzle.findEmptyTile());
      Puzzle solvedPuzzle = searchWithIda(newPuzzle, cost + 1, threshold);
      if (solvedPuzzle.isSolved() >= 0) {
        return solvedPuzzle;
      }
      if (solvedPuzzle.getEstimatedMinimumCost() < minimumCost) {
        minimumCost = solvedPuzzle.getEstimatedMinimumCost();
      }
    }
    puzzleToSolve.setEstimatedMinimumCost(minimumCost);
    return puzzleToSolve;
  }

  void move(Puzzle puzzle, int correctMove, int emptyFileIndex) {
    Collections.swap(puzzle.getCurrentState(), correctMove, emptyFileIndex);
    if (correctMove == emptyFileIndex + 1) {
      puzzle.getPath().add("Right");
      puzzle.getPreviousStates().add(puzzle.getCurrentState());
    } else if (correctMove == emptyFileIndex - 1) {
      puzzle.getPath().add("Left");
      puzzle.getPreviousStates().add(puzzle.getCurrentState());
    } else if (correctMove == emptyFileIndex + Puzzle.ROWLENGTH) {
      puzzle.getPath().add("Down");
      puzzle.getPreviousStates().add(puzzle.getCurrentState());
    } else if (correctMove == emptyFileIndex - Puzzle.ROWLENGTH) {
      puzzle.getPath().add("Up");
      puzzle.getPreviousStates().add(puzzle.getCurrentState());
    }
  }
}

