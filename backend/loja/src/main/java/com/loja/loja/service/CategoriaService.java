package com.loja.loja.service;

import com.loja.loja.dto.CategoriaDTO;
import com.loja.loja.entidades.Categoria;
import com.loja.loja.entidades.Pessoa;
import com.loja.loja.repository.CategoriaRepository;
import com.loja.loja.service.exceptions.DataIntegratyViolationException;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id:" + id + ",tipo: " + Categoria.class.getName()));
    }

    public Categoria create(@Valid CategoriaDTO categoriaDTO) {
        Categoria existingCategoria = categoriaRepository.findByName(categoriaDTO.getNome());
        if (existingCategoria != null) {
            throw new DataIntegratyViolationException("Categoria já cadastrada na base de dados!");
        }
        Categoria newCategoria = fromDTO(categoriaDTO);
        newCategoria.setDataCriacao(new Date());
        return categoriaRepository.save(newCategoria);
    }

    public Categoria update(Integer id,@Valid CategoriaDTO categoriaDTO) {
        Categoria categoria = findById(id);
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDataAtualizacao(new Date());
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        Categoria categoria = findById(id);
        categoriaRepository.deleteById(categoria.getId());
    }


    public Categoria fromDTO(CategoriaDTO objDTO) {
        Categoria newCategoria = new Categoria();
        newCategoria.setNome(objDTO.getNome());
        newCategoria.setDataCriacao(new Date());
        newCategoria.setDataAtualizacao(new Date());
        return newCategoria;
    }
}
