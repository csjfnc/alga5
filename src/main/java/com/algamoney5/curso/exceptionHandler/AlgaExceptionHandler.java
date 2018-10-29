package com.algamoney5.curso.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgaExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	//TRATE ERROS DE REQUISIÇÃO USUARIO INSERIU ATRIBUTOS A MAIS 
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		String menagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		return handleExceptionInternal(ex, new Erro(menagemUsuario, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	//TRATA ERROS DE NULLO
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request){
		List<Erro> erros = criarListadeErros(ex.getBindingResult());		
		return handleExceptionInternal(ex, erros, headers, httpStatus, request);
	}

	//RECEBE UM PACOTE DE ERROS E CRIA UMA LISTA
	public List<Erro> criarListadeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		for(FieldError error : bindingResult.getFieldErrors()){
			String menagemUsuario = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = error.toString();
			erros.add(new Erro(menagemUsuario, mensagemDesenvolvedor));			
		}
		return erros;
	}
	
	public class Erro{
		
		private String mensagemErroUsuario;
		private String mensagemErroDesenvolvedor;
		
		public Erro(String mensagemErroUsuario, String mensagemErroDesenvolvedor){
			this.mensagemErroUsuario = mensagemErroUsuario;
			this.mensagemErroDesenvolvedor = mensagemErroDesenvolvedor;
		}

		public String getMensagemErroUsuario() {
			return mensagemErroUsuario;
		}

		public void setMensagemErroUsuario(String mensagemErroUsuario) {
			this.mensagemErroUsuario = mensagemErroUsuario;
		}

		public String getMensagemErroDesenvolvedor() {
			return mensagemErroDesenvolvedor;
		}

		public void setMensagemErroDesenvolvedor(String mensagemErroDesenvolvedor) {
			this.mensagemErroDesenvolvedor = mensagemErroDesenvolvedor;
		}
		
		
	}
}
