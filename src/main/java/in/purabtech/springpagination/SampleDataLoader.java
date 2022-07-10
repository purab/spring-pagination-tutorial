package in.purabtech.springpagination;

import com.github.javafaker.Faker;
import in.purabtech.springpagination.entity.Address;
import in.purabtech.springpagination.entity.Person;
import in.purabtech.springpagination.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
    private final PersonRepository repository;
    private final Faker faker;

    public SampleDataLoader(PersonRepository repository, Faker faker) {
        this.repository = repository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading sample data...");

        /*Person person = new Person("Dan",
                "Vega",
                "216.555.1212",
                "danvega@gmail.com",
                new Address("Street","City","State","Zip"));

        repository.save(person); */

        //create 100 person sample data
        List<Person> people = IntStream.rangeClosed(1,100)
                .mapToObj(i -> new Person(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().cellPhone(),
                        faker.internet().emailAddress(),
                        new Address(
                                faker.address().streetAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        )

                ))
                .collect(Collectors.toList());
        repository.saveAll(people);

    }

}
