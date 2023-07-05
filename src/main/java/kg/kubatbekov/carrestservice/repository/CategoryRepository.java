package kg.kubatbekov.carrestservice.repository;

import kg.kubatbekov.carrestservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, PagingAndSortingRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String name);
}
