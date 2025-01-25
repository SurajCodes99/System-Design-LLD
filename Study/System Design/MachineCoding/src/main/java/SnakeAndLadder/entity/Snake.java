package SnakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Snake extends BoardObject{
    public Snake(int startPoint, int endPoint){
        super(startPoint, endPoint);
    }

    public boolean isValidPosition(){
        return (startPoint > endPoint);
    }

    public String getType(){
        return "Snake";
    }

}
