package ru.yandex.yandexlavka.rest.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class CourierDto {
    @JsonProperty("courier_id")
    Long id;

    @JsonUnwrapped
    CreateCourierDto body;

    public CourierDto(Long id, CreateCourierDto body) {
        this.id = id;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateCourierDto getBody() {
        return body;
    }

    public void setBody(CreateCourierDto body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CourierDto{" +
                "id=" + id +
                ", body=" + body +
                '}';
    }
}
