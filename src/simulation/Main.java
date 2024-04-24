package simulation;

import simulation.animal.*;
import simulation.plant.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Human human = new Human(2, 2);
        ArrayList<Organism> orgs = new ArrayList<>();
        orgs.add(human);
        orgs.add(new Sheep(4, 5));
        orgs.add(new Antelope(7, 7));
        orgs.add(new Sonchus(6, 6));
        orgs.add(new Fox(4, 1));
        orgs.add(new Turtle(9, 8));
        orgs.add(new Hogweed(3, 2));
        orgs.add(new Guarana(3, 3));
        orgs.add(new Wolf(9, 9));
        orgs.add(new WolfBerries(8, 9));
        orgs.add(new Grass(0, 0));
        World w = new World(20, 20, orgs, human);
    }
}
