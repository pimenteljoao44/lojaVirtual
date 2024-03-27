package com.loja.loja.service;

import com.loja.loja.entidades.Cidade;
import com.loja.loja.entidades.Endereco;
import com.loja.loja.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Transactional
    public Endereco createOrUpdate(Endereco endereco) {
        if (endereco.getEnderecoId() != null) {
            Endereco existingEndereco = enderecoRepository.getById(endereco.getEnderecoId());
            endereco.setDataCriacao(existingEndereco.getDataCriacao());
        }
        Cidade cidade = cidadeService.findById(endereco.getCidade().getCidId());
        endereco.setCidade(cidade);
        endereco.setDataAtualizacao(new Date());
        return enderecoRepository.save(endereco);
    }
}
