package by.gulis.project3API.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    @Min(value = -100)
    @Max(value = 100)
    private Double value;
    @Column(name = "raining")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor owner;
}
