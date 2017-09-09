package view;

import data.GameState;
import data.Snake;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
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
        snakeGame.initialize(this);
        addKeyListener(new Keys(snakeGame));
        snakeGame.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    void draw(Graphics g) {
        if (snakeGame.getGameState() == GameState.IN_GAME) {
            Snake snake = snakeGame.getSnake();
            Point foodPosition = snakeGame.getFoodPosition();
            g.setColor(Color.WHITE);
            g.fillOval(foodPosition.x, foodPosition.y, PIXEL_SIZE, PIXEL_SIZE);

            //draw snake
            for (Point p : snake.getSegmentQueue()) {
                g.fillRect(p.x, p.y, PIXEL_SIZE, PIXEL_SIZE);
            }
            Toolkit.getDefaultToolkit().sync();
        } else if (snakeGame.getGameState() == GameState.GAME_OVER) {
            //end game
        }
    }
}
