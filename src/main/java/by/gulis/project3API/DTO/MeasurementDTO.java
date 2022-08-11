package by.gulis.project3API.DTO;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;

public class MeasurementDTO {

    @DecimalMax(value = "100.0", message = "Значение value должно быть между -100 и 100")
    @DecimalMin(value = "-100.0" , message = "Значение value должно быть между -100 и 100")
    @NotNull(message = "Значение value не должно быть null")
    private Double value;
    @NotNull(message = "Значение raining не должно быть null")
    private Boolean raining;
    @NotNull(message = "Значение sensor не должно быть null")
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
