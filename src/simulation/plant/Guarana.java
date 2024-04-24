package simulation.plant;

import simulation.Plant;
import simulation.Organism;
import simulation.World;
import java.awt.*;
public class Guarana extends Plant{
        public Guarana(int x, int y) {
            super(x, y);
            this.initiative = 0;
            this.strength = 0;
        }
        @Override
        public void Action(World w) {
            super.Action(w);
            if (_plantX != -1 && _plantY != -1 && w.isEmptyCell(_plantX, _plantY)) {
                w.organisms.add(new Guarana(_plantX, _plantY));
                w.setOrganismOnBoard(_plantX, _plantY, w.organisms.get(w.organisms.size() - 1));
            }
            _plantX = -1;
            _plantY = -1;
        }
        @Override
        public void Colission(Organism org) {
            if (this.strength < org.GetStrenght()) {
                org.SetStrength(org.GetStrenght() + 3);
                Died();
            }
        }
    @Override
    public String Draw()
    {
        return "Guarana";
    }
    @Override
    public Color getColor() {
        return Color.CYAN;
    }
}
