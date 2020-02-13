package com.wandson.api.repository.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wandson.api.model.Pedido;
import com.wandson.api.repository.filter.PedidoFilter;

public interface PedidoRepositoryQuery {

	Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable);

}