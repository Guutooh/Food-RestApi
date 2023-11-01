package br.com.restapi.foodapi.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable // classe parte de alguma outra entidade.
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;
    @Column(name = "endereco_logradouro")
    private String logradouro;
    @Column(name = "endereco_numero")
    private String numero;
    @Column(name = "endereco_complemento")
    private String complemento;
    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_cidade_id")
    Cidade cidade;
}
