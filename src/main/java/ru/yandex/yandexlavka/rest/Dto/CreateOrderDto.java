package ru.yandex.yandexlavka.rest.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateOrderDto {
    @JsonProperty("weight")
    private Double weight;
    @JsonProperty("regions")
    private Long regions;
    @JsonProperty("delivery_hours")
    private List<String> schedule;
    @JsonProperty("cost")
    private Long cost;

    public CreateOrderDto(Double weight, Long regions, List<String> schedule, Long cost) {
        this.weight = weight;
        this.regions = regions;
        this.schedule = schedule;
        this.cost = cost;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getRegions() {
        return regions;
    }

    public void setRegions(Long regions) {
        this.regions = regions;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CreateOrderDto{" +
                "weight=" + weight +
                ", regions=" + regions +
                ", schedule=" + schedule +
                ", cost=" + cost +
                '}';
    }
}
