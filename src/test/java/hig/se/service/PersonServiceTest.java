package hig.se.service;

import hig.se.domain.Person;
import hig.se.repository.PersonDao;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    static PersonService dao;
    static PersonDao personDaoMock;
    static int id;
    static int birthYear;
    static String name;


    @BeforeAll
    public static void setUpClass() {
        personDaoMock = mock(PersonDao.class);
        when(personDaoMock.getAll()).thenReturn(List.of(new Person(id, name, birthYear), new Person(id, name, birthYear)));
        dao = new PersonService();

    }

    @AfterAll
    public static void tearDownClass() {
        dao = null;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
        id = 2;
        birthYear = 1999;
        name = "Ali";
        when(personDaoMock.get(id)).thenReturn(new Person(2, "Ali", 1999));
        PersonService ps = new PersonService(personDaoMock);
        Person exResult = new Person(2, "Ali", 1999);
        Person res = ps.get(id);
        assertTrue(exResult.equals(res));
        verify(personDaoMock, times(1)).get(id);
    }

    @Test
    void getAll() {

        id = 2;
        birthYear = 1999;
        name = "Ali";
        when(personDaoMock.getAll()).thenReturn(List.of(new Person(2, "Ali", 1999)));
        PersonService ps = new PersonService(personDaoMock);
        List<Person> exRes = List.of(new Person(2, "Ali", 1999));
        List<Person> res = ps.getAll();
        assertTrue(res.equals(exRes));
        verify(personDaoMock, times(1)).getAll();
    }

    @Test
    void save() {

        Person person = new Person(1,"David", 2000);
        when(personDaoMock.save(person)).thenReturn(new Person(4,"Åke", 1989));
        PersonService ps = new PersonService(personDaoMock);
        Person res = ps.save(person);
        Person exRes = new Person(4,"Åke", 1989);
        assertTrue(res.equals(exRes));
        verify(personDaoMock, times(1)).save(person);

    }

    @Test
    void update() {
        Person person = new Person(24, "Lolo", 2000);
        when(personDaoMock.update(person)).thenReturn(new Person(1,"Ali", 1999));
        PersonService ps = new PersonService(personDaoMock);
        Person res = ps.update(person);
        Person exRes = new Person(1,"Ali",1999);
        assertTrue(res.equals(exRes));
        verify(personDaoMock, times(1)).update(person);
    }

    @Test
    void delete() {
        Person person = new Person(5, "dada", 1832);
        when(personDaoMock.delete(person)).thenReturn(person);
        PersonService ps = new PersonService(personDaoMock);
        Person exRes = person;
        Person res = ps.delete(person);

        assertTrue(res.equals(exRes));
        verify(personDaoMock,times(1)).delete(person);

    }
}