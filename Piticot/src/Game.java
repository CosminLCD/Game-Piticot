import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    static Player[] players;
    private Board board;
    private Dice dice;
    private int currentPlayer;
    private JLabel currentPlayerLabel;
    private JLabel diceValueLabel; // Adăugăm un JLabel pentru valoarea zarului

    public Game() {
        board = new Board();
        players = new Player[2];
        players[0] = Player.createPlayer(board,1);
        players[1] = Player.createPlayer(board,2);
        dice = new Dice();
        currentPlayer = 0;

        this.setTitle("Piticot Game");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        this.add(panel);

        JPanel controlPanel = new JPanel();
        currentPlayerLabel = new JLabel("Este rândul lui " + players[currentPlayer].getName());
        controlPanel.add(currentPlayerLabel);

        diceValueLabel = new JLabel(" Valoarea zarului: "); // Inițializăm JLabel pentru valoarea zarului
        controlPanel.add(diceValueLabel);

        JButton rollButton = new JButton("Da cu zarul");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players[currentPlayer].hasWon()) {
                    JOptionPane.showMessageDialog(null, players[currentPlayer].getName() + " a câștigat deja comoara!");
                    return;
                }

                int steps = dice.roll();
                diceValueLabel.setText(" Valoarea zarului: " + steps); // Actualizăm textul JLabel cu valoarea zarului

                players[currentPlayer].move(steps);

                panel.repaint();

                if (players[currentPlayer].hasWon()) {
                    JOptionPane.showMessageDialog(null, players[currentPlayer].getName() + " a câștigat Comoara Piticului!");
                } else {
                    currentPlayer = (currentPlayer + 1) % players.length;
                    while (players[currentPlayer].getSkipNextTurn()) {
                        players[currentPlayer].setSkipNextTurn(false);
                        currentPlayer = (currentPlayer + 1) % players.length;
                    }
                    currentPlayerLabel.setText("Este rândul lui " + players[currentPlayer].getName());
                }
            }
        });

        controlPanel.add(rollButton);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            board.draw(g);
            for (Player player : players) {
                player.draw(g);
            }
        }
    }

    public void start() {
        // Initialize game logic if needed
    }
}
