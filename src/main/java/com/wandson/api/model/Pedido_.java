package com.wandson.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Integer> quantidadeProduto;
	public static volatile SingularAttribute<Pedido, Long> codigoCliente;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorTotal;
	public static volatile SingularAttribute<Pedido, Long> numeroControle;
	public static volatile SingularAttribute<Pedido, Long> id;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorProduto;
	public static volatile SingularAttribute<Pedido, LocalDate> dataCadastro;
	public static volatile SingularAttribute<Pedido, String> nomeProduto;

	public static final String QUANTIDADE_PRODUTO = "quantidadeProduto";
	public static final String CODIGO_CLIENTE = "codigoCliente";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String NUMERO_CONTROLE = "numeroControle";
	public static final String ID = "id";
	public static final String VALOR_PRODUTO = "valorProduto";
	public static final String DATA_CADASTRO = "dataCadastro";
	public static final String NOME_PRODUTO = "nomeProduto";

}

