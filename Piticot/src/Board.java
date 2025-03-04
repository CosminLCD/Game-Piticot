import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private int[] board = new int[72];
    private Map<Integer, Integer> specialSquares = new HashMap<>();
    private Map<Integer, Color> squareColors = new HashMap<>();
    private Map<Integer, String> specialMessages = new HashMap<>();

    public Board() {
        // Initialize board
        for (int i = 0; i < board.length; i++) {
            board[i] = i;
        }

        // Define special rules
        specialSquares.put(3, 4); // Covorul fermecat - inainteaza cu 4 casute
        specialMessages.put(3, "Urca pe covorul zburator si inainteaza 4 casute");

        specialSquares.put(8, 13); // Tunel secret - inainteaza la 21
        specialMessages.put(8, "Frumoasa din Padurea Adormita iti arata un tunel secret. Inainteaza pana la casuta 21");

        specialSquares.put(11, 0); // Sta o tura
        specialMessages.put(11, "Asteapta sa treaca oile. Stai o tura");

        specialSquares.put(14, 0); // Dubleaza punctele de pe zar
        specialMessages.put(14, "Brosca fermecata iti dubleaza punctele de pe zar");

        specialSquares.put(19, 5); // Prietenia cu zana - inainteaza la 24
        specialMessages.put(19, "Te-ai imprietenit cu o zana, aceasta te trimite la casuta 24.");

        specialSquares.put(29, -3); // Inapoi 3 casute
        specialMessages.put(29, "Sirenele isi inchipuie ca sunt cantarete. Fugi inapoi 3 casute ca sa nu le mai auzi.");

        specialSquares.put(33, 0); // Teleporteaza la cel mai inaintat pion
        specialMessages.put(33, "Vrajitoarea este in toane bune azi. Te va teleporta in casuta celui mai avansat jucator.");

        specialSquares.put(36, -7); // Inapoi la 29
        specialMessages.put(36, "Inspectorul Caramet este de parere ca sirenele au talent. Intoarcete la casuta 29 ca sa le mai auzi o data.");

        specialSquares.put(38, 0); // Sta o tura
        specialMessages.put(38, "Ajuta-l pe Omul de Tinichea sa isi adune piesele. Stai o tura.");

        specialSquares.put(41, 2); // Inainteaza 2 casute
        specialMessages.put(41, "Casuta celor trei porcusori este in constructie. Inainteaza 2 casute ca sa le ocolesti santierul.");

        specialSquares.put(44, 0); // Schimba locul cu adversarul
        specialMessages.put(44, "Schimba locul cu adversarul.");

        specialSquares.put(49, 0); // Adversarul sta o tura
        specialMessages.put(49, "Ghicitoarea iti va indeplini o dorinta, adversarul va sta o tura.");

        specialSquares.put(52, -5); // Inapoi 5 casute
        specialMessages.put(52, "Te-ai intalnit cu o iscoada a balauruli care pazeste comoara. Mergi inapoi 5 casute.");

        specialSquares.put(55, 3); // Inainteaza 3 casute
        specialMessages.put(55, "Pentru ca drumul a fost obositor. Magarusul se ofera sa te duca 3 casute.");

        specialSquares.put(60, -7); // Inapoi 7 casute
        specialMessages.put(60, "Acrobatul de la circ te arunca inapoi 7 casute.");

        specialSquares.put(67, -3); // Inapoi la 64
        specialMessages.put(67, "L-ai zarit pe lupul cel rau. Intoarcete la casuta 64 sa o avertizezi pe Scufita Rosie.");

        // Define colors for special squares
        squareColors.put(3, Color.YELLOW);
        squareColors.put(8, Color.ORANGE);
        squareColors.put(11, Color.PINK);
        squareColors.put(14, Color.CYAN);
        squareColors.put(19, Color.MAGENTA);
        squareColors.put(29, Color.RED);
        squareColors.put(33, Color.GREEN);
        squareColors.put(36, Color.BLUE);
        squareColors.put(38, Color.GRAY);
        squareColors.put(41, Color.LIGHT_GRAY);
        squareColors.put(44, Color.DARK_GRAY);
        squareColors.put(49, Color.MAGENTA);
        squareColors.put(52, Color.WHITE);
        squareColors.put(55, Color.YELLOW);
        squareColors.put(60, Color.ORANGE);
        squareColors.put(67, Color.PINK);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < board.length; i++) {
            if (squareColors.containsKey(i)) {
                g.setColor(squareColors.get(i));
                g.fillRect(10 + (i % 9) * 50, 10 + (i / 9) * 50, 50, 50);
            }
            g.setColor(Color.BLACK);
            g.drawRect(10 + (i % 9) * 50, 10 + (i / 9) * 50, 50, 50);
            g.drawString(Integer.toString(board[i]), 25 + (i % 9) * 50, 35 + (i / 9) * 50);
        }
    }

    public int getSpecialMove(int position) {
        return specialSquares.getOrDefault(position, 0);
    }

    public String getSpecialMessage(int position) {
        return specialMessages.getOrDefault(position, "");
    }
}
