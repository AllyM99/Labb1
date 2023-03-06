package hig.se.spike;

import hig.se.domain.Room;
import hig.se.domain.RoomType;
import hig.se.service.ServiceRunner;
import hig.se.service.room.UpdateRoomService;

public class UpdateRoom {

    public static void main(String[] args) {
        RoomType single = RoomType.SINGLE;
        RoomType king = RoomType.KING;
        Room room = new Room(51, 5, king, 3);
        room.setFloor(21);
        room.setRoomNumber(32);
        room.setRoomType(single);
        ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new UpdateRoomService(room));
        serviceRunner.execute();


    }
}
