package simulation.animal;

import simulation.Animal;
import simulation.Organism;
import simulation.World;
import java.awt.*;
import java.util.Random;
public class Antelope extends Animal{
    public Antelope(int x, int y)
    {
        super(x, y);
        this.initiative = 4;
        this.strength = 4;
    }
    @Override
    public void Action(World w)
    {
        super.Action(w);
        super.Action(w);
    }
    @Override
    public void Colission(Organism org)
    {
        if (org instanceof Antelope) {
            Born(org);
        }
        if (new Random().nextInt(100) > 50) {
            Action(world);
            Action(world);
            return;
        }
        if (this.strength < org.GetStrenght()) {
        Died();
    }
    }
    @Override
    public String Draw()
    {
        return "Antelope";
    }
    @Override
    public Color getColor() {
        return Color.BLACK;
    }


}
