package hig.se.spike;

import hig.se.domain.Person;
import hig.se.service.ServiceRunner;
import hig.se.service.person.UpdatePersonService;

public class UpdatePerson {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person(68, "Ali", 1921);
        person.setName("DODO");
        person.setBirthYear(1912);
        ServiceRunner<Person> serviceRunner = new ServiceRunner<>(new UpdatePersonService(person));
        serviceRunner.execute();
   }
}
