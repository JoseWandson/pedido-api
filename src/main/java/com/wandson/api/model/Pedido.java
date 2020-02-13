package com.wandson.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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

	@NotNull(message = "Número controle é obrigatório")
	@Column(name = "numero_controle")
	private Long numeroControle;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@NotBlank(message = "Nome do produto é obrigatório")
	@Column(name = "nome_produto")
	private String nomeProduto;

	@NotNull(message = "Valor do produto é obrigatório")
	@Column(name = "valor_produto")
	private BigDecimal valorProduto;

	@Column(name = "quantidade_produto")
	@Min(value = 1, message = "Quantidade de produto deve ser maior ou igual a 1")
	private Integer quantidadeProduto;

	@NotNull(message = "Código do cliente é obrigatório")
	@Column(name = "codigo_cliente")
	private Long codigoCliente;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

}
