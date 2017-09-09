package data;

import java.util.LinkedList;
import java.util.Queue;
import view.SnakeWindow;
import java.awt.Point;

public class Snake {

    private Queue<Point> segmentQueue = new LinkedList<>();
    private Point head;
    private Direction direction;
    private int segmentsToGrow = 0;

    public Queue<Point> getSegmentQueue() {
        return segmentQueue;
    }

    public Point getHead() {
        return head;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Snake() {
        int midX = SnakeWindow.WINDOW_WIDTH / 2;
        int midY = SnakeWindow.WINDOW_HEIGHT / 2;
        head = new Point(midX, midY);
        segmentQueue.add(head);
    }

    public void move() {
        Point p = (Point) head.clone();
        getNewPosition(p);
        segmentQueue.add(p);

        if (segmentsToGrow > 0) {
            segmentsToGrow--;
        } else {
            segmentQueue.remove();
        }
        head = p;
    }

    private void getNewPosition(Point p) {
        if (direction == Direction.LEFT) {
            p.x -= SnakeWindow.PIXEL_SIZE;
        } else if (direction == Direction.RIGHT) {
            p.x += SnakeWindow.PIXEL_SIZE;
        } else if (direction == Direction.UP) {
            p.y -= SnakeWindow.PIXEL_SIZE;
        } else if (direction == Direction.DOWN) {
            p.y += SnakeWindow.PIXEL_SIZE;
        }
    }

    public boolean isMovingLeft() {
        return direction == Direction.LEFT;
    }

    public boolean isMovingRight() {
        return direction == Direction.RIGHT;
    }

    public boolean isMovingUp() {
        return direction == Direction.UP;
    }

    public boolean isMovingDown() {
        return direction == Direction.DOWN;
    }

    public void addSegmentsToGrow() {
        segmentsToGrow += 5;
    }
}
