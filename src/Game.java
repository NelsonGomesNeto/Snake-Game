import view.SnakeWindow;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.EventQueue;

public class Game extends JFrame {

    private Game() {
        add(new SnakeWindow());
        setResizable(false);
        pack();

        setTitle("SNAKE");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new Game();
            frame.setVisible(true);
        });
    }
}
