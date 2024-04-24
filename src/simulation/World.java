package simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import simulation.animal.*;
import simulation.plant.*;
import simulation.animal.Human;


public class World extends JFrame implements KeyListener{
    private Organism[][] organismsBoard = null;
    private int row, col, turn;
    private int stepCount = 0;
    private Human human;
    public ArrayList<Organism> organisms;
    public int GetRow()
    {
        return row;
    }
    public int GetCol()
    {
        return col;
    }
    public void ClearBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                organismsBoard[i][j] = null;
            }
        }
    }
    public Organism GetOrganismByPosition(int x, int y) {
        for (Organism org : organisms) {
            if (org.GetX() == x && org.GetY() == y) {
                return org;
            }
        }
        return null;
    }
    public void setOrganismOnBoard(int x, int y, Organism org) {
        organismsBoard[y][x] = org;
    }
    public boolean isEmptyCell(int x, int y)
    {
        return organismsBoard[y][x] == null;
    }
    public boolean isCellExists(int x, int y)
    {
        return !(x < 0 || x >= col || y < 0 || y >= row);
    }

    public World(int row, int col, ArrayList<Organism> organisms,Human human) {
        super("Aliaksei Yashynski 196691");
        this.row = row;
        this.col = col;
        this.organisms = organisms;
        this.human = human;

        organismsBoard = new Organism[row][col];
        ClearBoard();

        for (Organism org : organisms) {
            organismsBoard[org.GetY()][org.GetX()] = org;
            if (org instanceof Animal) {
                ((Animal) org).SetWorld(this);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(col * 20, row * 22 + 100);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    x = x / 20;
                    y = (y - 40) / 20;
                    insert(x, y);
                }
            }
        });
        KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP -> {
                        MoveHuman(0,-1);
                        ExecuteTure();

                    }
                    case KeyEvent.VK_DOWN -> {
                        MoveHuman(0,1);
                        ExecuteTure();

                    }
                    case KeyEvent.VK_LEFT -> {
                        MoveHuman(-1,0);
                        ExecuteTure();

                    }
                    case KeyEvent.VK_RIGHT -> {
                        MoveHuman(1,0);
                        ExecuteTure();

                    }
                    case KeyEvent.VK_U -> {
                        if (stepCount >= 0 && stepCount < 5) {
                            human.activateAbility();
                            System.out.println("Ability is activated. Strength: " + human.GetStrenght2());
                        }
                        else if (stepCount >= 5 && stepCount < 10){
                            human.disactivateAbility();
                            System.out.println("Cooldown. Strength: " + human.GetStrenght2());
                        }
                    }
                }
            }
            public void keyReleased(KeyEvent keyEvent) {
            }
            public void keyTyped(KeyEvent keyEvent) {
            }
        };
        JButton button = new JButton("Legend");
        button.addActionListener(e -> {
            ShowLegend();

        });
        button.addKeyListener(keyListener);
        button.setBounds(0, row * 20+5, col * 20, 50);
        add(button);

        addKeyListener(keyListener);
        JTextField filenameField = new JTextField();
        filenameField.setBounds(col * 10, row * 20+5, col * 10, 50);
        add(filenameField);

        JButton button2 = new JButton("Save to file");
        button2.addActionListener(e -> {
            requestFocusInWindow();
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fName = selectedFile.getPath();
                try {
                    save(fName);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Complete! Press any key...");

            }

        });
        button2.addKeyListener(keyListener);
        button2.setBounds(col * 10, row * 20+55, col * 10, 50);
        add(button2);

        JButton button3 = new JButton("Load from file");
        button3.addActionListener(e -> {
            requestFocusInWindow();
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fName = selectedFile.getPath();
                try {
                    restore(fName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Complete! Press any key...");
            }
        });
        button3.addKeyListener(keyListener);
        button3.setBounds(0, row * 20+55, col * 10, 50);
        add(button3);

        setResizable(false);
        setLayout(null);
        setVisible(true);
        requestFocusInWindow();
        this.repaint();
    }
   public void insert(int x, int y) {
       if (x >= 0 && x < col && y >= 0 && y < row) {
           System.out.println(x + " " + y);
           if (isEmptyCell(x, y)) {
               List<String> names = new ArrayList<>();
               /*for (Organism org : organisms) {
                   if (org instanceof Human) {}
                   else names.add(org.Draw());
               }*/
               names.add("Sheep");
               names.add("Antelope");
               names.add("Fox");
               names.add("Turtle");
               names.add("Wolf");
               names.add("Grass");
               names.add("Guarana");
               names.add("Hogweed");
               names.add("Sonchus");
               names.add("WolfBerries");
               // Dialog window
               String selectedName = (String) JOptionPane.showInputDialog(
                       null,
                       "Select an organism:",
                       "Add organism",
                       JOptionPane.PLAIN_MESSAGE,
                       null,
                       names.toArray(),
                       null);
               if (selectedName != null) {
                   Organism newOrg = null;
                   switch (selectedName) {
                       case "Sheep":
                           newOrg = new Sheep(x, y);
                           break;
                       case "Antelope":
                           newOrg = new Antelope(x, y);
                           break;
                       case "Fox":
                           newOrg = new Fox(x, y);
                           break;
                       case "Turtle":
                           newOrg = new Turtle(x, y);
                           break;
                       case "Wolf":
                           newOrg = new Wolf(x, y);
                           break;
                       case "Grass":
                           newOrg = new Grass(x, y);
                           break;
                       case "Guarana":
                           newOrg = new Guarana(x, y);
                           break;
                       case "Hogweed":
                           newOrg = new Hogweed(x, y);
                           break;
                       case "Sonchus":
                           newOrg = new Sonchus(x, y);
                           break;
                       case "WolfBerries":
                           newOrg = new WolfBerries(x, y);
                           break;
                   }
                   if (newOrg != null) {
                       organisms.add(newOrg);
                       setOrganismOnBoard(x, y, newOrg);
                       repaint();
                   }
               }
           }
       }
   }
           private void ShowLegend() {
               String message = "Black: Antelope\n"
                       + "Blue: Fox\n"
                       + "Green: Grass\n"
                       + "Cyan: Guarana\n"
                       + "Dark Gray: Hogweed\n"
                       + "Magenta: Human\n"
                       + "Gray: Sonchus\n"
                       + "Light Grey: Sheep\n"
                       + "Orange: Turtle\n"
                       + "Red: Wolf\n"
                       + "Pink: WolfBerries\n";
               JOptionPane.showMessageDialog(this, message, "Legend", JOptionPane.INFORMATION_MESSAGE);
           }
           private void MoveHuman(int dx, int dy) {
               int newX = human.GetX()+dx;
               int newY = human.GetY()+dy;
               if (newX<0 || newX >= col || newY <0 || newY >= row) return;
               human.SetX(newX);
               human.SetY(newY);
               repaint();
           }

           public void paint(Graphics g) {
               super.paint(g);
               for(Organism organism: organisms) {
                   g.setColor(organism.getColor());
                   g.drawRect(organism.GetX()*20,(organism.GetY()+2)*20,20,20 );
                   g.fillRect(organism.GetX()*20,(organism.GetY()+2)*20,20,20);
               }
           }
           public void ExecuteTure() {
           Collections.sort(organisms, new Comparator<Organism>() {
                           @Override
                           public int compare(Organism o1, Organism o2) {
                               if (o1.GetInitiative() == o2.GetInitiative()) {
                                   return o2.GetAge() - o1.GetAge();
                               } else {
                                   return o2.GetInitiative() - o1.GetInitiative();
                               }
                           }
            });
               ClearBoard();
               stepCount++;
               if (stepCount == 10) stepCount = 0;
               System.out.println("Turn: " + turn++);
               int currentSize = organisms.size();
               for (int i = 0; i < currentSize; i++) {
                   organisms.get(i).Action(this);
                   if (organisms.get(i) instanceof Animal
                           && organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()] != null) {
                       organisms.get(i).Colission(organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()]);
                       organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()].Colission(organisms.get(i));
                       System.out.println(organisms.get(i).Draw() + " x " + organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()].Draw());
                   }
                   if (organisms.get(i) instanceof Plant
                           && organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()] != null) {
                       organisms.get(i).Colission(organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()]);
                       organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()].Colission(organisms.get(i));
                       System.out.println(organisms.get(i).Draw() + " x " + organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()].Draw());
                   }
                   organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()] = organisms.get(i);
               }
               for (int i = currentSize; i < organisms.size(); i++) {
                   if (organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()] != null) {
                       Organism other = organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()];
                       organisms.get(i).Colission(other);
                       other.Colission(organisms.get(i));
                   }
               }
               for (int i = 0; i < organisms.size(); i++) {
                   if (organisms.get(i).IsDied()) {
                       this.organismsBoard[organisms.get(i).GetY()][organisms.get(i).GetX()] = null;
                       System.out.println(organisms.get(i).Draw() + "-> is died!");
                       organisms.remove(i);
                       i--;
                   }
               }
               System.out.println("H [" + organisms.get(0).GetX() + ";" + organisms.get(0).GetY() + "]");
               System.out.println("Organism Count: " + organisms.size());
           }

    /*void save(String fName) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fName));
            writer.println(row + " " + col);
            for (int i = 0; i < row; i++) {
                writer.println(organisms.size() - 1);
                for (Organism o : organisms) {
                    if(o instanceof Human) continue;
                    writer.printf("%s %d %d%n", o.getClass().getSimpleName(), o.GetX(), o.GetY());
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }*/
    void save(String fName) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fName));
            List<Organism> existingOrganisms = new ArrayList<>();
            for (Organism o : organisms) {
                if (o instanceof Human) {
                    continue;
                }
                if (o.GetX() >= 0 && o.GetX() < row && o.GetY() >= 0 && o.GetY() < col) {
                    existingOrganisms.add(o);
                }
            }
            writer.println(row + " " + col);
            writer.println(existingOrganisms.size());
            for (Organism o : existingOrganisms) {
                writer.printf("%s %d %d%n", o.getClass().getSimpleName(), o.GetX(), o.GetY());
            }

            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void restore(String fName) throws IOException {
               try (BufferedReader fin = new BufferedReader(new FileReader(fName))) {
                   for (int i = 0; i < row; i++) {
                       for (int j = 0; j < col; j++) {
                           organismsBoard[i][j] = null;
                       }
                   }
                   organisms.clear();
                   //Reading the first line from the file and extracting the size of the playing field.
                   String[] tokens = fin.readLine().split(" ");
                   row = Integer.parseInt(tokens[0]);
                   col = Integer.parseInt(tokens[1]);
                   organismsBoard = new Organism[row][col];
                   int numOrganisms = Integer.parseInt(fin.readLine());
                   for (int i = 0; i < numOrganisms; i++) {
                       String[] organismData = fin.readLine().split(" ");
                       String organismType = organismData[0];
                       int x = Integer.parseInt(organismData[1]);
                       int y = Integer.parseInt(organismData[2]);
                       Organism o = OrganismFactory.create(OrganismType.valueOf(organismType), x, y);
                       organismsBoard[y][x] = o;
                       organisms.add(o);
                       organisms.add(human);
                       // setOrganismOnBoard(human.GetX(), human.GetY(), human);
                       //organisms.add(human);

                   }
                   repaint();

               }
           }


           @Override
           public void keyPressed(KeyEvent e) {}
           @Override
           public void keyReleased(KeyEvent e) {}
           @Override
           public void keyTyped(KeyEvent e) {}
    /* public void DrawWorld() {
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setTitle("Organisms World");
         JPanel gridPanel = new JPanel(new GridLayout(row, col, 1, 1));
         for (int i = 0; i < row; i++) {
             for (int j = 0; j < col; j++) {
                // char symbol = (organismsBoard[i][j] == null) ? OrganismType.EMPTY.getSymbol() : organismsBoard[i][j].Draw();
                 //JLabel cellLabel = new JLabel(Character.toString(symbol), SwingConstants.CENTER);
               //  cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // gridPanel.add(cellLabel);
             }
         }

         getContentPane().add(gridPanel);
         pack();
         setResizable(true); // добавить эту строку
        // setSize(600, 600);
         setLocationRelativeTo(null);
         setVisible(true);
     }*/
       }


