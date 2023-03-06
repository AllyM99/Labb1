package hig.se.service.person;

import hig.se.domain.Person;
import hig.se.service.BaseService;

import java.util.List;

public class GetAllPersonService extends BaseService<List<Person>> {


    @Override
    public List<Person> execute() {
        return factory.getPersonDao().getAll();

    }
}
