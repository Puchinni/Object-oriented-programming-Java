package simulation;
import simulation.animal.*;

import java.util.Random;
public abstract class Animal extends Organism {
    protected double probability = 8;
    private int[] step_y = {-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] step_x = {-1, 0, 1, -1, 1, -1, 0, 1};
    protected int _animalX = -1, _animalY = -1;
    protected World world = null;
    protected int GetRandomX(World w) {
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
    protected int GetRandomY(World w) {
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
    public Animal(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public void Action(World w)
    {
        this.world = w;
        x = GetRandomX(w);
        y = GetRandomY(w);
    }
    public void SetWorld(World w)
    {
        world = w;
    }

        protected void Born(Organism org) {
            int x_ = -1, y_ = -1 ;
            for (int i = 0; i < 8; i++)
            {
                x_ = this.x + step_x[i];
                y_ = this.y + step_y[i];
                if (world.isCellExists(x_, y_) && world.isEmptyCell(x_, y_)) {
                    Animal newAnimal = null;
                    if (this instanceof Antelope) {
                        newAnimal = new Antelope(x_, y_);
                    } else if (this instanceof Fox) {
                        newAnimal = new Fox(x_, y_);
                    } else if (this instanceof Sheep) {
                        newAnimal = new Sheep(x_, y_);
                    } else if (this instanceof Turtle) {
                        newAnimal = new Turtle(x_, y_);
                    } else if (this instanceof Wolf) {
                        newAnimal = new Wolf(x_, y_);
                    } else {
                        throw new IllegalStateException("Unknown animal type");
                    }
                    newAnimal.SetWorld(world);
                    world.organisms.add(newAnimal);
                    return;
                }
            }
            for (int i = 0; i < 8; i++)
            {
                x_ = org.GetX() + step_x[i];
                y_ = org.GetY() + step_y[i];
                if (world.isCellExists(x_, y_) && world.isEmptyCell(x_, y_)) {
                    Animal newAnimal = null;
                    if (org instanceof Antelope) {
                        newAnimal = new Antelope(x_, y_);
                    } else if (org instanceof Fox) {
                        newAnimal = new Fox(x_, y_);
                    } else if (org instanceof Sheep) {
                        newAnimal = new Sheep(x_, y_);
                    } else if (org instanceof Turtle) {
                        newAnimal = new Turtle(x_, y_);
                    } else if (org instanceof Wolf) {
                        newAnimal = new Wolf(x_, y_);
                    } else {
                        throw new IllegalStateException("Unknown animal type");
                    }
                    newAnimal.SetWorld(world);
                    world.organisms.add(newAnimal);
                    return;
                }
            }
        }
    }


