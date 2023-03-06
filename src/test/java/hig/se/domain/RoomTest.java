package hig.se.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    static Room room;

    @BeforeAll
    public static void setUpClass() {
        RoomType king = RoomType.KING;
        room = new Room(1, 2, king, 2);

    }

    @AfterAll
    public static void tearDownClass() {
        room = null;
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
        int res = room.getId();
        assertEquals(exId, res);
    }

    @Test
    void testRoomNumber() {
        int exRoomNumber = 2;
        int res = room.getRoomNumber();

        //getRoomNumber
        assertEquals(exRoomNumber, res);

        //Setroomnumber > than 499
        assertThrows(IllegalArgumentException.class, () -> room.setRoomNumber(500));
        //Setroomnumber < than 0
        assertThrows(IllegalArgumentException.class, () -> room.setRoomNumber(-1));
    }

    @Test
    void testRoomType() {
        RoomType king = RoomType.KING;
        RoomType res = room.getRoomType();

        assertEquals(king, res);
    }

    @Test
    void testFloor() {
        int exroomFlor = 2;
        int res = room.getFloor();

        //GetFloor
        assertEquals(exroomFlor, res);

        //Setroomnumber > than 99
        assertThrows(IllegalArgumentException.class, () -> room.setFloor(100));
        //Setroomnumber < than 0
        assertThrows(IllegalArgumentException.class, () -> room.setFloor(-1));
    }


}