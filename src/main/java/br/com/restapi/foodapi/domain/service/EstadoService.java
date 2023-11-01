package br.com.restapi.foodapi.domain.service;

import br.com.restapi.foodapi.domain.exception.EntidadeEmUsoException;
import br.com.restapi.foodapi.domain.exception.EstadoNaoEncontradoException;
import br.com.restapi.foodapi.domain.model.Estado;
import br.com.restapi.foodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    public static final String Msg_Estado_Em_Uso = "Estado de código %d não pode ser removido, pois está em uso";
    @Autowired
    EstadoRepository estadoRepository;



    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Msg_Estado_Em_Uso, estadoId));
        }
    }

    public Estado bucarOufalhar(long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }


}