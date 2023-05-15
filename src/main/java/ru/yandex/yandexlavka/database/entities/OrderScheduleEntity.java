package ru.yandex.yandexlavka.database.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity(name = "schedule_orders")
public class OrderScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="range_start")
    LocalDateTime start;

    @Column(name= "range_end")
    LocalDateTime end;

    @ManyToOne
    @JoinColumn(name= "order_id")
    OrderEntity order;

    public OrderScheduleEntity(OrderEntity o, String sched, DateTimeFormatter timeFormatter, LocalDateTime today) {
        String[] bounds = sched.split("-");
        if(bounds.length != 2)
            throw new IllegalArgumentException("Invalid schedule string: " + sched);

        String unparsed_start = bounds[0];
        String unparsed_end = bounds[1];

        try {
            setStart(LocalDateTime.parse(unparsed_start, timeFormatter));
            setEnd(LocalDateTime.parse(unparsed_end, timeFormatter));

            start = start.withYear(today.getYear()).withMonth(today.getMonthValue()).withDayOfYear(today.getDayOfYear());
            end = end.withYear(today.getYear()).withMonth(today.getMonthValue()).withDayOfYear(today.getDayOfYear());
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage() + ": cannot parse schedule string: " + sched + ", " + unparsed_start + ", " + unparsed_end);
        }

        setOrder(o);
    }
    public OrderScheduleEntity() {
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
