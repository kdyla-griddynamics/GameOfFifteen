import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Board {

    public List<Integer> getGameBoard(){
        List<Integer> gameBoard = new ArrayList<>();
        for (int i = 0; i <16 ; i++) {
            gameBoard.add(i);
        }
        return gameBoard;
    }

    public List<Integer> shuffleBoard(List<Integer> board){
        System.out.println(board.toString());
        Collections.shuffle(board);
        System.out.println(board.toString());
        return board;
    }
}
