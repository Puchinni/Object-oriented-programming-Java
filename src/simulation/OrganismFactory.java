package simulation;

import simulation.animal.*;
import simulation.plant.*;
public class OrganismFactory {
     public static Organism create(OrganismType type, int x, int y) {
         switch (type) {
             case EMPTY:
                 return null;
             case Fox:
                return new Fox(x, y);
             case Guarana:
                return new Guarana(x, y);
            case Grass:
                return new Grass(x, y);
           case Hogweed:
                return new Hogweed(x, y);
             case Sonchus:
                return new Sonchus(x, y);
             case Sheep:
                return new Sheep(x, y);
            case Turtle:
                return new Turtle(x, y);
             case Wolf:
                return new Wolf(x, y);
            case WolfBerries:
                return new WolfBerries(x, y);
            case Antelope:
                return new Antelope(x, y);
             default:
                 throw new RuntimeException("Unknown organism type: " + type);
         }
     }
}
