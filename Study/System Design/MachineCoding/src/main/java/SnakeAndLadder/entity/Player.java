package SnakeAndLadder.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String playerName;
    private GamePiece gamePiece;
}
