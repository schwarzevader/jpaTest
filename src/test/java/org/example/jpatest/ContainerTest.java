package org.example.jpatest;


import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.MountableFile;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
public class ContainerTest {

    @Container
     static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:latest")
                    .withCopyFileToContainer(MountableFile.forClasspathResource("testData.sql"),
                            "/docker-entrypoint-initdb.d/");


//    @Container
//    static PostgreSQLContainer<?> postgreSQLContainer =
//            new PostgreSQLContainer<>("postgres:latest")
//                    .withCopyFileToContainer(MountableFile.forClasspathResource
//                            ("schema-test.sql"), "/docker-entrypoint-initdb.d/");


    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
//        Startables.deepStart(postgreSQLContainer, mysqlContainer);

//        Startables.deepStart(postgreSQLContainer);
//        log.info("id------" + postgreSQLContainer.getContainerId());
//        log.info("url------" + postgreSQLContainer.getJdbcUrl());
//        log.info("username------" + postgreSQLContainer.getUsername());
//        log.info("password------" + postgreSQLContainer.getPassword());
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//        registry.add("ClasspathResource", (Supplier<Object>) postgreSQLContainer
//                .withCopyFileToContainer(MountableFile.forClasspathResource("schema-test.sql"), "/docker-entrypoint-initdb.d/"));
////////////////////////////////////////
        log.info("id------" + mysql.getContainerId());
        log.info("url------" + mysql.getJdbcUrl());
        log.info("username------" + mysql.getUsername());
        log.info("password------" + mysql.getPassword());
        Startables.deepStart(mysql);
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        ////////////////////////////
    }
}
