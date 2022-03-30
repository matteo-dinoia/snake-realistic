package utils.settings;

public class Constant {
	
	public static Constant newConstant(String s) {
		String command=s.split("#")[0];
		
		String[] fields =command.split(":");
		if(fields.length!=3) return null;
		
		return newConstant(fields[0], fields[1], fields[2]);
	}
	public static Constant newConstant(String name, String value, String type) {
		try {
			return new Constant(name, value, type);
		} catch (ConstantNullException e) {}
		
		return null;
	}
	
	
	private Constant(String name, String value, String type) throws ConstantNullException{
		if(name==null || value==null || type==null ) throw new ConstantNullException();
		
		this.name=lrTrim(name);
		this.value=lrTrim(value);
		this.type=lrTrim(type);
	}

	//GETTER AND TO_STRING ----------------------------------------------
	private String name, value, type;
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return "Name: \""+name+"\", Value: \""+value+"\", Type: \""+type+"\".";
	}

	public String getStringValue() {
		return value;
	}
	public Integer getIntegerValue() {
		return Integer.valueOf(value);
	}
	public Boolean getBooleanValue() {
		return Boolean.valueOf(value);
	}
	
	//UTILS
	private static String lrTrim(String str) {
		String temp=str.replaceAll("^\\s+", "");  //Left trim
        return temp.replaceAll("\\s+$", "");     //Rigth trim
    }
}
