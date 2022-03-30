package backend;

import java.awt.event.*;
import interfaces.*;
import utils.clocks.*;
import utils.piles.PileOnlyReadable;
import utils.settings.ConstantsManager;
import utils.vectors.Vector2D;

public class GameManager implements ClockListener, SnakesPositions, KeyListener{
	private Player[] snakes;
	
	private final static int ROTATION_POWER=ConstantsManager.getIntValueOrCrash("rotationPower"),
					SPEED=ConstantsManager.getIntValueOrCrash("speed");
	
	public GameManager() {
		initializeSnakes();
	}
	private void initializeSnakes() {  //TODO PULISCO
		int numberSnakes=ConstantsManager.getIntValueOrCrash("numberSnakes");
		int dimension=ConstantsManager.getIntValueOrCrash("dimension");
		int pixelSpacing=ConstantsManager.getIntValueOrCrash("pixelSpacing");
		
		//PLAYER
		snakes=new Player[numberSnakes];
		
		//Vector
		int sqrt=(int) Math.sqrt(numberSnakes);
		double verticalDouble=((double)numberSnakes)/sqrt;
		int vertical=(int) verticalDouble;
		if(verticalDouble>vertical) vertical+=1;
		
		for(int player=0; player<snakes.length; player++) {
			//INITIALIZE
			snakes[player]=new Player();
			
			//POSITION
			double x=((double)dimension*pixelSpacing)/(sqrt+1)*(player%sqrt+1);
			double y=((double)dimension*pixelSpacing)/(vertical+1)*((int)(player/(double)sqrt)+1);
			snakes[player].add(new Vector2D(x, y));
			
			//DIRECTION
			Vector2D headDirection=new Vector2D(x-(dimension*pixelSpacing)/2, y-(dimension*pixelSpacing)/2);
			if(headDirection.getModule()==0) headDirection=new Vector2D(1, 0);
			headDirection.normalize();
			snakes[player].setHeadDirection(headDirection);
			
			
		}	
	}
	
	@Override public void clockUpdate(int millsPassed) {
		collideSnakes();
		moveSnakes((int)millsPassed); //TODO check conversion
	}
	public void moveSnakes(int deltaMills) {
		
		
		for(Player player :snakes) {
			if(player.isAlive() || player.isDyingAnimation()) {
				//ROTATION
				int rotation=(-(player.isLeftKeyPressing()?1:0)+(player.isRightKeyPressing()?1:0));
				if(rotation!=0)
					player.getHeadDirection().rotateRadiant(deltaMills/1000.0* ROTATION_POWER *rotation);
				
				//MOVEMENT
				Vector2D c=player.getFirstCoord();
				if(c==null) c=new Vector2D(0,0);
				
				Vector2D nextVect=new Vector2D(c);
				nextVect.sum(Vector2D.getMultiplied(player.getHeadDirection(), deltaMills/1000.0* SPEED)); //TODO velocità par.
				
				//when adding if out of frame -> die
				if(!player.add(nextVect)) {
					player.setAlive(false);
				}
			}
		}
		
	}
	public void collideSnakes() {
		//TODO SOLVE AUTO COLLISION
		
		for(Player playerKilled :snakes) {
			for(Player playerCollided :snakes) {
				//IF different and both alive (or dying 1)
				if(playerCollided!=playerKilled && playerKilled.isAlive() 
						&& (playerCollided.isAlive()||playerCollided.isDyingAnimation()) ) {
					//If colliding
					if(playerCollided.isCollidingWithCircle(playerKilled.getFirstCoord())) {
						playerKilled.setAlive(false);
					}
				}
			}
		}
	}
	
	public void start(int fps) {
		new Clock(this, 1000/fps).start();
	}
	
	//SNAKE POSITION INTERFACE  --------------------------------------
	@Override public PileOnlyReadable getPlayerNPositions(int nPlayer) {
		if(Vector2D.isOutSegment(nPlayer, 0, snakes.length)) {
			return null;
		}
			
		return snakes[nPlayer].getOnlyReadable();
		
	}
	@Override public int getNumPlayer() {
		return snakes.length;
	}
	@Override public boolean isAlive(int nPlayer) {
		return snakes[nPlayer].isAlive();
	}
	@Override public boolean isDying(int nPlayer) {
		boolean ris=snakes[nPlayer].isDyingAnimation();
		
		if(ris)snakes[nPlayer].advanceDyingAnimation();
		
		return ris;
	}
	
	//KEY GETTING  ---------------------------------------------------
	
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyPressed(KeyEvent e) {
		setPlayerMovementByKey(e.getKeyChar(), true);
	}
	@Override public void keyReleased(KeyEvent e) {
		setPlayerMovementByKey(e.getKeyChar(), false);
	}
	private void setPlayerMovementByKey(char key, boolean value) {
		int i=0; //for the name
		for(Player player :snakes) {
			if(player.isAlive() || player.isDyingAnimation()) {
				String left=ConstantsManager.getStringValue("playerLeft"+(i+1));
				String right=ConstantsManager.getStringValue("playerRight"+(i+1));
				
				if(left!=null && left.charAt(0)==key) {
					player.setLeftKeyPressing(value);
				}
				if(right!=null &&  right.charAt(0)==key) {
					player.setRightKeyPressing(value);
				}
			}
			i++;
		}
		
	}
	
	
	
}
