package ru.yandex.yandexlavka.database.entities;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.LongArrayType;
import jakarta.persistence.*;

import org.hibernate.annotations.Type;
import ru.yandex.yandexlavka.rest.Dto.CreateCourierDto;

import java.util.Set;

@Entity(name = "couriers")
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="type")
    String type;

    @Column(name="regions", columnDefinition = "integer[]")
    @Type(IntArrayType.class)
    Integer[] regions;

    @OneToMany(mappedBy = "courier")
    Set<CourierScheduleEntity> schedule;

    @OneToMany(mappedBy = "courier")
    Set<OrderEntity> orders;


    public CourierEntity(CreateCourierDto courier) {
        setType(courier.getType());
        setRegions(courier.getRegions());
    }

    public CourierEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<CourierScheduleEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<CourierScheduleEntity> schedule) {
        this.schedule = schedule;
    }
}
