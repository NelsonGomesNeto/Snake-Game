package data.DirectionState;

import data.Direction;
import data.Snake;

public interface DirectionState {

	DirectionState turn(Direction direction);

	int getNextX();
	int getNextY();
}
