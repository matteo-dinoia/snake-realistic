package backend;

import utils.settings.ConstantsManager;
import utils.vectors.Vector2D;

public class Player extends Chunked2DMap{
	
	//GETTER AND SETTER  -------------------------------------------------------
	private Vector2D headDirection;
	private boolean alive=true;
	private boolean isDyingAnimation=false;
	public Vector2D getHeadDirection() {
		return headDirection;
	}
	public void setHeadDirection(Vector2D headDirection) {
		this.headDirection = headDirection;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		if(!alive) startDyingAnimation();
		else endDyingAnimation();
		this.alive = alive;
	}
	
	
	//DYING ANIMATION  ---------------------------------------------------------
	private int stepAnimation=0;
	private static final int MAX_STEP_ANIMATION=ConstantsManager.getIntValueOrCrash("stepDyingAnimation");
	public boolean isDyingAnimation() {
		return isDyingAnimation;
	}
	public void advanceDyingAnimation() {
		stepAnimation++;
		if(stepAnimation>=MAX_STEP_ANIMATION) endDyingAnimation();
	}
	private void endDyingAnimation() {
		this.isDyingAnimation=false;
		stepAnimation=0;
	}
	private void startDyingAnimation() {
		this.isDyingAnimation = true;
	}
	
	
	//IS PRESSING KEY  --------------------------------------------------------
	private boolean leftKeyPressing;
	private boolean rightKeyPressing;
	public boolean isLeftKeyPressing() {
		return leftKeyPressing;
	}
	public void setLeftKeyPressing(boolean leftKeyPressing) {
		this.leftKeyPressing = leftKeyPressing;
	}
	public boolean isRightKeyPressing() {
		return rightKeyPressing;
	}
	public void setRightKeyPressing(boolean rightKeyPressing) {
		this.rightKeyPressing = rightKeyPressing;
	}
}
