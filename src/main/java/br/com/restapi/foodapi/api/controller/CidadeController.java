package br.com.restapi.foodapi.api.controller;

import br.com.restapi.foodapi.domain.exception.EstadoNaoEncontradoException;
import br.com.restapi.foodapi.domain.exception.NegocioException;
import br.com.restapi.foodapi.domain.model.Cidade;
import br.com.restapi.foodapi.domain.repository.CidadeRepository;
import br.com.restapi.foodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CidadeService cidadeService;

    @GetMapping("/listar")
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }


    @GetMapping("buscar/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {

        return cidadeService.bucarOufalhar(cidadeId);

    }

    @PostMapping("/adicionar")
    public Cidade adicionar(@RequestBody Cidade cidade) {

        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("atualizar/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                            @RequestBody Cidade cidade) {

        Cidade cidadeAtual = cidadeService.bucarOufalhar(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return cidadeService.salvar(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("remover/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {

        cidadeService.excluir(cidadeId);
    }



}
