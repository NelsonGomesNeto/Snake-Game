package data;

import java.util.LinkedList;
import java.util.Queue;

import data.DirectionState.DirectionState;
import data.DirectionState.Left;
import view.SnakeWindow;
import java.awt.Point;

public class Snake {

    private Queue<Point> segmentQueue = new LinkedList<>();
    private Point head;
    public DirectionState directionState = new Left();
    private Direction direction;
    private int segmentsToGrow = 0;

    public Queue<Point> getSegmentQueue() {
        return segmentQueue;
    }

    public Point getHead() {
        return head;
    }

    public void setDirection(Direction direction) { this.directionState = directionState.turn(direction); }

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
        if (directionState != null) {
            p.x += directionState.getNextX() * SnakeWindow.PIXEL_SIZE;
            p.y += directionState.getNextY() * SnakeWindow.PIXEL_SIZE;
        }
    }

    public void addSegmentsToGrow() {
        segmentsToGrow += 5;
    }
}
