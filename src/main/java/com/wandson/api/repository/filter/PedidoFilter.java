package com.wandson.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilter {

	private Long numeroControle;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastroDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastroAte;

	private Long codigoCliente;

}