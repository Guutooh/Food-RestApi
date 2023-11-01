package br.com.restapi.foodapi.api.controller;

import br.com.restapi.foodapi.domain.model.Cozinha;
import br.com.restapi.foodapi.domain.repository.CozinhaRepository;
import br.com.restapi.foodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping("/listar")
    public List<Cozinha> listar() {

        return cozinhaRepository.findAll();

    }

    @GetMapping("buscar/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {

       return cozinhaService.bucarOufalhar(cozinhaId);

    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha cadastrar(@Valid @RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }


    @PutMapping("atualizar/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = cozinhaService.bucarOufalhar(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaService.salvar(cozinhaAtual);

    }

    @DeleteMapping("deletar/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long cozinhaId) {

            cozinhaService.excuir(cozinhaId);
    }

}
