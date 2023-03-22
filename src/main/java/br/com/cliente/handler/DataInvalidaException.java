package br.com.cliente.handler;

public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException() {
        super("Data Invalida, o formato deve ser yyyy-MM-ddTHH:mm:ss!");
    }

    public DataInvalidaException(String message) {
        super(message);
    }

    public DataInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInvalidaException(Throwable cause) {
        super(cause);
    }

}
