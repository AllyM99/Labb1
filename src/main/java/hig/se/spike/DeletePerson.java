package hig.se.spike;

import hig.se.domain.Person;
import hig.se.service.ServiceRunner;
import hig.se.service.person.DeletePersonService;

public class DeletePerson {

    public static void main(String[] args) {

        Person person = new Person(69, "Ali", 1999);
        ServiceRunner<Person> serviceRunner = new ServiceRunner<>(new DeletePersonService(person));

        serviceRunner.execute();

    }
}