package simulation;

import java.awt.*;
public abstract class Organism {
    protected int strength;
    protected int initiative, age;
    protected Color color;
    protected int x;
    protected int y;
    protected boolean isDied;
    public Organism()
    {
        isDied = false;
        x = -1;
        y = -1;
    }
    public abstract void Action(World w);
    public abstract void Colission(Organism org);
    public abstract String Draw();
    public Color getColor() {
        return Color.WHITE;
    }

    public int GetStrenght()
    {
        return strength;
    }
    public void SetStrength(int strenght)
    {
        this.strength = strenght;
    }

    public int GetInitiative()
    {
        return initiative;
    }
    public int GetAge()
    {
        return age;
    }
    public void SetInitiative(int initiative)
    {
        this.initiative = initiative;
    }

    public int GetX()
    {
        return x;
    }

    public void SetX(int x)
    {
        this.x = x;
    }

    public int GetY()
    {
        return y;
    }

    public void SetY(int y)
    {
        this.y = y;
    }

    public void SetXY(int x, int y)
    {
        SetX(x);
        SetY(y);
    }

    public void Died()
    {
        this.isDied = true;
    }

    public boolean IsDied()
    {
        return isDied;
    }
}
