package SnakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class GamePiece {
    private int currentPosition;

    public GamePiece() {
        this.currentPosition = 0;  // Starts at zero.
    }
    public void updatePosition(int newPosition){
        if(newPosition > 100) {
            System.out.println("Didn't update the position.");
            return;
        }
        this.currentPosition = newPosition;
    }
}
