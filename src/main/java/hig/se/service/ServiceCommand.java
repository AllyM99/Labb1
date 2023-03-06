package hig.se.service;

import hig.se.repository.DaoFactory;

public interface ServiceCommand<T> {
     public void init(DaoFactory factory);
     T execute();


}
