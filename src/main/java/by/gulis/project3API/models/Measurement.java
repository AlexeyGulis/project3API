package by.gulis.project3API.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    @Min(value = -100, message = "Значение value должно быть между -100 и 100")
    @Max(value = 100, message = "Значение value должно быть между -100 и 100")
    @NotEmpty(message = "Имя не должно быть пустым")
    private Double value;
    @Column(name = "raining")
    @NotEmpty(message = "Имя не должно быть пустым")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor owner;

    public Measurement() {
    }

    public Measurement(Double value, Boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getOwner() {
        return owner;
    }

    public void setOwner(Sensor owner) {
        this.owner = owner;
    }
}
