package SnakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dice {
    private int rollNumber;

    public int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }
}
