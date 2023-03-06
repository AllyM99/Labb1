package hig.se.service.person;

import hig.se.db.DbConnectionManager;
import hig.se.repository.PersonDao;
import hig.se.service.BaseService;

public class DeleteAllPersonService extends BaseService<Void> {


    @Override
    public Void execute() {
        PersonDao pd = new PersonDao();
        DbConnectionManager.getInstance().open();
        pd.deleteAll();
        DbConnectionManager.getInstance().close();
        return null;
    }
}
