package in.purabtech.springpagination.controller;

import in.purabtech.springpagination.entity.Person;
import in.purabtech.springpagination.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Person> findAll(
            @RequestParam(required = false) String title,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        List<Order> orders = new ArrayList<Order>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                //orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                Sort.Direction dire = _sort[1].contains("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
                Order order = new Order(dire,_sort[0]);
                orders.add( order );
            }
        } else {
            // sort=[field, direction]
            //orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            Sort.Direction dire = sort[1].contains("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
            Order order = new Order(dire,sort[0]);
            orders.add( order );

        }
        //PageRequest pr = PageRequest.of(page,size, Sort.by(orders));


        PageRequest pr = PageRequest.of(page,size);
        return repository.findAll(pr);
    }



}
