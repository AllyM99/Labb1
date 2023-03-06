package hig.se.service.room;

import hig.se.domain.Room;
import hig.se.service.BaseService;

import java.util.List;

public class GetAllRoomService extends BaseService<List<Room>> {
    public List<Room> execute() {
        return factory.getRoomDao().getAll();

    }
}
