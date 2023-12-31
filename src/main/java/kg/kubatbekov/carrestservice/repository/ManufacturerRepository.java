package kg.kubatbekov.carrestservice.repository;

import kg.kubatbekov.carrestservice.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends
        JpaRepository<Manufacturer, Integer>, PagingAndSortingRepository<Manufacturer, Integer> {
    Optional<Manufacturer> findByManufacturerName(String name);
}
