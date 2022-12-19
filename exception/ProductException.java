package exception;

/**
 * This class creates a custom exception for this program.
 * @author jordankrause
 *
 */
public class ProductException extends Exception
{
	private static final long serialVersionUID = 1L;
	/**
	 * Non-Default constructor to support ExceptionWrapping (to avoid losing some of the Stack Trace)
	 * @param e Source of the exception.
	 * @param msg Custom error of the exception
	 */
	public ProductException(Throwable e, String msg)
	{
		super(msg, e);
	}
}
