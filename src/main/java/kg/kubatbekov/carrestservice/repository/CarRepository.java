package kg.kubatbekov.carrestservice.repository;

import kg.kubatbekov.carrestservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends
        PagingAndSortingRepository<Car, Integer>, JpaRepository<Car, Integer> {
}
