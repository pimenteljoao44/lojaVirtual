package com.loja.loja.service;
import com.loja.loja.dto.ImagemProdutoDTO;
import com.loja.loja.entidades.ImagemProduto;
import com.loja.loja.entidades.Produto;
import com.loja.loja.repository.ImagemProdutoRepository;
import com.loja.loja.repository.ProdutoRepository;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImagemProdutoServiceTest {

    @Mock
    private ImagemProdutoRepository imagemProdutoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private FirebaseStorageService firebaseStorageService;

    @InjectMocks
    private ImagemProdutoService imagemProdutoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<ImagemProduto> imagemProdutoList = new ArrayList<>();
        imagemProdutoList.add(new ImagemProduto());
        imagemProdutoList.add(new ImagemProduto());
        when(imagemProdutoRepository.findAll()).thenReturn(imagemProdutoList);

        List<ImagemProduto> result = imagemProdutoService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        ImagemProduto imagemProduto = new ImagemProduto();
        imagemProduto.setId(1);
        Optional<ImagemProduto> optional = Optional.of(imagemProduto);
        when(imagemProdutoRepository.findById(1)).thenReturn(optional);

        ImagemProduto result = imagemProdutoService.findById(1);

        assertEquals(1, result.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(imagemProdutoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> imagemProdutoService.findById(1));
    }


    @Test
    public void testUpdate() {
        ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
        imagemProdutoDTO.setNome("new_name");

        ImagemProduto imagemProduto = new ImagemProduto();
        imagemProduto.setId(1);
        when(imagemProdutoRepository.findById(1)).thenReturn(Optional.of(imagemProduto));
        when(imagemProdutoRepository.save(any(ImagemProduto.class))).thenReturn(imagemProduto);

        ImagemProduto result = imagemProdutoService.update(1, imagemProdutoDTO);

        assertNotNull(result);
        assertEquals("new_name", result.getNome());
    }

}
