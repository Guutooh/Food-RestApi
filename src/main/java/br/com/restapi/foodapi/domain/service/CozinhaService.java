package br.com.restapi.foodapi.domain.service;

import br.com.restapi.foodapi.domain.exception.CozinhaNaoEncontradaException;
import br.com.restapi.foodapi.domain.exception.EntidadeEmUsoException;
import br.com.restapi.foodapi.domain.model.Cozinha;
import br.com.restapi.foodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    public static final String Msg_Cozinha_Nao_Encontrada = "Não existe um cadastro de cozinha com código %d";
    public static final String Msg_Cozinha_Em_Uso = "cozinha de código %d não pode ser removido, pois está em uso";

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }


    public void excuir(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);

        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Msg_Cozinha_Em_Uso, cozinhaId));
        }
    }

    public Cozinha bucarOufalhar(long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

}
