package hig.se.spike;

import hig.se.domain.Person;
import hig.se.service.ServiceRunner;
import hig.se.service.person.GetAllPersonService;

import java.util.List;

public class GetAllPerson {
    public static void main(String[] args) {
        ServiceRunner<List<Person>> serviceRunner = new ServiceRunner<>(new GetAllPersonService());
        List<Person> personList = serviceRunner.execute();
        for (Person p : personList) {
            System.out.println(p.getName());
        }
    }
}
