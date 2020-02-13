package com.wandson.api.repository.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wandson.api.model.Pedido;
import com.wandson.api.model.Pedido_;
import com.wandson.api.repository.filter.PedidoFilter;

public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);

		Predicate[] predicates = criarRestricoes(pedidoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Pedido> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(pedidoFilter));
	}

	private Predicate[] criarRestricoes(PedidoFilter pedidoFilter, CriteriaBuilder builder, Root<Pedido> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (Objects.nonNull(pedidoFilter.getNumeroControle())) {
			predicates.add(builder.equal(root.get(Pedido_.numeroControle), pedidoFilter.getNumeroControle()));
		}

		if (Objects.nonNull(pedidoFilter.getDataCadastroDe())) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Pedido_.dataCadastro), pedidoFilter.getDataCadastroDe()));
		}

		if (Objects.nonNull(pedidoFilter.getDataCadastroAte())) {
			predicates
					.add(builder.lessThanOrEqualTo(root.get(Pedido_.dataCadastro), pedidoFilter.getDataCadastroAte()));
		}

		if (Objects.nonNull(pedidoFilter.getCodigoCliente())) {
			predicates.add(builder.equal(root.get(Pedido_.codigoCliente), pedidoFilter.getCodigoCliente()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Pedido> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(PedidoFilter pedidoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pedido> root = criteria.from(Pedido.class);

		Predicate[] predicates = criarRestricoes(pedidoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}