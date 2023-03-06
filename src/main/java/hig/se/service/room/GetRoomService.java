package hig.se.service.room;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Room;
import hig.se.repository.RoomDao;
import hig.se.service.BaseService;
import hig.se.service.CleaningManagerServiceException;

import java.util.NoSuchElementException;

public class GetRoomService extends BaseService<Room> {

    int id;
    public GetRoomService(int id) {
        this.id = id;
    }

    @Override
    public Room execute() {
        RoomDao rd = new RoomDao();
        DbConnectionManager.getInstance().open();
        try {
            return rd.get(id);
        } catch (NoSuchElementException e) {
            throw new CleaningManagerServiceException(e.getMessage());
        } finally {
            DbConnectionManager.getInstance().close();
        }
    }

}
