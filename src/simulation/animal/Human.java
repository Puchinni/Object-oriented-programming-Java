package simulation.animal;

import simulation.World;
import simulation.Animal;
import simulation.Organism;
import java.awt.*;
public class Human extends Animal{
    private int baseStrength;
    public Human(int x, int y)
    {
        super(x, y);
        this.initiative = 4;
        baseStrength = this.strength = 5;
    }
    public void SetX(int x) {
        this.x = x;
    }
    public int GetX() {
        return x;
    }
    public void SetY(int y) {
        this.y = y;
    }
    public int GetY() {
        return y;
    }
    @Override
    public void Action(World w) {
    }
    @Override
    public void Colission(Organism org)
    {
        if (this.strength < org.GetStrenght()) {
            Died();
        }
    }
    public void activateAbility() {
        if (baseStrength < 10 && baseStrength >= 5) {
            baseStrength += 1;
        }
    }
    public void disactivateAbility() {
        if (baseStrength <= 10 && baseStrength > 5) {
            baseStrength -= 1;
        }
    }
    @Override
    public String Draw()
    {
        return "Human";
    }
    @Override
    public Color getColor() {
        return Color.magenta;
    }
    public int GetStrenght2() {
        return baseStrength;
    }
}
