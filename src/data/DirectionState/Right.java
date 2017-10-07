package data.DirectionState;

import data.Direction;

public class Right implements DirectionState {

	@Override
	public DirectionState turn(Direction direction) {
		if (direction == Direction.DOWN) {
			return new Down();
		}
		if (direction == Direction.UP) {
			return new Up();
		}
		return this;
	}

	@Override
	public int getNextX() {
		return 1;
	}

	@Override
	public int getNextY() {
		return 0;
	}
}
