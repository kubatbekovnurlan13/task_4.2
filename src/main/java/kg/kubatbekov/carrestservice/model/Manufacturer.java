package kg.kubatbekov.carrestservice.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "manufacturers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @JsonManagedReference(value = "car_manufacturer")
    @OneToMany(mappedBy = "manufacturer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Car> cars;

    public Manufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
