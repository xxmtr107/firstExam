package hsf302.f25.s3.firstexam.service;

import hsf302.f25.s3.firstexam.entity.Category;
import hsf302.f25.s3.firstexam.entity.Product;
import hsf302.f25.s3.firstexam.repository.CategoryRepository;
import hsf302.f25.s3.firstexam.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

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

    public Map<String, List<Product>> getTop3ProductsByStockPerCategory() {
        List<Category> categories = categoryService.findAll();
        Map<String, List<Product>> result = new HashMap<>();

        for (Category category : categories) {
            List<Product> top3 = productRepository.findTop3ByCate_CateIdOrderByStockDesc(category.getCateId());
            result.put(category.getCateName(), top3);
        }
        return result;
    }
}
