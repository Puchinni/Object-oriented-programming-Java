package simulation.plant;

import simulation.Organism;
import simulation.Plant;
import simulation.World;

import java.awt.*;

public class Hogweed extends Plant{
    private int[] step_y = {-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] step_x = {-1, 0, 1, -1, 1, -1, 0, 1};
    public Hogweed(int x, int y) {
        super(x, y);
        this.strength = 99;
        this.initiative = 0;
    }
    @Override
    public void Action(World w) {
        super.Action(w);
        if (_plantX != -1 && _plantY != -1 && w.isEmptyCell(_plantX, _plantY)) {
            w.organisms.add(new Hogweed(_plantX, _plantY));
            w.setOrganismOnBoard(_plantX, _plantY, w.organisms.get(w.organisms.size()-1));
        }
        _plantX = -1;
        _plantY = -1;
        for (int i = 0; i < 8; i++) {
            int x_ = this.x + step_x[i];
            int y_ = this.y + step_y[i];
            if (w.isCellExists(x_, y_) && !w.isEmptyCell(x_, y_)) {
                Organism other = w.GetOrganismByPosition(x_, y_);
                if (!(other instanceof Hogweed)) {
                    other.Died();
                    return;
                }
            }
        }
    }
    @Override
    public void Colission(Organism org)
    {
        org.Died();
    }
    @Override
    public String Draw()
    {
        return "Hogweed";
    }
    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }
}
