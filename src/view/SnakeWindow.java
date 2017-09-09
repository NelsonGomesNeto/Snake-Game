package view;

import data.GameState;
import data.Snake;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import logic.Keys;
import logic.SnakeGame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class SnakeWindow extends JPanel {

    public final static int WINDOW_WIDTH = 1000;
    public final static int WINDOW_HEIGHT = 720;
    public final static int PIXEL_SIZE = 20;
    public final static int WIDTH_IN_PIXELS = WINDOW_WIDTH / PIXEL_SIZE;
    public final static int HEIGHT_IN_PIXELS = WINDOW_HEIGHT / PIXEL_SIZE;


    private SnakeGame snakeGame = new SnakeGame();

    public SnakeWindow() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        snakeGame.setWindow(this);
        addKeyListener(new Keys(snakeGame));
        snakeGame.startNewGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (snakeGame.getGameState() == GameState.IN_GAME) {
            Snake snake = snakeGame.getSnake();
            Point foodPosition = snakeGame.getFoodPosition();
            g.setColor(Color.WHITE);
            drawFood(g, foodPosition);
            drawSnake(g, snake);
            Toolkit.getDefaultToolkit().sync();
        } else if (snakeGame.getGameState() == GameState.GAME_OVER) {
            displayGameOver(g);
        }
    }

    private void drawFood(Graphics g, Point foodPosition) {
        g.fillOval(foodPosition.x, foodPosition.y, PIXEL_SIZE, PIXEL_SIZE);
    }

    private void drawSnake(Graphics g, Snake snake) {
        for (Point p : snake.getSegmentQueue()) {
            g.fillRect(p.x, p.y, PIXEL_SIZE, PIXEL_SIZE);
        }
    }

    private void displayGameOver(Graphics g) {
        /* Display Death */
        String msg = "You Died";
        Font font = new Font("Times New Roman", Font.PLAIN, 32);
        FontMetrics metrics = getFontMetrics(font);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(msg, (WINDOW_WIDTH - metrics.stringWidth(msg)) / 2, WINDOW_HEIGHT / 2);

        /* Display Score */
        String scoreMsg = " Score: " + snakeGame.getSnake().getSegmentQueue().size();
        scoreMsg += "  High Score: " + readHighScore();
        font = new Font("Times New Roman", Font.PLAIN, 14);
        metrics = getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(scoreMsg, (WINDOW_WIDTH - metrics.stringWidth(scoreMsg)) / 2, (3 * WINDOW_HEIGHT) / 4);
    }

    private String readHighScore() {
        try {
            Scanner in = new Scanner(new FileReader("src/resources/highScore.txt"));
            return in.next();
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected Exception: " + e.toString());
        }
        return "Not found";
    }
}
