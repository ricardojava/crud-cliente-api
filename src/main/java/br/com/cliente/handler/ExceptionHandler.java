package br.com.cliente.handler;

import br.com.cliente.dto.Error;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.UnexpectedTypeException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(CpfDuplicadoException.class)
    public Error cpfDuplHandlerException(CpfDuplicadoException ex) {
        return Error.of(CONFLICT.value(),ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(UnexpectedTypeException.class)
    public Error unexpectedTypeException(UnexpectedTypeException ex) {
        return Error.of(BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new Error(BAD_REQUEST.value(), "Um ou mais campos estão invalidos!", ex.getBindingResult().getAllErrors());
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(DataInvalidaException.class)
    public Error dtInvalidaHandlerException(DataInvalidaException ex) {
        return Error.of(BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(NO_CONTENT)
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(ClienteNaoLocalizadoException.class)
    public Error clienteNaoLocalizadoHandlerException(ClienteNaoLocalizadoException ex) {
        return Error.of(NO_CONTENT.value(), ex.getMessage());
    }

}
