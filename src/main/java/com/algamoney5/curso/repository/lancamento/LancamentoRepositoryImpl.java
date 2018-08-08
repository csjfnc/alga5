package com.algamoney5.curso.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.util.StringUtils;

import com.algamoney5.curso.model.Categoria_;
import com.algamoney5.curso.model.Lancamento;
import com.algamoney5.curso.model.Lancamento_;
import com.algamoney5.curso.model.Pessoa_;
import com.algamoney5.curso.repository.filter.LancamentoFilter;
import com.algamoney5.curso.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteriaQuery = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

		criteriaQuery.select(builder.construct(ResumoLancamento.class 
				  				, root.get(Lancamento_.codigo), root.get(Lancamento_.descricao) 
				  				, root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento) 
				  				, root.get(Lancamento_.valor), root.get(Lancamento_.tipo) 
				  				, root.get(Lancamento_.categoria).get(Categoria_.descricao) 
				  				, root.get(Lancamento_.pessoa).get(Pessoa_.nome))); 


		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteriaQuery.where(predicates);

		TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteriaQuery);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));

	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
					"%" + filter.getDescricao().toLowerCase() + "%"));
		}

		if (filter.getDataVencimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoDe()));
		}

		if (filter.getDataVencimentoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
