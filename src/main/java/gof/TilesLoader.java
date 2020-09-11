package gof;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TilesLoader {

    static List<Integer> load(String fileName) throws InvalidPuzzleException {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        List<Integer> orderedPuzzle = Arrays.asList(arr);
        List<Integer> loadedPuzzle = new ArrayList<>();
        String resourcePath = "src/main/resources/";
        File sourceFile = new File(resourcePath + fileName);

        try (Scanner scanner = new Scanner(sourceFile)) {
            while (scanner.hasNextLine()) {
                int nextTile = Integer.parseInt(scanner.nextLine());
                loadedPuzzle.add(nextTile);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Puzzle file not found " + e.getMessage());
        }
        if (loadedPuzzle.size() == 16 && loadedPuzzle.containsAll(orderedPuzzle)) {
            return loadedPuzzle;
        } else {
            throw new InvalidPuzzleException("Loaded puzzle is incorrect");
        }
    }
}
