package hig.se.service;

import hig.se.domain.Room;
import hig.se.repository.RoomDao;

import java.util.List;

public class RoomService {
    RoomDao dao;

    public RoomService(RoomDao dao) {
        this.dao = dao;
    }

    public RoomService() {
        dao = new RoomDao();
    }

    public Room get(int id) {
        return dao.get(id);
    }

    public List<Room> getAll() {
        return dao.getAll();
    }

    public Room save(Room room) {
        return dao.save(room);
    }

    public void update(Room room) {
        dao.update(room);
    }

    public void delete(Room room) {
        dao.delete(room);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

}
