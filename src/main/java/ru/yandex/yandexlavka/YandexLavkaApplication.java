package ru.yandex.yandexlavka;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.request.WebRequest;
import ru.yandex.yandexlavka.constants.CourierTypeConstants;

import io.github.bucket4j.Bucket;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;
import java.util.TreeMap;


@EnableJpaRepositories("ru.yandex.yandexlavka.database.repositories")
@SpringBootApplication
public class YandexLavkaApplication {
    private static final Logger logger = LoggerFactory.getLogger(YandexLavkaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(YandexLavkaApplication.class, args);
    }

    @Bean
    protected CommandLineRunner contextLoaded() {
        return args -> logger.info("Service started successfully");
    }

    @Bean(name = "timeFormat")
    protected DateTimeFormatter formatter() {
        return new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.YEAR, 1970)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
    }

    @Bean(name = "datetimeFormat")
    protected DateTimeFormatter datetimeFormatter() {
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .toFormatter();
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                return new TreeMap<>();
            }
        };
    }

    @Bean(name = "courierTypes")
    public Map<String, CourierTypeConstants> courierTypes() {
        Map<String, CourierTypeConstants> res = new TreeMap<>();

        res.put("FOOT", new CourierTypeConstants(2L, 3L));
        res.put("BIKE", new CourierTypeConstants(3L, 2L));
        res.put("AUTO", new CourierTypeConstants(4L, 1L));

        return res;
    }
}
