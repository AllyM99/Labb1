package hig.se.repository;

import hig.se.db.DbConnectionManager;
import hig.se.domain.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class EntityNotFoundException extends NoSuchElementException{
    public EntityNotFoundException(int id) {
        super("Entity with id not found");
    }
}

public class PersonDao implements Dao<Person> {

    DbConnectionManager dbConManagerSingleton;

    public PersonDao(DbConnectionManager dbConManagerSingleton ) {
        this.dbConManagerSingleton = dbConManagerSingleton;
    }
    public PersonDao(){
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }

    @Override
    public Person get(int id) throws EntityNotFoundException {
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM persons WHERE id=" + id);
            if (!resultSet.next())
                throw new EntityNotFoundException(id);
            else
            {
                return new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> getAll() {

        ArrayList<Person> list = new ArrayList<>();

        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM persons");
            while (resultSet.next()) {
                list.add(new Person(resultSet.getInt(1),
                        resultSet.getString(2).trim(),
                        resultSet.getInt(3))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Person save(Person t) {
        String sql = "INSERT INTO persons (name, birth_year) VALUES (?, ?)";
            try{ PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement(
                sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getBirthYear());
            preparedStatement.executeUpdate();

           ResultSet rs = preparedStatement.getGeneratedKeys();
           if (rs.next()){
               int id = rs.getInt(1);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return t;
    }

    @Override
    public Person update(Person t) {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE persons set name = ?, birth_year = ? where id = ? ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getBirthYear());
            preparedStatement.setInt(3, t.getId());
            preparedStatement.executeUpdate();

            System.out.println("The person with id: " + t.getId() + " information has been updated!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t;

    }


    @Override
    public Person delete(Person t) {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM persons WHERE id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, t.getId());
            preparedStatement.executeUpdate();

            System.out.println("The person with id: " + t.getId() + " has been removed!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t;

    }

    @Override
    public void deleteAll(){
        PreparedStatement preparedStatement;

        try{
            preparedStatement = dbConManagerSingleton.prepareStatement("DELETE FROM persons", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            System.out.println("The whole table has been deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


