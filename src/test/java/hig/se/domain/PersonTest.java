package hig.se.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    static Person person;

    @BeforeAll
    public static void setUpClass() {
        person = new Person(1, "Ali", 1999);

    }

    @AfterAll
    public static void tearDownClass() {
        person = null;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testId() {
        int exId = 1;
        int res = person.getId();

        assertEquals(exId, res);
    }


    @Test
    void testName() {
        String exResult = "Ali";
        String result = person.getName();

        //getName
        assertEquals(exResult, result);

        //Setname to Null
        assertThrows(IllegalArgumentException.class, () -> person.setName(null));

        //setname to empty
        assertThrows(IllegalArgumentException.class, () -> person.setName("     "));

        //setname to empty
        assertThrows(IllegalArgumentException.class, () -> person.setName(""));

    }


    @Test
    void testBirthYear() {
        int exBirthYear = 1999;
        int res = person.getBirthYear();


        //GetBirthYear
        assertEquals(exBirthYear, res);

        //Set birthyear to > current year
        assertThrows(IllegalArgumentException.class, () -> person.setBirthYear(2024));


    }

}