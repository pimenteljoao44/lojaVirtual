package com.loja.loja.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.loja.loja.dto.ImagemProdutoDTO;
import com.loja.loja.entidades.ImagemProduto;
import com.loja.loja.entidades.Produto;
import com.loja.loja.repository.ImagemProdutoRepository;
import com.loja.loja.repository.ProdutoRepository;
import com.loja.loja.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemProdutoService {

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    public List<ImagemProduto> findAll() {
        return imagemProdutoRepository.findAll();
    }

    public ImagemProduto findById(Integer id) {
        Optional<ImagemProduto> obj = imagemProdutoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id:" + id + ",tipo: " + ImagemProduto.class.getName()));
    }

    public ImagemProduto create(Integer productId, MultipartFile file) throws IOException {
        Produto produto = produtoRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! id:" + productId));

        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo está vazio.");
        }

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        String imageUrl = uploadImageToFirebaseStorage(file.getBytes(), fileName, contentType);

        ImagemProduto obj = new ImagemProduto();
        obj.setProduto(produto);
        obj.setNome(fileName);
        obj.setUrl(imageUrl);
        obj.setDataCriacao(new Date());
        return imagemProdutoRepository.save(obj);
    }

    private String uploadImageToFirebaseStorage(byte[] fileBytes, String fileName, String contentType) throws IOException {
        Storage storage = firebaseStorageService.getStorage();

        BlobId blobId = BlobId.of(firebaseStorageService.getBucketName(), fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        Blob blob = storage.create(blobInfo, fileBytes);

        return blob.getMediaLink();
    }

    @Transactional
    public ImagemProduto update(Integer id, ImagemProdutoDTO objDTO) {
        ImagemProduto imgProduto = findById(id);
        imgProduto.setNome(objDTO.getNome());
        imgProduto.setDataAtualizacao(new Date());
        return imagemProdutoRepository.save(imgProduto);
    }

    public void delete(Integer id) {
        ImagemProduto imgProduto = findById(id);

        deleteImageFromFirebaseStorage(imgProduto.getUrl());

        imagemProdutoRepository.deleteById(imgProduto.getId());
    }

    private void deleteImageFromFirebaseStorage(String imageUrl) {
        String fileName = getImageFileNameFromUrl(imageUrl);

        Storage storage = firebaseStorageService.getStorage();

        BlobId blobId = BlobId.of(firebaseStorageService.getBucketName(), fileName);
        storage.delete(blobId);
    }

    private String getImageFileNameFromUrl(String imageUrl) {
        try {
            URI uri = new URI(imageUrl);
            String path = uri.getPath();
            return path != null ? path.substring(path.lastIndexOf('/') + 1) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ImagemProduto fromDTO(ImagemProdutoDTO imagemProdutoDTO) {
        ImagemProduto img = new ImagemProduto();
        img.setNome(imagemProdutoDTO.getNome());
        img.setDataCriacao(new Date());
        img.setDataAtualizacao(new Date());
        return img;
    }
}
