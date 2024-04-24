package simulation;
    public enum OrganismType {
        EMPTY(' '),
        Fox('F'),
        Guarana('G'),
        Grass('#'),
        Hogweed('h'),
        Human('H'),
        Sonchus('K'),
        Sheep('S'),
        Turtle('T'),
        Wolf('W'),
        WolfBerries('&'),
        Antelope('A');

        private char symbol;

        OrganismType(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }


