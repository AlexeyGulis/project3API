package by.gulis.project3API.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class MeasurementDTO {
    @Column(name = "value")
    @Min(value = -100, message = "Значение value должно быть между -100 и 100")
    @Max(value = 100, message = "Значение value должно быть между -100 и 100")
    @NotEmpty(message = "Имя не должно быть пустым")
    private Double value;
    @Column(name = "raining")
    @NotEmpty(message = "Имя не должно быть пустым")
    private Boolean raining;
    private SensorDTO sensor;

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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
