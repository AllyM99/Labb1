package hig.se.spike;

import hig.se.domain.Person;
import hig.se.service.ServiceRunner;
import hig.se.service.person.AddPersonService;

public class AddPerson {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("JOOOOHN", 1999);
        ServiceRunner<Person> serviceRunner = new ServiceRunner<>(new AddPersonService(person));
        serviceRunner.execute();
    }
}
