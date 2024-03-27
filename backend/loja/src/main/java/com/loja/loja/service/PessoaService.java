package com.loja.loja.service;

import com.loja.loja.dto.PessoaDTO;
import com.loja.loja.entidades.Endereco;
import com.loja.loja.entidades.Pessoa;
import com.loja.loja.repository.EnderecoRepository;
import com.loja.loja.repository.PessoaRepository;
import com.loja.loja.service.exceptions.DataIntegratyViolationException;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa findById(Integer id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id:" + id + ",tipo: " + Pessoa.class.getName()));
    }

    @Transactional
    public Pessoa create(PessoaDTO objDTO) {
        if (pessoaRepository.findByCPF(objDTO.getCpf()) != null) {
            throw new DataIntegratyViolationException("Pessoa já cadastrada na base de dados!");
        }
        Pessoa pessoa = new Pessoa();
        populatePessoa(pessoa, objDTO);
        pessoa.setDataCriacao(new Date());
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa update(Integer id, PessoaDTO objDTO) {
        Pessoa pessoa = findById(id);
        populatePessoa(pessoa, objDTO);
        return pessoaRepository.save(pessoa);
    }

    public void delete(Integer id) {
        pessoaRepository.deleteById(id);
    }

    private void populatePessoa(Pessoa pessoa, PessoaDTO objDTO) {
        pessoa.setNome(objDTO.getNome());
        pessoa.setTelefone(objDTO.getTelefone());
        pessoa.setCpf(objDTO.getCpf());
        pessoa.setEmail(objDTO.getEmail());

        pessoa.setEndereco(enderecoService.createOrUpdate(objDTO.getEndereco()));
    }
}