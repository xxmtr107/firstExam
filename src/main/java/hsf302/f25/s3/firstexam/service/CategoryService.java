package hsf302.f25.s3.firstexam.service;

import hsf302.f25.s3.firstexam.entity.Category;
import hsf302.f25.s3.firstexam.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
