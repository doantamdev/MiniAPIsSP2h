package com.SpringBootTutorial.FirstProject.database;

import com.SpringBootTutorial.FirstProject.models.Product;
import com.SpringBootTutorial.FirstProject.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    docker run -d --rm --name mysql-spring-boot-tutorial mysql:latest
    -e MYSQL_ROOT_PASSWORD=12345678Aa
    -e MYSQL_USER= admin
    -e MYSQL_PASSWORD= 12345678Aa
    -e MYSQL_DATABASE= test_db
    -p 3309:3306\
    -v mysql-spring-boot-tutorial_volume:/var/lib/mysql


    mysql -h localhost -P 3309 --protocol=tcp -u doantam -p
 */


/*
    docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD=12345678Aa -e MYSQL_USER=doantam -e MYSQL_PASSWORD=12345678Aa -e MYSQL_DATABASE=test_db -p 3309:3306 -v mysql-spring-boot-tutorial_volume:/var/lib/mysql mysql:latest
    mysql -u doantam -p -h localhost -P 3309
 */

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("Iphone 11 ProMax",2019,1299.00,"");
//                Product productB = new Product("Iphone 6Plus",2022,499.00,"");
//                logger.info("insert data" + productRepository.save(productA));
//                logger.info("insert data" + productRepository.save(productB));
            }
        };
    }
}
