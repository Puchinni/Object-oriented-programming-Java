package simulation.plant;

import simulation.Organism;
import simulation.Plant;
import simulation.World;
import java.awt.*;
public class WolfBerries extends Plant {
    public WolfBerries(int x, int y) {
        super(x, y);
        this.strength = 99;
        this.initiative = 0;
    }
    @Override
    public void Colission(Organism org)
    {
        if (this.strength < org.GetStrenght()) {
            Died();
        }
    }
    @Override
    public void Action(World w)
    {
        super.Action(w);
        if (_plantX != -1 && _plantY != -1 && w.isEmptyCell(_plantX, _plantY)) {
            w.organisms.add(new WolfBerries(_plantX, _plantY));
            w.setOrganismOnBoard(_plantX, _plantY, w.organisms.get(w.organisms.size()-1));
        }
        _plantX = -1;
        _plantY = -1;
    }
    @Override
    public String Draw()
    {
        return "WolfBerries";
    }
    @Override
    public Color getColor() {
        return Color.PINK;
    }
}
