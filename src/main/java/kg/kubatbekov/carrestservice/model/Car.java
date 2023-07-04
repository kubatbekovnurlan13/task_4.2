package kg.kubatbekov.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;

    @JsonBackReference(value = "car_manufacturer")
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "year")
    private int year;

    @JsonBackReference(value = "car_model")
    @OneToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @JsonBackReference(value = "car_category")
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

}
