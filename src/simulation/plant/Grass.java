package simulation.plant;
import simulation.Organism;
import simulation.Plant;
import simulation.World;
import java.awt.*;
public class Grass extends Plant {
    public Grass(int x, int y) {
        super(x, y);
        this.strength = 0;
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
            w.organisms.add(new Grass(_plantX, _plantY));
            w.setOrganismOnBoard(_plantX, _plantY, w.organisms.get(w.organisms.size()-1));
        }
        _plantX = -1;
        _plantY = -1;
    }
    @Override
    public String Draw()
    {
        return "Grass";
    }
    @Override
    public Color getColor() {
        return Color.green;
    }
}
