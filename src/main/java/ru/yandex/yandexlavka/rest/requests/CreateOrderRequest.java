package ru.yandex.yandexlavka.rest.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.yandexlavka.rest.Dto.CreateCourierDto;
import ru.yandex.yandexlavka.rest.Dto.CreateOrderDto;

import java.util.List;

public class CreateOrderRequest {
    @JsonProperty("orders")
    List<CreateOrderDto> orders;

    public CreateOrderRequest(List<CreateOrderDto> orders) {
        this.orders = orders;
    }

    public CreateOrderRequest() {
    }

    public List<CreateOrderDto> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "orders=" + orders +
                '}';
    }

    public void setOrders(List<CreateOrderDto> orders) {
        this.orders = orders;
    }
}
