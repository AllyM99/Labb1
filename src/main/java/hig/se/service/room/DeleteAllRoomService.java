package hig.se.service.room;

import hig.se.db.DbConnectionManager;
import hig.se.repository.RoomDao;
import hig.se.service.BaseService;

public class DeleteAllRoomService extends BaseService<Void> {

    public Void execute() {
        RoomDao rd = new RoomDao();
        DbConnectionManager.getInstance().open();
        rd.deleteAll();
        DbConnectionManager.getInstance().close();
        return null;
    }
}
