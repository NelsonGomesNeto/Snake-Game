package logic;

import data.GameState;
import data.Snake;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;
import javax.swing.Timer;
import view.SnakeWindow;

public class SnakeGame implements ActionListener {

    private JPanel window;
    private Timer timer;
    private GameState gameState;
    private Snake snake;
    private Point foodPosition;
    private final int DELAY = 50;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getFoodPosition() {
        return foodPosition;
    }

    public void initialize(JPanel window) {
        this.window = window;
        snake = new Snake();
        foodPosition = getRandomPosition();
        gameState = GameState.IN_GAME;
        timer = new Timer(DELAY, this);
    }

    public void reset() {
        snake = new Snake();
        foodPosition = getRandomPosition();
        gameState = GameState.IN_GAME;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.IN_GAME) {
            updateGame();
            window.repaint();
        }
    }

    public void updateGame() {
        checkFood();
        checkCollision();
        snake.move();
    }

    private void checkFood() {
        if (snake.getHead().equals(foodPosition)) {
            snake.addSegmentsToGrow();
            foodPosition = getRandomPosition();
        }
    }

    private void checkCollision() {
        snake.getSegmentQueue().stream()
                .filter(p -> Collections.frequency(snake.getSegmentQueue(), p) > 1)
                .findAny()
                .ifPresent(p -> {
                    System.out.println("GAME OVER: Collision at " + p.toString());
                    timer.stop();
                    gameState = GameState.GAME_OVER;
                });
    }

    private Point getRandomPosition() {
        int randX = SnakeWindow.PIXEL_SIZE * ThreadLocalRandom.current().nextInt(SnakeWindow.WIDTH_IN_PIXELS);
        int randY = SnakeWindow.PIXEL_SIZE * ThreadLocalRandom.current().nextInt(SnakeWindow.HEIGHT_IN_PIXELS);
        return new Point(randX, randY);
    }
}
