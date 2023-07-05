package kg.kubatbekov.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String carId;

    @JsonBackReference(value = "car_manufacturer")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "year")
    private int year;

    @JsonBackReference(value = "car_model")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    @JsonBackReference(value = "car_category")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    public Car(
            Manufacturer manufacturer,
            int year,
            Model model,
            Category category) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.model = model;
        this.category = category;
    }
}
