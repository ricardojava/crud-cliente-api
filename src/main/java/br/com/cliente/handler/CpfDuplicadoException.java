package br.com.cliente.handler;

public class CpfDuplicadoException extends RuntimeException {

    public CpfDuplicadoException() {
        super("CPF Ja foi cadastrado!");
    }

    public CpfDuplicadoException(String message) {
        super(message);
    }

    public CpfDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfDuplicadoException(Throwable cause) {
        super(cause);
    }

}
