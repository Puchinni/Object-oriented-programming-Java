package simulation.animal;

import simulation.Animal;
import simulation.Organism;
import java.awt.*;
public class Sheep extends Animal {
    public Sheep(int x, int y)
    {
        super(x, y);
        this.strength = 4;
        this.initiative = 4;
    }
    @Override
    public void Colission(Organism org)
    {
        if (org instanceof Sheep) {
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
        return "Sheep";
    }
    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }

}
