package br.com.restapi.foodapi.domain.repository;

import br.com.restapi.foodapi.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {


    List<FormaPagamento> todas();

    FormaPagamento porId(Long id);

    FormaPagamento adicionar(FormaPagamento formaPagamento);

    void remover(FormaPagamento formaPagamento);
}
