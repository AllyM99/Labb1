package hig.se.service.person;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Person;
import hig.se.repository.PersonDao;
import hig.se.service.BaseService;

public class DeletePersonService extends BaseService<Person> {

    Person person;
    public DeletePersonService(Person person){
        this.person = person;
    }

    @Override
    public Person execute() {
        PersonDao pd = new PersonDao();
        DbConnectionManager.getInstance().open();
        Person person1 = pd.delete(person);
        DbConnectionManager.getInstance().close();
        return person1;
    }
}
