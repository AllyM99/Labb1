package hig.se.service;

import hig.se.db.DbConnectionManager;
import hig.se.repository.DaoFactory;

public class ServiceRunner <T> {

    private final ServiceCommand<T> service;

    public ServiceRunner(ServiceCommand<T> service) {
        this.service = service;
    }

    public T execute(){
        service.init(new DaoFactory());
        DbConnectionManager.getInstance().open();
        T result  = this.service.execute();
        DbConnectionManager.getInstance().close();
        return result;

    }
}
