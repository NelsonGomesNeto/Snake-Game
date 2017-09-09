package logic;

import data.Direction;
import data.GameState;
import data.Snake;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter {

    private SnakeGame snakeGame;

    public Keys(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Snake snake = snakeGame.getSnake();

        if (isLeftKey(key) && !snake.isMovingRight()) {
            snake.setDirection(Direction.LEFT);
        } else if (isRightKey(key) && !snake.isMovingLeft()) {
            snake.setDirection(Direction.RIGHT);
        } else if (isUpKey(key) && !snake.isMovingDown()) {
            snake.setDirection(Direction.UP);
        } else if (isDownKey(key) && !snake.isMovingUp()) {
            snake.setDirection(Direction.DOWN);
        }

        if (isEscKey(key) && snakeGame.getGameState() == GameState.IN_GAME) {
            snakeGame.setGameState(GameState.PAUSED);
        } else if (isEscKey(key) && snakeGame.getGameState() == GameState.PAUSED) {
            snakeGame.setGameState(GameState.IN_GAME);
        } else if (isEscKey(key) && snakeGame.getGameState() == GameState.GAME_OVER) {
            snakeGame.reset();
        }
    }

    private boolean isLeftKey(int key) {
        return key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A;
    }

    private boolean isRightKey(int key) {
        return key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D;
    }

    private boolean isUpKey(int key) {
        return key == KeyEvent.VK_UP || key == KeyEvent.VK_W;
    }

    private boolean isDownKey(int key) {
        return key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S;
    }

    private boolean isEscKey(int key) {
        return key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER;
    }
}
