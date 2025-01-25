package SnakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ladder extends BoardObject{
    public Ladder(int startPoint, int endPoint){
        super(startPoint, endPoint);
    }

    public boolean isValidPosition(){
        return (startPoint < endPoint);
    }

    public String getType(){
        return "Ladder";
    }
}
