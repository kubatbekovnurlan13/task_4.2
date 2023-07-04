package kg.kubatbekov.carrestservice.repository;

import kg.kubatbekov.carrestservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
