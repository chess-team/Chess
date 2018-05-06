package Chess.Model.GameVariants;

import Chess.Model.VariantOfGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class listOfGameVariants {
    public List<String> getNamesOfVariants(){
        ArrayList <String> resultList = new ArrayList<>();
        resultList.add("classic chess");
        resultList.add("upside down chess");
        resultList.add("chess 960");
        resultList.add("transcendental chess");
        return resultList;
    }
    public VariantOfGame getInstance(String name){
        switch (name){
            case "classic chess":
                return new ClassicChess();
            case "upside down chess":
                return new UpsideDownChess();
            case "chess 960":
                return new Chess960();
            case "transcendental chess":
                return new TranscendentalChess();
        }
        return null;
    }
}
