import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int position;
    private Color color;
    private boolean hasWon;
    private boolean skipNextTurn;
    private Board board;

    public Player(String name, Color color, Board board) {
        this.name = name;
        this.color = color;
        this.position = 0;
        this.hasWon = false;
        this.skipNextTurn = false;
        this.board = board;
    }

    public void move(int steps) {
        if (skipNextTurn) {
            skipNextTurn = false;
            return;
        }

        position += steps;
        if (position >= 71) {
            position = 71; // Ultima casută
            hasWon = true;
        } else {
            int specialMove = board.getSpecialMove(position);
            String message = board.getSpecialMessage(position);
            if (!message.isEmpty()) {
                JOptionPane.showMessageDialog(null, message);
                if (specialMove != 0) {
                    position += specialMove;
                    if (position >= 71) {
                        position = 71;
                        hasWon = true;
                    }
                }
            }

            // Handle specific cases
            if (position == 11 || position == 38) {
                skipNextTurn = true;
            } else if (position == 14) {
                position += steps; // Double the steps from dice
            } else if (position == 33) {
                // Teleport to the most advanced player
                int maxPosition = this.position;
                for (Player player : Game.players) {
                    if (player != this && player.getPosition() > maxPosition) {
                        maxPosition = player.getPosition();
                    }
                }
                position = maxPosition;
            } else if (position == 44) {
                // Swap places with the opponent
                Player opponent = (this == Game.players[0]) ? Game.players[1] : Game.players[0];
                int tempPosition = opponent.getPosition();
                opponent.setPosition(this.position);
                this.position = tempPosition;
            } else if (position == 49) {
                // Opponent skips next turn
                Player opponent = (this == Game.players[0]) ? Game.players[1] : Game.players[0];
                opponent.setSkipNextTurn(true);
            }
        }
    }

    public void draw(Graphics g) {
        int x = 10 + (position % 9) * 50;
        int y = 10 + (position / 9) * 50;
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public String getName() {
        return name;
    }

    public boolean getSkipNextTurn() {
        return skipNextTurn;
    }

    public void setSkipNextTurn(boolean skipNextTurn) {
        this.skipNextTurn = skipNextTurn;
    }

    public static Player createPlayer(Board board, int i) {
        String name = JOptionPane.showInputDialog("Introduceți numele jucătorului "+i+" :");
        Color color = chooseColor();
        return new Player(name, color, board);
    }

    private static Color chooseColor() {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
        String[] colorNames = {"Roșu", "Albastru", "Verde", "Galben", "Portocaliu", "Magenta"};

        int choice = JOptionPane.showOptionDialog(null, "Alegeți culoarea jucătorului:", "Selectați Culoarea",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, colorNames, colorNames[0]);

        if (choice >= 0 && choice < colors.length) {
            return colors[choice];
        } else {
            return Color.BLACK; // Default color
        }
    }
}
