package hig.se.spike;

import hig.se.domain.Room;
import hig.se.service.ServiceRunner;
import hig.se.service.room.GetAllRoomService;

import java.util.List;

public class GetAllRoom {
    public static void main(String[] args) {
        ServiceRunner<List<Room>> serviceRunner = new ServiceRunner<>(new GetAllRoomService());
        List<Room> roomsList = serviceRunner.execute();
        for (Room p : roomsList) {
            System.out.println(p.getRoomType());
        }
    }
    }
