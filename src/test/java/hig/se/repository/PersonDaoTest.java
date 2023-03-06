package hig.se.repository;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Person;
import hig.se.service.PersonService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PersonDaoTest {
    private static DbConnectionManager dbMock;
    private static ResultSet rsMock;
    private static PersonDao personDao;
    private static PersonService personService;
    private static Person person;

    private static PreparedStatement preparedStatement;

    @BeforeAll
    public static void setUpClass() {
        dbMock = mock(DbConnectionManager.class);
        rsMock = mock(ResultSet.class);
        preparedStatement = mock(PreparedStatement.class);

        personDao = new PersonDao(dbMock);
        personService = new PersonService(personDao);
        person = new Person(64, "Ali", 1999);

    }

    @AfterAll
    public static void tearDownAfterClass() {
        dbMock = null;
        rsMock = null;
        personService = null;
        personDao = null;
        person = null;
    }

    @Test
    void get() throws SQLException {
        when(dbMock.excecuteQuery(anyString())).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true).thenReturn(false);
        when(rsMock.getInt(1)).thenReturn(person.getId());
        when(rsMock.getString(2)).thenReturn(person.getName());
        when(rsMock.getInt(3)).thenReturn(person.getBirthYear());

        Person result = personDao.get(person.getId());

        assertTrue(person.equals(result));


    }

    @Test
    void getAll() throws SQLException {
        when(dbMock.excecuteQuery(anyString())).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true).thenReturn(false);
        when(rsMock.getInt(1)).thenReturn(person.getId());
        when(rsMock.getString(2)).thenReturn(person.getName());
        when(rsMock.getInt(3)).thenReturn(person.getBirthYear());

        List<Person> result = personDao.getAll();

        assertEquals(1, result.size());
        assertTrue(result.get(0).equals(person));

    }

    @Test
    void save() throws SQLException {

        Person t = new Person(65, "Danne", 1993);

        when(dbMock.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(rsMock);
        when(rsMock.getInt(1)).thenReturn(person.getId());
        when(rsMock.getString(2)).thenReturn(person.getName());
        when(rsMock.getInt(3)).thenReturn(person.getBirthYear());

        Person person = personDao.save(t);
        assertTrue(person.equals(t));

    }

    @Test
    void update() throws SQLException {
        Person t = new Person(67, "ÅkeWallin", 1993);

        when(dbMock.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(rsMock);
        when(rsMock.getInt(1)).thenReturn(person.getId());
        when(rsMock.getString(2)).thenReturn(person.getName());
        when(rsMock.getInt(3)).thenReturn(person.getBirthYear());

        Person person = personDao.update(t);
        assertTrue(person.equals(t));
    }

    @Test
    void delete() throws SQLException{
        Person t = new Person(65, "Danne", 1993);

        when(dbMock.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(rsMock);
        when(rsMock.getInt(1)).thenReturn(person.getId());
        when(rsMock.getString(2)).thenReturn(person.getName());
        when(rsMock.getInt(3)).thenReturn(person.getBirthYear());

        Person person = personDao.delete(t);
        assertTrue(person.equals(t));

    }
}