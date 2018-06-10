package Chess.Model.Moves;


import Chess.Model.Position;

//blocks figure next turn
public class BlockFigureMove extends SpecialMove{
    public BlockFigureMove(Position to) {
        this.to = to;
    }

    @Override
    public String toString(){
        return "block figure at " + to.toString();
    }

    @Override
    public void changeState() {

    }

    @Override
    public boolean isBreakingRules() {
        return false;
    }


}
