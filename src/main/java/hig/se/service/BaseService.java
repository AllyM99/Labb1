package hig.se.service;

import hig.se.repository.DaoFactory;

public abstract class BaseService<T> implements ServiceCommand<T> {
    protected DaoFactory factory;
    @Override
    public void init(DaoFactory factory) {
        this.factory = factory;
    }

    @Override
    public abstract T execute();
}
