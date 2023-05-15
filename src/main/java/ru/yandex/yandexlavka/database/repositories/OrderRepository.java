package ru.yandex.yandexlavka.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.database.entities.CourierEntity;
import ru.yandex.yandexlavka.database.entities.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByOrderByIdAsc();
    List<OrderEntity> findAllByCourierAndCourierNotNullAndCompleteNotNullAndCompleteBetween(CourierEntity courier, LocalDateTime completeStart, LocalDateTime completeEnd);
}
