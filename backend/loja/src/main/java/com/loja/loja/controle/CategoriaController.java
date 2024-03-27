package com.loja.loja.controle;

import com.loja.loja.dto.CategoriaDTO;
import com.loja.loja.entidades.Categoria;
import com.loja.loja.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaService.findById(id));
        return ResponseEntity.ok().body(categoriaDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDTO = new ArrayList<>();
        for (Categoria c : list) {
            listDTO.add(new CategoriaDTO(c));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria newObj = categoriaService.create(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDTO(newObj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO newObj = new CategoriaDTO(categoriaService.update(id, categoriaDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
