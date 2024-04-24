package simulation.animal;

import simulation.Animal;
import simulation.Organism;
import simulation.World;
import java.awt.*;
import java.util.Random;
public class Turtle extends Animal {
    public Turtle(int x, int y) {
        super(x, y);
        this.initiative = 1;
        this.strength = 2;
    }
    @Override
    public void Action(World w) {
        if (new Random().nextInt(100) > 75) {
            super.Action(w);
        }
    }
    @Override
    public void Colission(Organism org) {
        if (org instanceof Turtle) {
            Born(org);
        } else {
            if (5 > org.GetStrenght()) {
            } else {
                if (org.GetStrenght() >= 5 && (this.strength < org.GetStrenght())) {
                    Died();
                }
            }
        }
    }
    @Override
    public String Draw()
    {
        return "Turtle";
    }
    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
}
