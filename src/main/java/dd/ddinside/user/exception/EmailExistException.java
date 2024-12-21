package dd.ddinside.user.service;

public class EmailExistException extends RuntimeException
{
    public EmailExistException()
    {
        super();
    }

    public EmailExistException(String message)
    {
        super(message);
    }

    public EmailExistException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EmailExistException(Throwable cause)
    {
        super(cause);
    }
}
