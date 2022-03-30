package utils.settings;

import java.io.*;

public class ConstantsManager {

	//"SINGLETON"  ------------------------------------------------------------------
	private ConstantsManager() {}
	private static ConstantsData constants=new ConstantsData();
	public static ConstantsData getCostantsData() {
		return constants;
	}
	/**Need to be different from Null
	 * if null does nothing*/
	public static void setNotNullCostantsData(ConstantsData constantsData) {
		if(constantsData==null) return;
		constants=constantsData;
	}
	
	
	//ADDING AND GETTING ------------------------------------------------------------
	private static void readConstantsFromFile() {
		
		//OPEN FILE
		BufferedReader reader = getBufferedReader("Costants.txt");
		if(reader==null) return;
		
		//ADD ROW BY ROW
		String s=nextLine(reader);
		while(s!=null) {
			constants.addNotNull(Constant.newConstant(s));
			
			s=nextLine(reader);
		}
		
	}
	private static void addDefaultConstants(String[] constantsStings) {
		if(constantsStings==null) return;
		//ADD ROW BY ROW
		for(String s:constantsStings) {
			constants.addNotNull(Constant.newConstant(s));
		}
	}
	public static void addDefaultConstantsThenReadFromFile(String[] constantsStings) {
		addDefaultConstants(constantsStings);
		readConstantsFromFile();
	}
	
	
	public static String getStringValue(String key) {
		Constant c=constants.get(key);
		if(c==null) return null;
		
		return c.getStringValue();
	}
	public static Integer getIntegerValue(String key) {
		Constant c=constants.get(key);
		if(c==null) return null;
		
		return c.getIntegerValue();
	}
	public static Boolean getBooleanValue(String key) {
		Constant c=constants.get(key);
		if(c==null) return null;
		
		return c.getBooleanValue();
	}
	
	public static int getIntValueOrCrash(String key) {
		Integer n=getIntegerValue(key);
		
		if(n==null) {
			System.err.println("Conversion error in class: ConstantManager, while parsing: int\n"
					+ "Error with key: \""+key+"\"\n");
			
			System.exit(-23);
		}
		return n;
	}
	public static boolean getBoolValueOrCrash(String key) {
		Boolean n=getBooleanValue(key);
		
		if(n==null) {
			System.err.println("Conversion error in class: ConstantManager, while parsing: int\n"
					+ "Error with key: \""+key+"\"\n");
			
			System.exit(-23);
		}
		return n;
	}
	
	
	//FILE READER UTILS (TODO MAYBE DO ITS CLASS)  ------------------------------------
	private static BufferedReader getBufferedReader(String path) {
		File file=new File(path);
		if (file.exists()) {
			return getBufferedReaderByFile(file);
		}
		else{
			try {
				if (!file.createNewFile()) {
					System.out.println("Il file " + path + " non può essere creato");
				}
				else System.out.println("Il file " + path + " è stato creato");
			} catch (IOException e) {}	
			
			
		}
		return null;
		
	}
	private static BufferedReader getBufferedReaderByFile(File file) {
		BufferedReader reader;
		try {
			 reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			return null;
		}
		
		return reader;
	}
	private static String nextLine(BufferedReader reader) {
		String ris=null;
		try {
			ris = reader.readLine();
		} catch (IOException e) {}
		
		return ris;
	}

	//UTILS ALTRO  -------------------------------------------------------------------
	public static void debugPrintValue() {
		System.out.println(""+constants);
	}
}
