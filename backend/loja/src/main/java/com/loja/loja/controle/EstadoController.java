package com.loja.loja.controle;

import com.loja.loja.dto.EstadoDTO;
import com.loja.loja.entidades.Estado;
import com.loja.loja.service.EstadoService;
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
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping("{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable Integer id) {
        EstadoDTO estadoDTO = new EstadoDTO(estadoService.findById(id));
        return ResponseEntity.ok().body(estadoDTO);
    }

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDTO = new ArrayList<>();
        for (Estado e : list) {
            listDTO.add(new EstadoDTO(e));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> create(@Valid @RequestBody EstadoDTO estadoDTO) {
        Estado newObj = estadoService.create(estadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getEstId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> update(@PathVariable Integer id, @Valid @RequestBody EstadoDTO estadoDTO) {
        EstadoDTO newObj = new EstadoDTO(estadoService.update(id, estadoDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
