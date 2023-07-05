package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Category;
import kg.kubatbekov.carrestservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    public List<Category> findCategoriesByPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("categoryName").ascending());
        Page<Category> pagingCategories = categoryRepository.findAll(pageRequest);
        return pagingCategories.getContent();
    }
}
