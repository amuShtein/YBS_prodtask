package ru.yandex.yandexlavka.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.database.entities.OrderScheduleEntity;

public interface OrderScheduleRepository extends JpaRepository<OrderScheduleEntity, Long> {
}
