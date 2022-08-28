package kodlamaio.northwind.business.core.utilities.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	public BusinessException(String message) {
		super(message);
	}
}
