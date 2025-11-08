package hsf302.f25.s3.firstexam.service;

import hsf302.f25.s3.firstexam.entity.Product;
import hsf302.f25.s3.firstexam.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAllByOrderByCreatedAtDesc();
    }
}
