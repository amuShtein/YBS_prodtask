package ru.yandex.yandexlavka.rest.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompleteOrder {
    @JsonProperty("courier_id")
    Long courierId;

    @JsonProperty("order_id")
    Long orderId;

    @JsonProperty("complete_time")
    String completeTime;

    public CompleteOrder(Long courierId, Long orderId, String completeTime) {
        this.courierId = courierId;
        this.orderId = orderId;
        this.completeTime = completeTime;
    }

    public CompleteOrder() {
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public String toString() {
        return "CompleteOrder{" +
                "courierId=" + courierId +
                ", orderId=" + orderId +
                ", completeTime='" + completeTime + '\'' +
                '}';
    }
}
