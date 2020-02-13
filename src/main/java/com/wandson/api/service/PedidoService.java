package com.wandson.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wandson.api.model.Pedido;
import com.wandson.api.repository.PedidoRepository;
import com.wandson.api.repository.filter.PedidoFilter;
import com.wandson.api.service.exception.NumeroControleCadastradoException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Page<Pedido> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		return pedidoRepository.filtrar(pedidoFilter, pageable);
	}

	public List<Pedido> criar(List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			validarPedido(pedido);

			if (Objects.isNull(pedido.getDataCadastro())) {
				pedido.setDataCadastro(LocalDate.now());
			}
			if (Objects.isNull(pedido.getQuantidadeProduto())) {
				pedido.setQuantidadeProduto(1);
			}
			if (pedido.getQuantidadeProduto() > 5 && pedido.getQuantidadeProduto() < 10) {
				pedido.setValorTotal(calcularValorTotalComDesconto(pedido, 5));
			} else if (pedido.getQuantidadeProduto() >= 10) {
				pedido.setValorTotal(calcularValorTotalComDesconto(pedido, 10));
			} else {
				pedido.setValorTotal(calcularValorTotal(pedido));
			}
		}
		return pedidoRepository.saveAll(pedidos);
	}

	private BigDecimal calcularValorTotalComDesconto(Pedido pedido, Integer porcentagem) {
		BigDecimal valorTotalSemDesconto = calcularValorTotal(pedido);
		return valorTotalSemDesconto.subtract(
				valorTotalSemDesconto.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(porcentagem)));
	}

	private BigDecimal calcularValorTotal(Pedido pedido) {
		return pedido.getValorProduto().multiply(BigDecimal.valueOf(pedido.getQuantidadeProduto()));
	}

	private void validarPedido(Pedido pedido) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findByNumeroControle(pedido.getNumeroControle());
		if (pedidoOptional.isPresent()) {
			throw new NumeroControleCadastradoException();
		}
	}

}