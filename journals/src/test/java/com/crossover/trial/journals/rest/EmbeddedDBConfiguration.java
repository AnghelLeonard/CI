package com.crossover.trial.journals.rest;

import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

/**
 *
 * @author newlife
 */
@TestConfiguration
@Profile("test")
public class EmbeddedDBConfiguration {

    @Bean
    public static DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().
                generateUniqueName(true).
                setType(HSQL).
                setScriptEncoding("UTF-8").
                ignoreFailedDrops(true).
                addScript("schema-testing.sql").
                addScripts("data.sql").
                build();
    }
}
