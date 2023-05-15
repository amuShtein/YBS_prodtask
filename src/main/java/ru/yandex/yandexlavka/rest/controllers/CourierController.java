package ru.yandex.yandexlavka.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.yandexlavka.managers.CourierManager;
import ru.yandex.yandexlavka.rest.Dto.CourierDto;
import ru.yandex.yandexlavka.rest.RateLimiter;
import ru.yandex.yandexlavka.rest.requests.CreateCourierRequest;
import ru.yandex.yandexlavka.rest.responses.CreateCourierResponse;
import ru.yandex.yandexlavka.rest.responses.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.rest.responses.GetCouriersResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CourierController {
    @Autowired
    CourierManager courierManager;

    @Autowired
    RateLimiter rateLimiter;
    private static final String MAIN_RATELIMITER="mainRatelimiter";

    private static final Logger logger = LoggerFactory.getLogger(CourierController.class);

    @PostMapping(value = "/couriers", consumes = "application/json", produces = "application/json")
    CreateCourierResponse createCourier(@RequestBody CreateCourierRequest request) {
        rateLimiter.countConnection();
        logger.info("Received request: " + request);

        CreateCourierResponse response;

            List<CourierDto> created = request.getCouriers().stream()
                    .map(courierManager::CreateCourier).toList();
            response = new CreateCourierResponse(created);

        logger.info("Response sent: " + response);

        return response;
    }

    @GetMapping(value = "/couriers/{courier_id}", produces = "application/json")
    CourierDto getSingleCourier(@PathVariable("courier_id") Long id) {
        rateLimiter.countConnection();
        logger.info("Request for courier with id " + id);

        CourierDto response;
        response = courierManager.findCourier(id);

        if(response == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        logger.info("Response sent: " + response);

        return response;
    }

    @GetMapping(value = "/couriers", produces = "application/json")
    GetCouriersResponse getCouriers(
            @RequestParam(name="offset", required = false, defaultValue = "0") Long offset,
            @RequestParam(name="limit", required = false, defaultValue = "1") Long limit
    ) {
        rateLimiter.countConnection();
        if(offset == null || limit == null || offset < 0 || limit < 0)
            throw new IllegalArgumentException();

        logger.info("Request for couriers: offset " + offset + " limit " + limit);

        GetCouriersResponse response = new GetCouriersResponse(courierManager.findCouriers(Math.toIntExact(offset), Math.toIntExact(limit)), limit, offset);

        logger.info("Response sent: " + response);
        return response;
    }

    @GetMapping(value = "/couriers/meta-info/{courier_id}", produces = "application/json")
    GetCourierMetaInfoResponse getInfoAboutCourier(
            @PathVariable("courier_id") Long id,
            @RequestParam(name="startDate") String startDate,
            @RequestParam(name="endDate") String endDate
    ) {
        rateLimiter.countConnection();
        logger.info("Request for meta info of courier with id " + id);
        GetCourierMetaInfoResponse response;

        response = courierManager.calculateMetaInfo(id, LocalDate.parse(startDate), LocalDate.parse(endDate));

        if(response == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        logger.info("Response sent: " + response);

        return response;
    }
}
