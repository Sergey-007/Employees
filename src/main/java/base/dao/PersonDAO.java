package base.dao;

import base.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/test_bdbt";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Sergey";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setReason(resultSet.getInt("reason"));
                person.setStart_date(resultSet.getDate("start_date"));
                person.setDuration(resultSet.getInt("duration"));
                person.setDiscounted(resultSet.getBoolean("discounted"));
                person.setDescription(resultSet.getString("description"));

                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        Person person = new Person();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    person.setId(resultSet.getInt("id"));
                    person.setReason(resultSet.getInt("reason"));
                    person.setStart_date(resultSet.getDate("start_date"));
                    person.setDuration(resultSet.getInt("duration"));
                    person.setDiscounted(resultSet.getBoolean("discounted"));
                    person.setDescription(resultSet.getString("description"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
   }

    public void save(Person person) {

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES (DEFAULT," + person.getReason() +
                    "," + "'" + person.getStart_date() + "'," + person.getDuration() + "," +
                    person.isDiscounted() + ",'" + person.getDescription() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            Statement statement = connection.createStatement();

            String SQL = "UPDATE Person SET reason = "
                    + updatedPerson.getReason() + "," + "start_date ="
                    + "'" + updatedPerson.getStart_date() + "'," + "duration ="
                    + updatedPerson.getDuration() + "," + "discounted ="
                    + updatedPerson.isDiscounted() + "," + "description ="
                    + "'" + updatedPerson.getDescription() + "'"
                    + "WHERE id = " + id + ";";

            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE from Person where id=" + id + "";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
