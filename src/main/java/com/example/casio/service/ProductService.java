package com.example.casio.service;

import com.example.casio.dto.request.ProductRequestDto;
import com.example.casio.dto.response.ProductResponseDto;
import com.example.casio.mapper.ProductMapper;
import com.example.casio.model.Product;
import com.example.casio.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        final Product user = productMapper.toModel(productRequest);
        final Product savedUser = productRepository.save(user);
        return productMapper.toResponseDto(savedUser);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        final var product = productRepository.findAll();
        return product.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return productMapper.toResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        existingProduct.setName(productRequest.name());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setPrice(productRequest.price());
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toResponseDto(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        productRepository.deleteById(id);
    }
}
