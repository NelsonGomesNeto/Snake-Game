package data

import view.SnakeWindow

import java.awt.Point

class Snake {

    Queue<Point> segmentQueue = new LinkedList<>()
    Point head
    Direction direction
    int segmentsToGrow = 0

    Snake() {
        int midX = SnakeWindow.WINDOW_WIDTH/2
        int midY = SnakeWindow.WINDOW_HEIGHT/2
        head = new Point(midX, midY)
        segmentQueue.add(head)
        direction = Direction.RIGHT
    }

    void move() {
        Point p = head.clone() as Point
        if (direction == Direction.LEFT) {
            int newX = p.x - SnakeWindow.PIXEL_SIZE
            p.x = Math.max(0, newX)
        } else if (direction == Direction.RIGHT) {
            int newX = p.x + SnakeWindow.PIXEL_SIZE
            p.x = Math.min(newX, SnakeWindow.WINDOW_WIDTH)
        } else if (direction == Direction.UP) {
            int newY = p.y - SnakeWindow.PIXEL_SIZE
            p.y = Math.max(0, newY)
        } else if (direction == Direction.DOWN) {
            int newY = p.y + SnakeWindow.PIXEL_SIZE
            p.y = Math.min(newY, SnakeWindow.WINDOW_HEIGHT)
        }
        segmentQueue.add(p)
        if (segmentsToGrow > 0) {
            segmentsToGrow--
        } else {
            segmentQueue.remove()
        }
        head = p
    }

    boolean isMovingLeft() {
        direction == Direction.LEFT
    }

    boolean isMovingRight() {
        direction == Direction.RIGHT
    }

    boolean isMovingUp() {
        direction == Direction.UP
    }

    boolean isMovingDown() {
        direction == Direction.DOWN
    }

    void addSegmentsToGrow() {
        segmentsToGrow += 5
    }
}
