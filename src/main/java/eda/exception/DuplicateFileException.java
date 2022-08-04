package eda.exception;

public class DuplicateFileException extends RuntimeException{
	public DuplicateFileException(String message) {
		super(message);
	}
	
	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}
}
