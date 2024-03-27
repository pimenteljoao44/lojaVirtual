package com.loja.loja.controle;

import com.loja.loja.dto.PessoaDTO;
import com.loja.loja.entidades.Pessoa;
import com.loja.loja.service.PessoaService;
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
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @GetMapping(value="{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Integer id){

        PessoaDTO objDTO = new PessoaDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll(){
        List<Pessoa> list = service.findAll();
        List<PessoaDTO> listDTO = new ArrayList<>();

        for(Pessoa p : list) {
            listDTO.add(new PessoaDTO(p));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO>  creaate(@Valid @RequestBody PessoaDTO objDTO){
        Pessoa newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaDTO(newObj));
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Integer id,@Valid @RequestBody PessoaDTO objDTO){
        PessoaDTO newObj = new PessoaDTO(service.update(id,objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}