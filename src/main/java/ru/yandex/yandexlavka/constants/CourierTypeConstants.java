package ru.yandex.yandexlavka.constants;

public class CourierTypeConstants {
    Long earning;
    Long rating;

    public CourierTypeConstants(Long earning, Long rating) {
        this.earning = earning;
        this.rating = rating;
    }

    public Long getEarning() {
        return earning;
    }

    public void setEarning(Long earning) {
        this.earning = earning;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
