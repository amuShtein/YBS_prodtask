package ru.yandex.yandexlavka.managers;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.yandexlavka.database.entities.CourierEntity;
import ru.yandex.yandexlavka.database.entities.CourierScheduleEntity;
import ru.yandex.yandexlavka.database.entities.OrderEntity;
import ru.yandex.yandexlavka.database.entities.OrderScheduleEntity;
import ru.yandex.yandexlavka.database.repositories.CourierRepository;
import ru.yandex.yandexlavka.database.repositories.CourierScheduleRepository;
import ru.yandex.yandexlavka.database.repositories.OrderRepository;
import ru.yandex.yandexlavka.database.repositories.OrderScheduleRepository;
import ru.yandex.yandexlavka.rest.Dto.*;
import ru.yandex.yandexlavka.rest.responses.GetCourierMetaInfoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.min;

@Service
@Transactional
public class OrderManager {
    private static final Logger logger = LoggerFactory.getLogger(OrderManager.class);

    final OrderRepository orderRepository;
    final CourierRepository courierRepository;
    final OrderScheduleRepository orderScheduleRepository;
    final DateTimeFormatter formatter;
    final DateTimeFormatter datetimeFormatter;

    public OrderManager(
            OrderRepository orderRepository,
            CourierRepository courierRepository,
            OrderScheduleRepository orderScheduleRepository,
            @Qualifier("timeFormat") DateTimeFormatter formatter,
            @Qualifier("datetimeFormat") DateTimeFormatter datetimeFormatter) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
        this.orderScheduleRepository = orderScheduleRepository;
        this.formatter = formatter;
        this.datetimeFormatter = datetimeFormatter;
    }

    public OrderDto CreateOrder(CreateOrderDto o) {
        LocalDateTime now = LocalDateTime.now();

        OrderEntity databaseEntity = new OrderEntity(o);
        orderRepository.save(databaseEntity);

        o.getSchedule().forEach(e -> orderScheduleRepository.save(new OrderScheduleEntity(databaseEntity, e, formatter, now)));

        return new OrderDto(databaseEntity.getId(), null,  o);
    }

    public OrderDto orderDtoByEntity(OrderEntity entity) {
        List<String> schedule = entity.getSchedule().stream().map(
                 e -> e.getStart().format(formatter) + "-" + e.getEnd().format(formatter)
         ).toList();

         CreateOrderDto createOrderDto = new CreateOrderDto(entity.getWeight(), entity.getRegions(), schedule, entity.getCost());

         return new OrderDto(
                 entity.getId(),
                 entity.getComplete() == null ? null : entity.getComplete().format(datetimeFormatter),
                 createOrderDto
         );
    }

    public OrderDto findOrder(Long id) {
        Optional<OrderEntity> found = orderRepository.findById(id);

        if(found.isEmpty())
            throw new NullPointerException("Cannot find order with id " + id);

        return orderDtoByEntity(found.get());
    }

    public List<OrderDto> findOrders(int offset, int limit) {
        List<OrderEntity> all = orderRepository.findAllByOrderByIdAsc();

        if(offset >= all.size())
            return new LinkedList<>();

        all = all.subList(offset, min(all.size(), offset + limit) );

        return all.stream().map(this::orderDtoByEntity).toList();
    }

    public OrderDto completeOrder(CompleteOrder info) {
        Optional<OrderEntity> foundOrder = orderRepository.findById(info.getOrderId());
        if(foundOrder.isEmpty())
            throw new IllegalArgumentException("Invalid order id " + info.getOrderId());

        Optional<CourierEntity> foundCourier = courierRepository.findById(info.getCourierId());
        if(foundCourier.isEmpty())
            throw new IllegalArgumentException("Invalid courier id " + info.getCourierId());

        LocalDateTime time = LocalDateTime.parse(info.getCompleteTime(), datetimeFormatter);

        if(foundOrder.get().getCourier() != foundCourier.get())
            throw new IllegalArgumentException();

        foundOrder.get().setComplete(time);

        orderRepository.save(foundOrder.get());

        return orderDtoByEntity(foundOrder.get());
    }
}
