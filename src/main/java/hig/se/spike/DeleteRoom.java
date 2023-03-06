package hig.se.spike;


import hig.se.domain.Room;
import hig.se.domain.RoomType;
import hig.se.service.ServiceRunner;
import hig.se.service.room.DeleteRoomService;

public class DeleteRoom {
    public static void main(String[] args) {
        RoomType king = RoomType.KING;
        Room room = new Room(50,5,king, 3);

        ServiceRunner<Room> serviceRunner = new ServiceRunner<>(new DeleteRoomService(room));
        serviceRunner.execute();
    }
}
