package com.loja.loja.controle;

import com.loja.loja.dto.CategoriaDTO;
import com.loja.loja.dto.ImagemProdutoDTO;
import com.loja.loja.entidades.Categoria;
import com.loja.loja.entidades.ImagemProduto;
import com.loja.loja.service.CategoriaService;
import com.loja.loja.service.ImagemProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/imagemProduto")
public class ImagemProdutoController {

    @Autowired
    ImagemProdutoService imagemProdutoService;

    @GetMapping("{id}")
    public ResponseEntity<ImagemProdutoDTO> findById(@PathVariable Integer id) {
        ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO(imagemProdutoService.findById(id));
        return ResponseEntity.ok().body(imagemProdutoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ImagemProdutoDTO>> findAll() {
        List<ImagemProduto> list = imagemProdutoService.findAll();
        List<ImagemProdutoDTO> listDTO = new ArrayList<>();
        for (ImagemProduto img : list) {
            listDTO.add(new ImagemProdutoDTO(img));
        }
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ImagemProdutoDTO> create(@Valid @RequestParam Integer prodId, @RequestParam MultipartFile file) throws IOException {
        ImagemProduto newObj = imagemProdutoService.create(prodId,file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ImagemProdutoDTO(newObj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ImagemProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody ImagemProdutoDTO imagemProdutoDTO) {
        ImagemProdutoDTO newObj = new ImagemProdutoDTO(imagemProdutoService.update(id, imagemProdutoDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        imagemProdutoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
