package ru.yandex.yandexlavka.managers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.yandexlavka.constants.CourierTypeConstants;
import ru.yandex.yandexlavka.database.entities.CourierEntity;
import ru.yandex.yandexlavka.database.entities.CourierScheduleEntity;
import ru.yandex.yandexlavka.database.entities.OrderEntity;
import ru.yandex.yandexlavka.database.repositories.CourierRepository;
import ru.yandex.yandexlavka.database.repositories.CourierScheduleRepository;
import ru.yandex.yandexlavka.database.repositories.OrderRepository;
import ru.yandex.yandexlavka.rest.Dto.CourierDto;
import ru.yandex.yandexlavka.rest.Dto.CreateCourierDto;
import ru.yandex.yandexlavka.rest.responses.GetCourierMetaInfoResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Math.min;

@Service
@Transactional
public class CourierManager {
    private static final Logger logger = LoggerFactory.getLogger(CourierManager.class);
    private final Map<String, CourierTypeConstants> types;

    final CourierRepository courierRepository;
    final OrderRepository orderRepository;
    final CourierScheduleRepository courierScheduleRepository;
    final DateTimeFormatter formatter;
    final DateTimeFormatter datetimeFormatter;
    public CourierManager(
            CourierRepository courierRepository,
            OrderRepository orderRepository,
            CourierScheduleRepository courierScheduleRepository,
            @Qualifier("timeFormat") DateTimeFormatter formatter,
            @Qualifier("datetimeFormat") DateTimeFormatter datetimeFormatter,
            @Qualifier("courierTypes") Map<String, CourierTypeConstants> types
    ) {
        this.courierRepository = courierRepository;
        this.orderRepository = orderRepository;
        this.courierScheduleRepository = courierScheduleRepository;
        this.formatter = formatter;
        this.datetimeFormatter = datetimeFormatter;
        this.types = types;
    }

    public CourierDto CreateCourier(CreateCourierDto c) {
        if(!types.containsKey(c.getType()))
            throw new IllegalArgumentException("Invalid type value");

        CourierEntity databaseEntity = new CourierEntity(c);
        courierRepository.save(databaseEntity);

        c.getSchedule().forEach(e -> courierScheduleRepository.save(new CourierScheduleEntity(databaseEntity, e, formatter)));

        return new CourierDto(databaseEntity.getId(), c);
    }

    public CourierDto courierDtoByEntity(CourierEntity entity) {
        List<String> schedule = entity.getSchedule().stream().map(
                 e -> e.getStart().format(formatter) + "-" + e.getEnd().format(formatter)
         ).toList();

         CreateCourierDto createCourierDto = new CreateCourierDto(entity.getType(), entity.getRegions(), schedule);

         return new CourierDto(entity.getId(), createCourierDto);
    }

    public CourierDto findCourier(Long id) {
        Optional<CourierEntity> found = courierRepository.findById(id);

        if(found.isEmpty())
            throw new NullPointerException("Cannot find courier with id " + id);

        return courierDtoByEntity(found.get());
    }

    public List<CourierDto> findCouriers(int offset, int limit) {
        List<CourierEntity> all = courierRepository.findAllByOrderByIdAsc();

        if(offset >= all.size())
            return new LinkedList<>();

        all = all.subList(offset, min(all.size(), offset + limit) );

        return all.stream().map(this::courierDtoByEntity).toList();
    }

    public GetCourierMetaInfoResponse calculateMetaInfo(Long id, LocalDate start, LocalDate end) {
        Optional<CourierEntity> found = courierRepository.findById(id);
        if(found.isEmpty() || start == null || end == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CourierDto courier = courierDtoByEntity(found.get());

        LocalDateTime from = start.atStartOfDay();
        LocalDateTime to = end.atStartOfDay().minusNanos(1);

        logger.info("select in range [" + from.format(datetimeFormatter) + ", " + to.format(datetimeFormatter) + "]");

        List<OrderEntity> res;
        res = orderRepository.findAllByCourierAndCourierNotNullAndCompleteNotNullAndCompleteBetween(found.get(), from, to);

        if(res.size() == 0)
            return new GetCourierMetaInfoResponse(null, null, courier);

        Long sum = 0L;
        for(OrderEntity e : res)
            sum += e.getCost();


        CourierTypeConstants consts = types.get(courier.getBody().getType());

        if(consts == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        return new GetCourierMetaInfoResponse(
                Math.round(((double)res.size()/ Duration.between(start.atStartOfDay(), end.atStartOfDay()).toHours()) * consts.getRating()),
                sum * consts.getEarning(),
                courier
        );
    }
}
