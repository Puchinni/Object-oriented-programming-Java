package simulation.animal;

import simulation.Animal;
import simulation.Organism;
import java.awt.*;
public class Wolf extends Animal {
    public Wolf(int x, int y) {
        super(x, y);
        this.initiative = 5;
        this.strength = 9;
    }
    @Override
    public void Colission(Organism org) {
        if (org instanceof Wolf) {
            Born(org);
        }
        else {
            if (this.strength < org.GetStrenght()) {
                Died();
            }
        }
    }
    @Override
    public String Draw()
    {
        return "Wolf";
    }
    @Override
    public Color getColor() {
        return Color.RED;
    }
}
