package com.loja.loja.controle;

import com.loja.loja.dto.ProdutoDTO;
import com.loja.loja.entidades.Produto;
import com.loja.loja.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
        ProdutoDTO produtoDTO = new ProdutoDTO(produtoService.findById(id));
        return ResponseEntity.ok().body(produtoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> list = produtoService.findAll();
        List<ProdutoDTO> listDTO = new ArrayList<>();
        for (Produto p : list) {
            listDTO.add(new ProdutoDTO(p));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto newObj = produtoService.create(produtoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getProdId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO(newObj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO newObj = new ProdutoDTO(produtoService.update(id, produtoDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
