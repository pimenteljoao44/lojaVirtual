package com.loja.loja.service;


import com.loja.loja.dto.UserDTO;
import com.loja.loja.entidades.Usuario;
import com.loja.loja.entidades.enums.NivelAcesso;
import com.loja.loja.repository.UserRepository;
import com.loja.loja.service.exceptions.DataIntegratyViolationException;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private Usuario findByLogin(UserDTO objDTO) {
        Usuario obj = repository.findByLogin(objDTO.getLogin());
        if (obj != null) {
            return obj;
        }
        return null;
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado!"));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(UserDTO objDTO) {
        if (findByLogin(objDTO) != null) {
            throw new DataIntegratyViolationException("Usuário já cadastrado na base de dados!");
        }
        return fromDTO(objDTO);
    }

    public Usuario update(@Valid UserDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Usuario fromDTO(UserDTO obj) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(obj.getSenha());
        Usuario newObj = new Usuario();
        newObj.setId(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setLogin(obj.getLogin());
        newObj.setSenha(encryptedPassword);
        newObj.setNivelAcesso(NivelAcesso.toEnum(obj.getNivelAcesso().getCod()));

        return repository.save(newObj);
    }
}
