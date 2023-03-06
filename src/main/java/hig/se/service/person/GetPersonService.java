package hig.se.service.person;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Person;
import hig.se.repository.PersonDao;
import hig.se.service.BaseService;
import hig.se.service.CleaningManagerServiceException;

import java.util.NoSuchElementException;


public class GetPersonService extends BaseService<Person> {
    int id;
    public GetPersonService(int id){
        this.id = id;
    }


    @Override
    public Person execute() {
        PersonDao pd = new PersonDao();
        DbConnectionManager.getInstance().open();
        try {
            return pd.get(id);
        }catch (NoSuchElementException e){
            throw new CleaningManagerServiceException(e.getMessage());
        }finally {
            DbConnectionManager.getInstance().close();
        }

    }
}
