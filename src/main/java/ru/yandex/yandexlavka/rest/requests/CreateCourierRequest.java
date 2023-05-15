package ru.yandex.yandexlavka.rest.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.yandexlavka.rest.Dto.CreateCourierDto;

import java.util.List;

public class CreateCourierRequest {
    @JsonProperty("couriers")
    List<CreateCourierDto> couriers;

    public CreateCourierRequest() {

    }

    public CreateCourierRequest(List<CreateCourierDto> couriers) {
        this.couriers = couriers;
    }

    public List<CreateCourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CreateCourierDto> couriers) {
        this.couriers = couriers;
    }
}
