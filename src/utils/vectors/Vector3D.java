package utils.vectors;

public class Vector3D {
	//TODO dico che angolo è radianti
	private double x=0, y=0, z=0, module=0, angleX=0,  angleY=0, angleZ=0;
	
	public Vector3D(double x, double y, double z) {
		this.set(x, y, z);
	}
	public Vector3D(Vector3D vect) {
		this(vect.x, vect.y, vect.z);
	}
	//VECTORS OPERATION --------------------------------------------------
	public double distance(Vector3D vect) {
		return distance(vect.x, vect.y, vect.z);
	}
	public double distance(double x2, double y2, double z2) {
		return getModule(x2-x, y2-y, z2-z);
	}
	
	public void normalize() {
		double module=getModule();
		this.divide(module);
		
	}
	/*public void rotateRadiant(double angle) {
		double module=getModule();
		double angleTot=getAngleRandiant()+angle;
		
		
		double xNew=module*Math.cos(angleTot);
		double yNew=module*Math.sin(angleTot);
		
		set(xNew, yNew);
	}*/
	
	public void sum(Vector3D vect) {
		sum(vect.x, vect.y, vect.z);
	}
	public void sum(double x2, double y2, double z2) {
		this.set(this.x+x2, this.y+y2, this.z+z2);
	}
	public void difference(Vector3D vect) {
		difference(vect.x, vect.y, vect.z);
	}
	public void difference(double x2, double y2,double z2) {
		sum(-x2, -y2, -z2);
	}
	public void divide(double n) {
		multiply(1/n);
	}
	public void multiply(double n) {
		this.set(this.x*n, this.y*n, this.z*n);
	}
	
	public static Vector3D getSummed(Vector3D vect1, Vector3D vect2) {
		if(vect1==null || vect2==null) return null;
		
		Vector3D ris=new Vector3D(vect1);
		ris.sum(vect2);
		
		return ris;
	}
	public static Vector3D getDiffernce(Vector3D vect1, Vector3D vect2) {
		return getSummed(vect1, new Vector3D(-vect2.x, -vect2.y, -vect2.z));
	}
	public static Vector3D getDevided(Vector3D vect1, double n) {
		return getMultiplied(vect1, 1/n);
	}
	public static Vector3D getMultiplied(Vector3D vect, double n) {
		if(vect==null) return null;
		
		Vector3D ris=new Vector3D(vect);
		ris.multiply(n);
		
		return ris;
	}
	
	/*private static double getAngleRandiant(double x, double y) {
		//Cases with vertital line
		if(x==0){
			if(y>0) return Math.PI/2;
			else return -Math.PI/2;
		}
		
		double ris=Math.atan(y/x);
		if(x<0) ris=ris+Math.PI;
		
		return ris;
	}*/
	private static double getModule(double x, double y, double z) {
		//Pitagora
		return Math.sqrt(x*x+y*y+z*z);
	}
		
	//SETTER AND GETTER (+POLAR) -----------------------------------------
	public double getModule() {
		return module;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public double getAngleX() {
		return angleX;
	}
	public double getAngleY() {
		return angleY;
	}
	public double getAngleZ() {
		return angleZ;
	}
	public void setX(double x) {
		set(x, this.y, this.z);
	}
	public void setY(double y) {
		set(this.x, y, this.z);
	}
	public void setZ(double z) {
		set(this.x, this.y, z);
	}
	public void set(Vector3D v) {
		set(v.x, v.y, v.z);
	}
	public void set(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
		
		this.module=getModule(x, y, z);
		
		//Angle TODO CHECK PLS!!!!
		this.angleX=new Vector2D(z, y).getAngleRandiant();
		this.angleY=new Vector2D(x, z).getAngleRandiant(); //hotizontal
		this.angleZ=new Vector2D(x, y).getAngleRandiant(); //vertical
	}
	
	public void rotateAxisX(double angle) {
		Vector2D temp=new Vector2D(z, y);
		temp.rotateRadiant(angle);
		
		this.set(this.x, temp.getY(), temp.getX());
	}
	public void rotateAxisY(double angle) {
		Vector2D temp=new Vector2D(x, z);
		temp.rotateRadiant(angle);
		
		this.set(temp.getX(), this.y, temp.getY());
	}
	public void rotateAxisZ(double angle) {
		Vector2D temp=new Vector2D(x, y);
		temp.rotateRadiant(angle);
		
		this.set(temp.getX(), temp.getY(), this.z);
	}
	
	//TO-STRING
	public String toString() {
		return "x: "+x+"   y: "+y+"  z:"+z+"\n\t"
				+"module:"+module
				+"   angleX:"+angleX+"   angleX:"+angleY+"   angleX:"+angleZ;
	}
}
