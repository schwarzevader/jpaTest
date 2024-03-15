package org.example.jpatest;




import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
//import org.testcontainers.shaded.com.google.common.net.HttpHeaders;


import org.testcontainers.utility.MountableFile;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(initializers = {AbstractContainerTest.Initializer.class})



//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//
////@DataJpaTest
////@TestPropertySource(properties = {
////        "spring.test.database.replace=none",
////        "spring.datasource.url=jdbc:tc:postgresql:latest:///db?TC_INITSCRIPT=schema-test.sql"
////})
//@Testcontainers


//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class
//        ,DataSourceTransactionManagerAutoConfiguration.class
//        , JpaRepositoriesAutoConfiguration.class})


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "spring.jpa.defer-datasource-initialization=false")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest(properties = "application-test.yml")
//@ContextConfiguration

//@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
//@TestPropertySource(value = "classpath:application-test.yml")
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class AbstractContainerTest {


//    protected  RequestSpecification requestSpecification;
//
//    @LocalServerPort
//    private Integer localPort;

//    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:latest")
            .withCopyFileToContainer(MountableFile.forClasspathResource("testData.sql"),
                    "/docker-entrypoint-initdb.d/");


//    @Container
//    static PostgreSQLContainer<?> postgreSQLContainer =
//            new PostgreSQLContainer<>("postgres:latest")
//                    .withCopyFileToContainer(MountableFile.forClasspathResource
//                            ("schema-test.sql"), "/docker-entrypoint-initdb.d/");


//    @DynamicPropertySource
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
