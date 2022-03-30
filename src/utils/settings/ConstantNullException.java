package utils.settings;

public class ConstantNullException extends Exception {
	private static final long serialVersionUID = 3953248214766154048L;

	public ConstantNullException() {
		this("The Constant is null");
	}

	public ConstantNullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
