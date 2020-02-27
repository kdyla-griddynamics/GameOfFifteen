package gof;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TilesLoader {

  public static List<Integer> load(String fileName) throws BoardNotCompleteException {
    List<Integer> loadedBoard = new ArrayList<>();
    File sourceFile = new File("src/main/resources/boardsNamedByMovesToSolveNumber/" + fileName);

    try (Scanner scanner = new Scanner(sourceFile)) {
      while (scanner.hasNextLine()) {
        int nextTile = Integer.parseInt(scanner.nextLine());
        loadedBoard.add(nextTile);
      }
    } catch (FileNotFoundException e) {
      System.err.println("Board scheme not found");
      e.getMessage();
    }
    if (loadedBoard.size() == 16) {
      return loadedBoard;
    } else {
      throw new BoardNotCompleteException("Board is not loaded completely");
    }
  }
}
