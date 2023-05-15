package ru.yandex.yandexlavka.rest.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class CreateCourierDto {
    @JsonProperty("courier_type")
    private String type;
    @JsonProperty("regions")
    private Integer[] regions;
    @JsonProperty("working_hours")
    private List<String> schedule;

    public CreateCourierDto(String type, Integer[] regions, List<String> schedule) {
        this.type = type;
        this.regions = regions;
        this.schedule = schedule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer[] getRegions() {
        return regions;
    }

    public void setRegions(Integer[] regions) {
        this.regions = regions;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "CreateCourierRequest{" +
                "type='" + type + '\'' +
                ", regions=" + Arrays.toString(regions) +
                ", schedule=" + schedule +
                '}';
    }
}
