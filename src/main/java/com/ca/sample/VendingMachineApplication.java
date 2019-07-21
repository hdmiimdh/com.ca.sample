package com.ca.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@PropertySource({
        "classpath:/db/database.properties",
        "classpath:/i18n/messages.properties"
})
@EnableJpaRepositories(basePackages = {"com.ca.sample.repository"})
@EntityScan(basePackages = {"com.ca.sample.domain"})
public class VendingMachineApplication {

    private static final Logger logger = LoggerFactory.getLogger(VendingMachineApplication.class);

    public static void main(final String[] args) throws UnknownHostException {

        final Environment env = SpringApplication.run(VendingMachineApplication.class, args).getEnvironment();

        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port") + "/swagger-ui.html#/",
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port") + "/swagger-ui.html#/");
    }
}
