package com.example.yone3.springwebsample.seeder;

import com.example.yone3.springwebsample.entity.ReviewEntity;
import com.example.yone3.springwebsample.mapper.ReviewMapper;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@ShellComponent
public class ReviewSeeder {

    private final Faker faker = new Faker(new Locale("ja_JP"));

    @Autowired
    private ReviewMapper mapper;

    @Transactional
    @ShellMethod(value = "Run the seeder", key = "run-seeder")
    public void runSeeder(@ShellOption(value = "count", defaultValue = "100") int createCount) {
        List<ReviewEntity> entities = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 2, 1);
        Date randomStartDate = calendar.getTime();
        calendar.set(2024, 2, 1);
        Date randomEndTime = calendar.getTime();
        for (int i = 0; i < createCount; i++) {
            entities.add(ReviewEntity.newInstance(
                    0,
                    faker.name().fullName(),
                    faker.book().title(),
                    faker.internet().image(),
                    faker.options().nextElement(Arrays.asList("S","A","B","C","D","E")),
                    faker.zelda().game(),
                    faker.options().nextElement(Arrays.asList("127.0.0.1", faker.internet().ipV4Address())),
                    faker.internet().userAgent(),
                    new Timestamp(faker.date().between(randomStartDate, randomEndTime).getTime()).toLocalDateTime(),
                    new Timestamp(faker.date().between(randomStartDate, randomEndTime).getTime()).toLocalDateTime(),
                    null,
                    false
            ));
        }
        mapper.bulkCreate(entities);
    }
}
