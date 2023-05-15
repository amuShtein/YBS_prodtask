package ru.yandex.yandexlavka.database.entities;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import ru.yandex.yandexlavka.rest.Dto.CreateCourierDto;
import ru.yandex.yandexlavka.rest.Dto.CreateOrderDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="regions")
    Long regions;

    @Column(name="cost")
    Long cost;

    @Column(name="weight")
    Double weight;

    @Column(name="complete_time")
    LocalDateTime complete;

    @ManyToOne
    @JoinColumn(name="courier")
    CourierEntity courier;

    @OneToMany(mappedBy = "order")
    Set<OrderScheduleEntity> schedule;

    public OrderEntity(CreateOrderDto dto) {
        this(null, dto.getRegions(), dto.getCost(), dto.getWeight(), null, null, null);

    }

    public OrderEntity(Long id, Long regions, Long cost, Double weight, LocalDateTime complete, CourierEntity courier, Set<OrderScheduleEntity> schedule) {
        this.id = id;
        this.regions = regions;
        this.cost = cost;
        this.weight = weight;
        this.complete = complete;
        this.courier = courier;
        this.schedule = schedule;
    }

    public OrderEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegions() {
        return regions;
    }

    public void setRegions(Long regions) {
        this.regions = regions;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDateTime getComplete() {
        return complete;
    }

    public void setComplete(LocalDateTime complete) {
        this.complete = complete;
    }

    public CourierEntity getCourier() {
        return courier;
    }

    public void setCourier(CourierEntity courier) {
        this.courier = courier;
    }

    public Set<OrderScheduleEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<OrderScheduleEntity> schedule) {
        this.schedule = schedule;
    }
}
