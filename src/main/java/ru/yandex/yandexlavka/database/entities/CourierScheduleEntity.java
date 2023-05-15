package ru.yandex.yandexlavka.database.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


@Entity(name = "schedule_couriers")
public class CourierScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="range_start")
    LocalDateTime start;

    @Column(name= "range_end")
    LocalDateTime end;

    @ManyToOne
    @JoinColumn(name="courier")
    CourierEntity courier;

    public CourierScheduleEntity(CourierEntity c, String sched, DateTimeFormatter timeFormatter) {
        String[] bounds = sched.split("-");
        if(bounds.length != 2)
            throw new IllegalArgumentException("Invalid schedule string: " + sched);

        String unparsed_start = bounds[0];
        String unparsed_end = bounds[1];

        try {
            setStart(LocalDateTime.parse(unparsed_start, timeFormatter));
            setEnd(LocalDateTime.parse(unparsed_end, timeFormatter));
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage() + ": cannot parse schedule string: " + sched + ", " + unparsed_start + ", " + unparsed_end);
        }

        setCourier(c);
    }

    public CourierScheduleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public CourierEntity getCourier() {
        return courier;
    }

    public void setCourier(CourierEntity courier) {
        this.courier = courier;
    }
}
