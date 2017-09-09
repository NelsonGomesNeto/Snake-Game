package logic;

import data.GameState;
import data.Snake;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
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
    private int highScore;

    public GameState getGameState() {
        return gameState;
    }

    void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getFoodPosition() {
        return foodPosition;
    }

    public void setWindow(JPanel window) {
        this.window = window;
    }

    public void startNewGame() {
        snake = new Snake();
        foodPosition = getRandomPosition();
        highScore = readHighScore();
        gameState = GameState.IN_GAME;
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.IN_GAME) {
            updateGame();
            window.repaint();
        }
    }

    private void updateGame() {
        checkFood();
        checkSelfCollision();
        checkWallCollision();
        snake.move();
    }

    private void checkFood() {
        if (snake.getHead().equals(foodPosition)) {
            snake.addSegmentsToGrow();
            foodPosition = getRandomPosition();
        }
    }

    private void checkSelfCollision() {
        snake.getSegmentQueue().stream()
                .filter(p -> Collections.frequency(snake.getSegmentQueue(), p) > 1)
                .findAny()
                .ifPresent(p -> endGame());
    }

    private void checkWallCollision() {
        Point head = snake.getHead();
        if (head.x < 0
                || head.x > SnakeWindow.WINDOW_WIDTH
                || head.y < 0
                || head.y > SnakeWindow.WINDOW_HEIGHT) {
            endGame();
        }
    }

    private void endGame() {
        timer.stop();
        gameState = GameState.GAME_OVER;
        int score = snake.getSegmentQueue().size();
        if (score > highScore) {
            writeHighScore(score);
        }

    }

    private Point getRandomPosition() {
        int randX = SnakeWindow.PIXEL_SIZE * ThreadLocalRandom.current().nextInt(SnakeWindow.WIDTH_IN_PIXELS);
        int randY = SnakeWindow.PIXEL_SIZE * ThreadLocalRandom.current().nextInt(SnakeWindow.HEIGHT_IN_PIXELS);
        return new Point(randX, randY);
    }

    private int readHighScore() {
        try {
            Scanner in = new Scanner(new FileReader("src/resources/highScore.txt"));
            return in.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected Exception: " + e.toString());
        }
        return 0;
    }

    private void writeHighScore(int highScore) {
        try {
            PrintWriter writer = new PrintWriter("src/resources/highScore.txt", "UTF-8");
            writer.println(String.valueOf(highScore));
            writer.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            System.out.println("Unexpected Exception: " + e.toString());
        }
    }
}
