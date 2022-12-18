package bessa.morangon.rafael.clinica.configuration.erros;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorException {
    
    @Autowired
    private ModelMapper mapper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<?>> tratadorMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<Erro> erros = fieldErrors.stream().map(field -> mapper.map(field, Erro.class)).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler( SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> tratadorSQLIntegrityConstraintViolationException( SQLIntegrityConstraintViolationException e){
        String mensagem = e.getMessage();
        Erro erro = new Erro();
        erro.setDefaultMessage(mensagem);
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> tratadorHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        String mensagem = e.getMessage();
        Erro erro = new Erro();
        erro.setDefaultMessage(mensagem);
        return ResponseEntity.badRequest().body(erro);
    }



}
