package hig.se.spike;

import hig.se.domain.Room;
import hig.se.domain.RoomType;
import hig.se.service.ServiceRunner;
import hig.se.service.room.AddRoomService;

public class AddRoom {

    public static void main(String[] args) {
        RoomType king = RoomType.KING;
        Room room = new Room(5, king, 3);
        ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new AddRoomService(room));
        serviceRunner.execute();
    }
}
