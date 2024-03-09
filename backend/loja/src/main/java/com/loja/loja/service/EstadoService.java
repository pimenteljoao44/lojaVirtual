package com.loja.loja.service;

import com.loja.loja.dto.EstadoDTO;
import com.loja.loja.entidades.Estado;
import com.loja.loja.repository.EstadoRepository;
import com.loja.loja.service.exceptions.DataIntegratyViolationException;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    EstadoRepository estadoRepository;

    public Estado findById(Integer id) {
        Optional<Estado> obj = estadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id:" + id + " tipo:" +
                Estado.class.getName()));
    }

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado create(@Valid EstadoDTO estado) {
        if (estadoRepository.existsByNomeOrSigla(estado.getNome(), estado.getSigla())) {
            throw new DataIntegratyViolationException("Estado com o mesmo nome ou sigla já existe");
        }

        Estado newEstado = fromDTO(estado);
        newEstado.setDataCriacao(new Date());
        return estadoRepository.save(newEstado);
    }

    public Estado update(Integer id, @Valid EstadoDTO estadoDTO) {
        Estado estado = findById(id);
        estado.setNome(estadoDTO.getNome());
        estado.setSigla(estadoDTO.getSigla());
        estado.setDataAtualizacao(new Date());

        return estadoRepository.save(estado);
    }

    public void delete(Integer id) {
        Estado estado = findById(id);
        estadoRepository.deleteById(estado.getEstId());
    }

    public Estado fromDTO(EstadoDTO obj) {
        Estado newEstado = new Estado();
        newEstado.setNome(obj.getNome());
        newEstado.setSigla(obj.getSigla());
        newEstado.setDataCriacao(obj.getDataCriacao());
        newEstado.setDataAtualizacao(obj.getDataAtualizacao());
        return newEstado;
    }
}
