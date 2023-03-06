package hig.se.service.room;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Room;
import hig.se.repository.RoomDao;
import hig.se.service.BaseService;

public class UpdateRoomService extends BaseService<Room> {
    Room room;
    public UpdateRoomService(Room room){
        this.room = room;
    }
    @Override
    public Room execute() {
        RoomDao rd = new RoomDao();
        DbConnectionManager.getInstance().open();
        Room room1 = rd.update(room);
        DbConnectionManager.getInstance().close();
        return room1;
    }
}
