package simulation.plant;

import simulation.Plant;
import simulation.World;
import simulation.Organism;
import java.awt.*;
public class Sonchus extends Plant {
    public Sonchus(int x, int y) {
        super(x, y);
        this.strength = 0;
        this.initiative = 0;
    }
    @Override
    public void Action(World w) {
        for (int i = 0; i < 3; i++) {
            super.Action(w);
            if (_plantX != -1 && _plantY != -1 && w.isEmptyCell(_plantX, _plantY)) {
                w.organisms.add(new Sonchus(_plantX, _plantY));
            }
            _plantX = -1;
            _plantY = -1;
        }
    }
    @Override
    public void Colission(Organism org) {
        if (this.strength < org.GetStrenght()) {
            Died();
        }
    }
    @Override
    public String Draw()
    {
        return "Sonchus";
    }
    @Override
    public Color getColor() {
        return Color.GRAY;
    }
}
