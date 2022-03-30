package utils.vectors;

import utils.piles.PileElement;

public class Vector2D implements PileElement {
	
	//COSTRUCTOR --------------------------------------------------------
	private double x=0, y=0, module=0, angle=0;
	public Vector2D(double x, double y) {
		this.set(x, y);
	}
	public Vector2D(Vector2D c) {
		this(c.x, c.y);
	}


	//VECTORS OPERATION --------------------------------------------------
	public double distance(Vector2D coord) {
		return distance(coord.x, coord.y);
	}
	public double distance(double x2, double y2) {
		return getModule(x2-x, y2-y);
	}
	
	public void normalize() {
		double module=getModule();
		this.divide(module);
		
	}
	public void rotateRadiant(double angle) {
		double module=getModule();
		double angleTot=getAngleRandiant()+angle;
		
		
		double xNew=module*Math.cos(angleTot);
		double yNew=module*Math.sin(angleTot);
		
		set(xNew, yNew);
	}
	
	public void sum(Vector2D vect) {
		sum(vect.x, vect.y);
	}
	public void sum(double xAdd, double yAdd) {
		this.set(x+xAdd, y+yAdd);
	}
	public void difference(Vector2D vect) {
		difference(vect.x, vect.y);
	}
	public void difference(double xRem, double yRem) {
		sum(-xRem, -yRem);
	}
	public void divide(double n) {
		multiply(1/n);
	}
	public void multiply(double n) {
		this.set(this.x*n, this.y*n);
	}
	
	public static Vector2D getSummed(Vector2D vect1, Vector2D vect2) {
		if(vect1==null || vect2==null) return null;
		
		Vector2D ris=new Vector2D(vect1);
		ris.sum(vect2);
		
		return ris;
	}
	public static Vector2D getDiffernce(Vector2D vect1, Vector2D vect2) {
		return getSummed(vect1, new Vector2D(-vect2.x, -vect2.y));
		//TODO MERDA 
	}
	public static Vector2D getDevided(Vector2D vect1, double n) {
		return getMultiplied(vect1, 1/n);
	}
	public static Vector2D getMultiplied(Vector2D vect, double n) {
		if(vect==null) return null;
		
		Vector2D ris=new Vector2D(vect);
		ris.multiply(n);
		
		return ris;
	}
	
	private static double getAngleRandiant(double x, double y) {
		//Cases with vertital line
		if(x==0){
			if(y>0) return Math.PI/2;
			else return -Math.PI/2;
		}
		
		double ris=Math.atan(y/x);
		if(x<0) ris=ris+Math.PI;
		
		return ris;
	}
	private static double getModule(double x, double y) {
		//Pitagora
		return Math.sqrt(x*x+y*y);
	}
	
	//SETTER AND GETTER (+POLAR) -----------------------------------------
	public double getAngleRandiant() {
		return angle;
	}
	public double getModule() {
		return module;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		set(x, this.y);
	}
	public void setY(double y) {
		set(this.x, y);
	}
	public void set(Vector2D v) {
		set(v.x, v.y);
	}
	public void set(double x, double y) {
		this.x=x;
		this.y=y;
		
		this.module=getModule(x, y);
		this.angle=getAngleRandiant(x, y);
	}
	
	
	//UTILITIES ----------------------------------------------------------
	//*Min included max excluded*/
	public boolean isOutSquare(double min, double max) {
		return isOutSquare(this.x, this.y, min, max);
	}
	public static boolean isOutSquare(double x, double y, double min, double max) {
		return isOutSegment(x, min, max) || isOutSegment(y, min, max);
	}
	public static boolean isOutSegment(double x, double min, double max) {
		if(x<min || x>=max) return true;
		
		return false;
	}
	

}
