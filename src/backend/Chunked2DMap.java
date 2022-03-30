package backend;

import java.util.ArrayList;

import utils.piles.*;
import utils.settings.ConstantsManager;
import utils.vectors.Vector2D;

public class Chunked2DMap {
	private Pile allNode=new Pile();
	private ArrayList<Vector2D>[][] nodeByCoord;
	private final int PIXEL_SPACING=ConstantsManager.getIntValueOrCrash("pixelSpacing"),
			DIMENSION=ConstantsManager.getIntValueOrCrash("dimension"), 
			MAX_LENGTH=ConstantsManager.getIntValueOrCrash("maxLengthSnake");
	
	//MAP  --------------------------------------------------
	public Chunked2DMap() {
		initializeMap();
	}
	@SuppressWarnings("unchecked")
	private void initializeMap() {
		
		nodeByCoord=(ArrayList<Vector2D>[][])new ArrayList[DIMENSION][DIMENSION];
		
		for(int i=0; i<DIMENSION; i++) {
			for(int i2=0; i2<DIMENSION; i2++) {
				nodeByCoord[i][i2]=new ArrayList<Vector2D>();
			}
		}
	}
	
	
	//ADD and REMOVE (+autoremove)  -------------------------
	public boolean add(Vector2D coord) {
		if(coord.isOutSquare(0, DIMENSION*PIXEL_SPACING)) return false;
		
		allNode.push(coord);
		addInMap(coord);
		autoRemoveLast();
		return true;
	}
	private void addInMap(Vector2D coord) {
		int x = (int) (coord.getX() / PIXEL_SPACING);
		int y = (int) (coord.getY() / PIXEL_SPACING);
		
		nodeByCoord[x][y].add(coord);
	}
	
	public void deleteLast() {
		Vector2D c=(Vector2D) allNode.pop();
		removeFromMap(c);
	}
	private void removeFromMap(Vector2D coord) {
		if(coord==null)  return;
		
		//REMOVE DA MAP
		int x = (int) (coord.getX() / PIXEL_SPACING);
		int y = (int) (coord.getY() / PIXEL_SPACING);
		nodeByCoord[x][y].remove(coord);
	}
	
	private void autoRemoveLast() {
		//AMOUNT TO REMOVE
		int toRemove=allNode.getSize()-MAX_LENGTH;
		if(toRemove<=0)return;
		
		//REMOVE
		for(int i=0; i<toRemove; i++) {
			this.deleteLast();
		}
			
	}
	
	
	//PHYSICS -------------------------------------------------
	public boolean isCollidingWithCircle(Vector2D coord) {
		int xMedio = (int) (coord.getX() / PIXEL_SPACING);
		int yMedio = (int) (coord.getY() / PIXEL_SPACING);
		
		for(int x=xMedio-1; x<=xMedio+1; x++) {
			for(int y=yMedio-1; y<=yMedio+1; y++) {
				if(!Vector2D.isOutSquare(x, y, 0, DIMENSION)) { //exist place
					for(Vector2D t : nodeByCoord[x][y]) {
						
						if(t.distance(coord)<PIXEL_SPACING) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	public PileOnlyReadable getOnlyReadable() {
		return allNode;
	}
	public Vector2D getFirstCoord() {
		return (Vector2D) allNode.getFirst();
	}
	
}
