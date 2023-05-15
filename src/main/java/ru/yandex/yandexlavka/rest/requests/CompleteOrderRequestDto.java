package ru.yandex.yandexlavka.rest.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.yandexlavka.rest.Dto.CompleteOrder;

import java.util.List;

public class CompleteOrderRequestDto {
    @JsonProperty("complete_info")
    List<CompleteOrder> info;

    public CompleteOrderRequestDto(List<CompleteOrder> info) {
        this.info = info;
    }

    public CompleteOrderRequestDto() {
    }

    public List<CompleteOrder> getInfo() {
        return info;
    }

    public void setInfo(List<CompleteOrder> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "CompleteOrderRequestDto{" +
                "info=" + info +
                '}';
    }
}
