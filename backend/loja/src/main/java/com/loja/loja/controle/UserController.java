package com.loja.loja.controle;

import com.loja.loja.dto.UserDTO;
import com.loja.loja.entidades.Usuario;
import com.loja.loja.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        UserDTO objDTO = new UserDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<Usuario> list = service.findAll();
        List<UserDTO> listDTO = new ArrayList<>();

        for (Usuario u : list) {
            listDTO.add(new UserDTO(u));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDTO) {
        Usuario newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDTO(newObj));
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id,@Valid @RequestBody UserDTO obj){
        obj = new UserDTO(service.update(obj));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
