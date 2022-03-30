package frontend;

import java.awt.*;
import backend.GameManager;
import interfaces.SnakesPositions;
import templetes.canvas.*;
import utils.piles.PileOnlyReadable;
import utils.settings.ConstantsManager;
import utils.vectors.Vector2D;

public class App implements CanvasRepaintListener{
	
	public static void main(String[] args) {
		ConstantsManager.addDefaultConstantsThenReadFromFile(
			("numberSnakes:4:int  _  dimension:100:int  _ pixelSpacing:10:int _"
			+ "fps:60:int _ cps:120:int _  maxLengthSnake:100:int  _  stepDyingAnimation:2:int  _"
			+ "rotationPower:8:int  _  speed:300:int  _  playerLeft1:a:String  _  playerRight1:d:String _"
			+ "playerLeft2:j:String  _   playerRight2:l:String   _  playerLeft3:4:String  _"
			+ "playerRight3:6:String  _  playerLeft4:+:String  _  playerRight4:-:String _"
			+ "backgroundColor:f8f5ac:String  _  playerColor1:82f4a5:String _"
			+ "playerColor2:adeff3:String _  playerColor3:c8adf3:String  _"
			+ "playerColor4:f8acda:String")
			.split("_"));
		ConstantsManager.debugPrintValue();
		
		new App();
	}
	
	public App() {
		
		//FRAME
		CanvasFrame canvas=new CanvasFrame(this);
		canvas.getFrame().setResizable(false);
		
		//SNAKE
		GameManager snakes=new GameManager();
		canvas.getFrame().addKeyListener(snakes);
		
		//PANEL GAME
		this.snakesPositions=snakes;
		this.PIXEL_SPACING=ConstantsManager.getIntValueOrCrash("pixelSpacing");
		this.DIMENSION=ConstantsManager.getIntValueOrCrash("dimension");
		
		this.SIDE=PIXEL_SPACING*DIMENSION;
		canvas.setPreferredSize(new Dimension(SIDE, SIDE));
		
		//StartClock and setvisible
		canvas.setVisible(true);
		canvas.start(ConstantsManager.getIntValueOrCrash("fps"));
		snakes.start(ConstantsManager.getIntValueOrCrash("cps"));
	}
	
	private final int PIXEL_SPACING, DIMENSION, SIDE;
	private SnakesPositions snakesPositions=null;
	@Override public void paintComponent(Graphics2D graphics) {
		PileOnlyReadable positions=null;
		Vector2D coord=null;
		
		//BACKGROUND
		setColorByCostant("backgroundColor", graphics);
		graphics.fillRect(0, 0, SIDE, SIDE);
		
		for(int player=0; player<snakesPositions.getNumPlayer(); player++) {
			boolean isDying=snakesPositions.isDying(player);
			if(snakesPositions.isAlive(player)||isDying) {
				//POSITION
				positions=snakesPositions.getPlayerNPositions(player);
				
				//COORD
				if(positions!=null) {
					positions.restartPileOnlyReadable(); //NEEDED for restarting index
					coord = (Vector2D) positions.next();
				}
				else coord=null;
				
				//COLOR
				if(isDying) graphics.setColor(Color.red);
				else setColorByCostant("playerColor"+(player+1), graphics);
				
				//PRINT
				while(coord!=null) {
					graphics.fillOval((int)(coord.getX()-PIXEL_SPACING/2), (int)(coord.getY()-PIXEL_SPACING/2), 
							PIXEL_SPACING, PIXEL_SPACING);
					
					coord = (Vector2D) positions.next();
				}
			}	
		}
	}
	/**Green is DefaultColor*/
	private void setColorByCostant(String key, Graphics2D graphics) {
		String colorExa=ConstantsManager.getStringValue(key);
		
		
		graphics.setColor(Color.green); 
		if(colorExa!=null){
			graphics.setColor(Color.decode("#"+colorExa));
		}
	}
}

/*TODO RIASSUNTO:
put fucking important file in 000-JavaAlwaysUsed (anche altri progetti)
costanti
*/
