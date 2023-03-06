package hig.se.spike;

import hig.se.domain.Person;
import hig.se.service.ServiceRunner;
import hig.se.service.person.GetPersonService;

public class GetPerson {
    public static void main(String[] args){
        ServiceRunner<Person> serviceRunner = new ServiceRunner<>(new GetPersonService(76));
        Person person = serviceRunner.execute();
        System.out.println(person.getName());
    }
}
