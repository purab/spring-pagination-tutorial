package in.purabtech.springpagination.repository;

import in.purabtech.springpagination.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person,Integer> {

}