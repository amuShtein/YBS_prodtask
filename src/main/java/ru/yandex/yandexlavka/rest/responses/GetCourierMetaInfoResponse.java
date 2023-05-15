package ru.yandex.yandexlavka.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import ru.yandex.yandexlavka.rest.Dto.CourierDto;
import ru.yandex.yandexlavka.rest.Dto.CreateCourierDto;

public class GetCourierMetaInfoResponse {
    @JsonProperty("rating")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long rating;

    @JsonProperty("earnings")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long earnings;

    @JsonUnwrapped
    CourierDto body;

    public GetCourierMetaInfoResponse(Long rating, Long earnings, CourierDto body) {
        this.rating = rating;
        this.earnings = earnings;
        this.body = body;
    }

    public GetCourierMetaInfoResponse() {
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public CourierDto getBody() {
        return body;
    }

    public void setBody(CourierDto body) {
        this.body = body;
    }
}
