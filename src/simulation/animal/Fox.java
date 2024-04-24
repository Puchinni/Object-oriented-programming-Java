package simulation.animal;

import simulation.Animal;
import simulation.Organism;
import simulation.World;
import java.awt.*;
public class Fox extends Animal {
    private int[] step_y = {-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] step_x = {-1, 0, 1, -1, 1, -1, 0, 1};
    public Fox(int x, int y) {
        super(x, y);
        this.initiative = 7;
        this.strength = 3;
    }
    @Override
    public void Action(World w) {
        int stepCount = 0;
        for (int i = 0; i < 8; i++) {
            if (!w.isCellExists(this.x + step_x[i], this.y + step_y[i])) {
                stepCount++;
            }
        }
        while (stepCount < 8) {
            int x_ = GetRandomX(w);
            int y_ = GetRandomY(w);
            if (w.isCellExists(x_, y_)) {
                if (w.isEmptyCell(x_, y_)) {
                    this.x = x_;
                    this.y = y_;
                    return;
                } else {
                    Organism org = w.GetOrganismByPosition(x_, y_);
                    if (org.GetStrenght() >= this.GetStrenght()) {
                        return;
                    }
                    org.Colission(this);
                    return;
                }
            }
        }
    }
    @Override
    public void Colission(Organism org) {
        if (org instanceof Fox) {
            Born(org);
        } else {
            if (this.strength < org.GetStrenght()) {
                Died();
            }
        }
    }
    @Override
    public String Draw()
    {
        return "Fox";
    }
    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
