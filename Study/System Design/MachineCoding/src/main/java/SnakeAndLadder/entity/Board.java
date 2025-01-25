package SnakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Board {
    private List<Snake> snake;
    private List<Ladder> ladders;
}
