package gof;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TilesLoader {

  public static List<Integer> load(String fileName) throws PuzzleNotCompleteException {
    List<Integer> loadedPuzzle = new ArrayList<>();
    String resourcePath = "src/main/resources/";
    File sourceFile = new File(resourcePath + fileName);

    try (Scanner scanner = new Scanner(sourceFile)) {
      while (scanner.hasNextLine()) {
        int nextTile = Integer.parseInt(scanner.nextLine());
        loadedPuzzle.add(nextTile);
      }
    } catch (FileNotFoundException e) {
      System.err.println("Puzzle file not found");
      e.getMessage();
    }
    if (loadedPuzzle.size() == 16) {
      return loadedPuzzle;
    } else {
      throw new PuzzleNotCompleteException("Puzzle has too many or too few tiles");
    }
  }
}
