package SnakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BoardObject {
    int startPoint;
    int endPoint;

    public boolean isPlayerOnObject(int currentPosition) {
        return currentPosition == startPoint;
    }
    public abstract String getType();

    public abstract boolean isValidObject();
}
