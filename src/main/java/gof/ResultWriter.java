package gof;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultWriter {

  public static final File result = new File("src/main/resources/result.txt");

  public static void writeToFile(Puzzle puzzle, List<Integer> initial) {
    try (FileWriter writer = new FileWriter(result)) {
      writer.write("Initial configuration: \n");
      writeBoardAsArray(initial, writer);
      writer.write("\nNumber of tiles movement: " + puzzle.isSolved() + "\n");
      for (int i = 0; i < puzzle.getPath().size(); i++) {
        writer.write(puzzle.getPath().get(i) + "\n");
        List<Integer> parent = puzzle.getPreviousStates().get(i);
        writeBoardAsArray(parent, writer);
        writer.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeBoardAsArray(List<Integer> init, FileWriter writer) throws IOException {
    for (int i = 0; i < init.size(); i += Puzzle.ROWLENGTH) {
      writer.write("[ ");
      for (int j = 0; j < Puzzle.ROWLENGTH; j++) {
        writer.write(init.get(i + j) + " ");
      }
      writer.write("]\n");
    }
  }
}
