package in.purabtech.springpagination;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPaginationApplication.class, args);
    }

    @Bean
    Faker faker() {
        return new Faker();
    }

}
