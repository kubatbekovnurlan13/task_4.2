package kg.kubatbekov.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @JsonManagedReference(value = "car_category")
    @OneToMany(mappedBy = "category",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Car> cars;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
