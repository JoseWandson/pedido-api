package com.wandson.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wandson.api.exceptionhandler.MaxSizeConstraint;
import com.wandson.api.exceptionhandler.PedidoExceptionHandler.Erro;
import com.wandson.api.model.Pedido;
import com.wandson.api.repository.filter.PedidoFilter;
import com.wandson.api.service.PedidoService;
import com.wandson.api.service.exception.NumeroControleCadastradoException;

@Validated
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Page<Pedido> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		return pedidoService.pesquisar(pedidoFilter, pageable);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Pedido>> criar(
			@RequestBody @NotEmpty(message = "Pedidos não pode estar vazio") @MaxSizeConstraint List<@Valid Pedido> pedidos,
			HttpServletResponse response) {
		pedidoService.criar(pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidos);
	}

	@ExceptionHandler({ NumeroControleCadastradoException.class })
	public ResponseEntity<List<Erro>> handlePessoaInexistenteOuInativaException(NumeroControleCadastradoException ex) {
		String mensagemUsuario = messageSource.getMessage("pedido.numero-controle-cadastrado", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
