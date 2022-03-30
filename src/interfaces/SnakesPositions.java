package interfaces;

import utils.piles.PileOnlyReadable;

public interface SnakesPositions {
	public PileOnlyReadable getPlayerNPositions(int nPlayer);
	public boolean isAlive(int nPlayer);
	public boolean isDying(int nPlayer);
	public int getNumPlayer();
}
