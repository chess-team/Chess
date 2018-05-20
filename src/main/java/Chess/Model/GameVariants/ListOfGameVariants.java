package Chess.Model.GameVariants;

import Chess.Model.VariantOfGame;

import java.util.ArrayList;
import java.util.List;

public class ListOfGameVariants {
    public List<String> getNamesOfVariants(){
        ArrayList <String> resultList = new ArrayList<>();
        resultList.add("classic chess");
        resultList.add("upside down chess");
        resultList.add("chess 960");
        resultList.add("transcendental chess");
        resultList.add("double move chess");
        resultList.add("monster chess");
        resultList.add("progressive chess take all");
        resultList.add("kill king chess");
        resultList.add("Dunsany's Chess");
        resultList.add("Weak!");
        resultList.add("Charge of the Light Brigade");
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
            case "double move chess":
                return new DoubleMoveChess();
            case "monster chess":
                return new MonsterChess();
            case "progressive chess take all":
                return new ProgressiveChessTakeAll();
            case "kill king chess":
                return new KillKingChess();
            case "Dunsany's Chess":
                return new DunsanysChess();
            case "Weak!":
                return new Weak();
            case "Charge of the Light Brigade":
                return new ChargeOfLightBrigade();
        }
        return null;
    }
}
