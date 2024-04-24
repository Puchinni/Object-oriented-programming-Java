package simulation;

import java.util.Random;
public abstract class Plant extends Organism {
    protected double probability = 7;
    protected int _plantX = -1, _plantY = -1;
    protected World world = null;
    protected int GetRandomX(World w)
    {
        while (true)
        {
            Random rand = new Random();
            int x = rand.nextInt(3) - 1;
            int result_x = this.x + x;
            if (result_x >= 0 && result_x < w.GetCol()) {
                return result_x;
            }
        }
    }
    protected int GetRandomY(World w)
    {
        while (true)
        {
            Random rand = new Random();
            int y = rand.nextInt(3) - 1;
            int result_y = this.y + y;
            if (result_y >= 0 && result_y < w.GetRow()) {
                return result_y;
            }
        }
    }
    public Plant(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public void Action(World w)
    {
        this.world = w;
        Random rand = new Random();
        if (rand.nextInt((int) probability) == 0) {
            _plantX = GetRandomX(w);
            _plantY= GetRandomY(w);
            probability += 0.5;
        }
    }
}
