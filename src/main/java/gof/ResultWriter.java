package gof;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultWriter {

  public static void writeToFile(Board board, Solver solver) {
    File result = new File("src/main/resources/result.txt");
    List<Integer> init = solver.getInitial();
    try (FileWriter writer = new FileWriter(result)) {
      writer.write("Initial configuration: \n");
      writeBoardAsArray(init, writer);
      writer.write("\nNumber of tiles movement: " + board.isSolved() + "\n");
      for (int i = 0; i < board.getPath().size(); i++) {
        writer.write(board.getPath().get(i) + "\n");
        List<Integer> parent = board.getParents().get(i);
        writeBoardAsArray(parent, writer);
        writer.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeBoardAsArray(List<Integer> init, FileWriter writer) throws IOException {
    for (int i = 0; i < init.size(); i += Board.ROWLENGTH) {
      writer.write("[ ");
      for (int j = 0; j < Board.ROWLENGTH; j++) {
        writer.write(init.get(i + j) + " ");
      }
      writer.write("]\n");
    }
  }
}
