package br.com.restapi.foodapi.domain.repository;

import br.com.restapi.foodapi.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {


    List<Permissao> todas();

    Permissao porId(Long id);

    Permissao adicionar(Permissao permissao);

    void remover(Permissao permissao);
}
