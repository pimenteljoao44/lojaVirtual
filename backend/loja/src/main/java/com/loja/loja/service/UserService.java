package com.loja.loja.service;


import com.loja.loja.dto.EnderecoDTO;
import com.loja.loja.dto.PessoaDTO;
import com.loja.loja.dto.UserDTO;
import com.loja.loja.entidades.Cidade;
import com.loja.loja.entidades.Endereco;
import com.loja.loja.entidades.Pessoa;
import com.loja.loja.entidades.Usuario;
import com.loja.loja.entidades.enums.NivelAcesso;
import com.loja.loja.repository.CidadeRepository;
import com.loja.loja.repository.PessoaRepository;
import com.loja.loja.repository.UserRepository;
import com.loja.loja.service.exceptions.DataIntegratyViolationException;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EmailService emailService;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado!"));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(UserDTO objDTO) {
        if (findByLogin(objDTO.getLogin()) != null) {
            throw new DataIntegratyViolationException("Usuário já cadastrado na base de dados!");
        }
        Pessoa pessoa = createPessoaFromDTO(objDTO);
        pessoa = pessoaRepository.save(pessoa);

        Usuario usuario = new Usuario(objDTO);
        usuario.setPessoa(pessoa);
        emailService.enviarEmailTexto(usuario.getPessoa().getEmail(),"Cadastro na loja da silvana",
                "O registro na loja foi realizado com sucesso!");
        return repository.save(usuario);
    }

    public Usuario update(UserDTO objDTO) {
        Usuario oldObj = findById(objDTO.getId());
        oldObj.setLogin(objDTO.getLogin());
        oldObj.setNome(objDTO.getNome());
        oldObj.setSenha(objDTO.getSenha());
        oldObj.setNivelAcesso(objDTO.getNivelAcesso());

        Pessoa pessoa = oldObj.getPessoa();
        if (pessoa == null) {
            throw new ObjectNotFoundException("Pessoa não encontrada para este usuário!");
        }

        pessoa.setNome(objDTO.getNome());
        pessoa.setTelefone(objDTO.getPessoa().getTelefone());
        pessoa.setCpf(objDTO.getPessoa().getCpf());
        pessoa.setEmail(objDTO.getPessoa().getEmail());

        if (objDTO.getPessoa().getEndereco() != null) {
            Endereco endereco = pessoa.getEndereco();
            if (endereco == null) {
                endereco = new Endereco();
                pessoa.setEndereco(endereco);
            }
            updateEndereco(endereco, new EnderecoDTO(objDTO.getPessoa().getEndereco()));
        }

        return repository.save(oldObj);
    }

    private void updateEndereco(Endereco endereco, EnderecoDTO enderecoDTO) {
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());

        if (enderecoDTO.getCidade() != null) {
            Cidade cidade = cidadeRepository.findById(enderecoDTO.getCidade().getCidId())
                    .orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada!"));
            endereco.setCidade(cidade);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


    public Pessoa createPessoaFromDTO(UserDTO objDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(objDTO.getNome());
        pessoa.setTelefone(objDTO.getPessoa().getTelefone());
        pessoa.setEmail(objDTO.getPessoa().getEmail());
        pessoa.setCpf(objDTO.getPessoa().getCpf());
        Hibernate.initialize(objDTO.getPessoa().getEndereco().getCidade());

        Endereco endereco = new Endereco();
        endereco.setRua(objDTO.getPessoa().getEndereco().getRua());
        endereco.setNumero(objDTO.getPessoa().getEndereco().getNumero());
        endereco.setComplemento(objDTO.getPessoa().getEndereco().getComplemento());
        endereco.setBairro(objDTO.getPessoa().getEndereco().getBairro());
        endereco.setCidade(objDTO.getPessoa().getEndereco().getCidade());
        endereco.setPessoa(pessoa);

        pessoa.setEndereco(endereco);

        return pessoa;
    }

    private Usuario findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
