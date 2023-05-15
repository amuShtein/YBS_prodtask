package ru.yandex.yandexlavka.rest.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.yandexlavka.rest.Dto.CourierDto;
import java.util.List;

public class CreateCourierResponse {
    @JsonProperty("couriers")
    List<CourierDto> couriers;

    public CreateCourierResponse(List<CourierDto> couriers) {
        this.couriers = couriers;
    }

    public List<CourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CourierDto> couriers) {
        this.couriers = couriers;
    }
}
