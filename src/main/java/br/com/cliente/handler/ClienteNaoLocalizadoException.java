package br.com.cliente.handler;

public class ClienteNaoLocalizadoException extends RuntimeException {

    public ClienteNaoLocalizadoException() {
        super("Cliente não localizado!");
    }

    public ClienteNaoLocalizadoException(String message) {
        super(message);
    }

    public ClienteNaoLocalizadoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteNaoLocalizadoException(Throwable cause) {
        super(cause);
    }

}
