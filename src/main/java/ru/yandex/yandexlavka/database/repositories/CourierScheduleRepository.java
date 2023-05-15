package ru.yandex.yandexlavka.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.database.entities.CourierScheduleEntity;

public interface CourierScheduleRepository extends JpaRepository<CourierScheduleEntity, Long> {
}
