package hig.se.repository;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Room;
import hig.se.domain.RoomType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RoomDao implements Dao<Room> {

    DbConnectionManager dbConManagerSingleton;

    public RoomDao() {
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }

    @Override

    public Room get(int id) throws EntityNotFoundException {
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, roomNumber, roomType, floor FROM room WHERE id=" + id);
            if (!resultSet.next())
                throw new EntityNotFoundException(id);
            else {
                return new Room(resultSet.getInt(1), resultSet.getInt(2),
                        RoomType.valueOf(resultSet.getString(3)),
                        resultSet.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getAll() {
        ArrayList<Room> list = new ArrayList<>();

        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, roomNumber, roomType, floor FROM room");
            while (resultSet.next()) {
                list.add(new Room(resultSet.getInt(1),
                                resultSet.getInt(2),
                                RoomType.valueOf(resultSet.getString(3)),
                                resultSet.getInt(4)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Room save(Room room) {

        String sql = "INSERT INTO room (roomNumber, roomType, floor) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setString(2, room.getRoomType().toString());
            preparedStatement.setInt(3, room.getFloor());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Room update(Room room) {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE room set roomNumber = ?, roomType = ?, floor = ? where id = ? ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setString(2, room.getRoomType().toString());
            preparedStatement.setInt(3, room.getFloor());
            preparedStatement.setInt(4, room.getId());

            preparedStatement.executeUpdate();

            System.out.println("The room with id: " + room.getId() + " information has been updated!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

    @Override
    public Room delete(Room room) {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM room WHERE id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, room.getId());
            preparedStatement.executeUpdate();

            System.out.println("The room with id: " + room.getId() + " has been removed!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

    @Override
    public void deleteAll() {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConManagerSingleton.prepareStatement("DELETE FROM room", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            System.out.println("The whole table has been cleaned up");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



