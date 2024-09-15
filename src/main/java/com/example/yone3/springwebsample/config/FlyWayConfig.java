package com.example.yone3.springwebsample.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfig {

    @Bean
    public FlywayMigrationStrategy cleanMigrateStrategy() {
//        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
//            @Override
//            public void migrate(Flyway flyway) {
//                flyway.clean();
//                flyway.migrate();
//            }
//        };
//
//        return strategy;
        return new FlywayMigrationStrategy() {
            @Override
            public void migrate(Flyway flyway) {
                flyway.migrate();
            }
        };
    }
}
