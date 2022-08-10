package by.gulis.project3API.DTO;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30,
            message = "Название сенсора должно быть длиной от 2 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
