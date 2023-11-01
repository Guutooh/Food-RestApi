package br.com.restapi.foodapi.domain.service;

import br.com.restapi.foodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.restapi.foodapi.domain.model.Cozinha;
import br.com.restapi.foodapi.domain.model.Restaurante;
import br.com.restapi.foodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.bucarOufalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }


    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
    }


}
