package kg.kubatbekov.carrestservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @Column(name = "model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelId;

    @Column(name = "model_name")
    private String modelName;

    @JsonManagedReference(value = "car_model")
    @OneToOne(mappedBy = "model")
    private Car car;
}
