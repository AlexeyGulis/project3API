package by.gulis.project3API.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @Min(value = -100, message = "Значение value должно быть между -100 и 100")
    @Max(value = 100, message = "Значение value должно быть между -100 и 100")
    private Double value;
    @NotNull(message = "Значение raining не должно быть пустым")
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
