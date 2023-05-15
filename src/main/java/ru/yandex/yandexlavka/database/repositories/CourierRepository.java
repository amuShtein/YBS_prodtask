package ru.yandex.yandexlavka.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.database.entities.CourierEntity;

import java.util.List;

public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    List<CourierEntity> findAllByOrderByIdAsc();
}
