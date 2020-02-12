package com.wandson.pedido.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "pedido")
public class Pedido {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "numero_controle")
	private Long numeroControle;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@NotBlank
	@Column(name = "nome_produto")
	private String nomeProduto;

	@NotNull
	@Column(name = "valor_produto")
	private BigDecimal valorProduto;

	@Column(name = "quantidade_produto")
	private Integer quantidadeProduto;

	@NotNull
	@Column(name = "codigo_cliente")
	private Long codigoCliente;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

}
