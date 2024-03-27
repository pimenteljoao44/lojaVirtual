package com.loja.loja.service;

import com.loja.loja.dto.CategoriaDTO;
import com.loja.loja.dto.ProdutoDTO;
import com.loja.loja.entidades.Categoria;
import com.loja.loja.entidades.Produto;
import com.loja.loja.repository.ProdutoRepository;
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
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id:" + id + ",tipo: " + Produto.class.getName()));
    }

    @Transactional
    public Produto create(@Valid ProdutoDTO produtoDTO) {
        Produto existingProduto = produtoRepository.findBySimpleDescription(produtoDTO.getDescricaoCurta());
        if (existingProduto != null) {
            throw new DataIntegratyViolationException("Produto já cadastrado na base de dados!");
        }
        Produto newProduto = fromDTO(produtoDTO);
        newProduto.setDataCriacao(new Date());
        return produtoRepository.save(newProduto);
    }

    @Transactional
    public Produto update(Integer id,@Valid ProdutoDTO objDTO) {
        Produto produto = findById(id);
        produto.setAtivo(objDTO.getAtivo());
        Categoria categoria = categoriaService.findById(objDTO.getCategoria().getId());
        produto.setCategoria(categoria);
        produto.setAvaliacaoMedia(objDTO.getAvaliacaoMedia());
        produto.setEstoque(objDTO.getEstoque());
        produto.setTamanho(objDTO.getTamanho());
        produto.setDescricaoDetalhada(objDTO.getDescricaoDetalhada());
        produto.setDescricaoCurta(objDTO.getDescricaoCurta());
        produto.setQuantidade(objDTO.getQuantidade());
        produto.setDataAtualizacao(new Date());
        produto.setValorCusto(objDTO.getValorCusto());
        produto.setValorVenda(objDTO.getValorVenda());
        return produtoRepository.save(produto);
    }

    public void delete(Integer id) {
        Produto produto = findById(id);
        produtoRepository.deleteById(produto.getProdId());
    }

    public Produto fromDTO(ProdutoDTO objDTO) {
        Produto newProduto = new Produto();
        newProduto.setAtivo(objDTO.getAtivo());
        Categoria categoria = categoriaService.findById(objDTO.getCategoria().getId());
        newProduto.setCategoria(categoria);
        newProduto.setAvaliacaoMedia(objDTO.getAvaliacaoMedia());
        newProduto.setEstoque(objDTO.getEstoque());
        newProduto.setTamanho(objDTO.getTamanho());
        newProduto.setDescricaoDetalhada(objDTO.getDescricaoDetalhada());
        newProduto.setDescricaoCurta(objDTO.getDescricaoCurta());
        newProduto.setQuantidade(objDTO.getQuantidade());
        newProduto.setDataCriacao(new Date());
        newProduto.setDataAtualizacao(new Date());
        newProduto.setValorCusto(objDTO.getValorCusto());
        newProduto.setValorVenda(objDTO.getValorVenda());
        return newProduto;
    }
}
