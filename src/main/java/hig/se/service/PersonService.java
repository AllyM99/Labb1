package hig.se.service;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Person;
import hig.se.repository.PersonDao;

import java.util.List;

public class PersonService {

    PersonDao dao;

    public PersonService(PersonDao dao){
        this.dao = dao;
    }
    public PersonService(){
        dao = new PersonDao(DbConnectionManager.getInstance());
    }
    public Person get(int id){
        return dao.get(id);
    }
    public List<Person> getAll(){
        return dao.getAll();
    }
    public Person save(Person person){
        return dao.save(person);
    }
    public Person update(Person person){
        return dao.update(person);

    }
    public Person delete(Person person){
       return dao.delete(person);
    }
    public void deleteAll(){
        dao.deleteAll();
    }

}
