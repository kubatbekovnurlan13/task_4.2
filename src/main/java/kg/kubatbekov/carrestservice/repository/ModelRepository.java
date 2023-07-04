package kg.kubatbekov.carrestservice.repository;

import kg.kubatbekov.carrestservice.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
}
