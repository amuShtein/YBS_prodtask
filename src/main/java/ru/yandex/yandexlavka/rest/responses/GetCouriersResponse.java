package ru.yandex.yandexlavka.rest.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.yandexlavka.rest.Dto.CourierDto;

import java.util.List;

public class GetCouriersResponse {
    @JsonProperty("couriers")
    List<CourierDto> couriers;

    Long limit, offset;

    public GetCouriersResponse(List<CourierDto> couriers, Long limit, Long offset) {
        this.couriers = couriers;
        this.limit = limit;
        this.offset = offset;
    }

    public GetCouriersResponse() {
    }

    public List<CourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CourierDto> couriers) {
        this.couriers = couriers;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "GetCouriersResponse{" +
                "couriers=" + couriers +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
