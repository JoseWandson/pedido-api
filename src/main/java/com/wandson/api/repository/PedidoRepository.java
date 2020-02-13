package com.wandson.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandson.api.model.Pedido;
import com.wandson.api.repository.pedido.PedidoRepositoryQuery;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryQuery {

	Optional<Pedido> findByNumeroControle(Long numeroControle);

}