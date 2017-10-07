package data.DirectionState;

import data.Direction;

public class Up implements DirectionState {

	@Override
	public DirectionState turn(Direction direction) {
		if (direction == Direction.LEFT) {
			return new Left();
		}
		if (direction == Direction.RIGHT) {
			return new Right();
		}
		return this;
	}

	@Override
	public int getNextX() {
		return 0;
	}

	@Override
	public int getNextY() {
		return -1;
	}
}
