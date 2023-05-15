package ru.yandex.yandexlavka.rest.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class OrderDto {
    @JsonProperty("order_id")
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("completed_time")
    String completedTime;

    @JsonUnwrapped
    CreateOrderDto body;

    public OrderDto(Long id, String completedTime, CreateOrderDto body) {
        this.id = id;
        this.completedTime = completedTime;
        this.body = body;
    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }

    public CreateOrderDto getBody() {
        return body;
    }

    public void setBody(CreateOrderDto body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", completedTime='" + completedTime + '\'' +
                ", body=" + body +
                '}';
    }
}
