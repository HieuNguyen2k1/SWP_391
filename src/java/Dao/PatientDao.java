package Dao;

import Contact.ContactDB;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public boolean createUser(String name, String email, String password, String phone, String verify_key, String address, String dob, boolean gender) throws ClassNotFoundException {
        String sql = "insert into patients(name, email, password, phone, dob, gender, address, is_admin, verify_key, is_verified) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, dob);
            preparedStatement.setBoolean(6, gender);
            preparedStatement.setString(7, address);
            preparedStatement.setBoolean(8, false);
            preparedStatement.setString(9, verify_key);
            preparedStatement.setBoolean(10, false);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean emailExist(String email) throws SQLException, ClassNotFoundException {
        String sql = "select count(id) from patients where email = ?";
        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int status = resultSet.getInt(1);
            if (status == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean phoneExist(String phone) throws SQLException, ClassNotFoundException {
        String sql = "select count(id) from patients where phone = ?";
        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, phone);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int status = resultSet.getInt(1);
            if (status == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public User getPatientById(int id) {
        String query = "select * from patients where id = ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(10), resultSet.getBoolean(11));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public User getPatientByKey(String key) {
        String query = "select * from patients where verify_key = ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(10), resultSet.getBoolean(11));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean updateUser(int id, String name, String email, String password, String dob, boolean gender, String address, String phone) {
        String sql = "update patients set name = ?, email = ?, password = ?, dob = ?, gender = ?, address = ?, phone = ? where id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, dob);
            preparedStatement.setBoolean(5, gender);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, phone);
            preparedStatement.setInt(8, id);
            preparedStatement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertReview(String cmt, int rating, int doctorid, int patientID) {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);

        String query = "insert into review(cmt, rating, dateUp ,doctorid, patientID)\n"
                + " values(?,?,?,?,?);";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cmt);
            preparedStatement.setInt(2, rating);
            preparedStatement.setString(3, formattedDate);
            preparedStatement.setInt(4, doctorid);
            preparedStatement.setInt(5, patientID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void getReviewbyId(int patientID) {
        String query = "select * from Review where patientid = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientID);
            preparedStatement.executeQuery();
        } catch (Exception e) {
        }
    }

    public boolean updatePassPatient(int id, String pass) {
        String sql = "update patients set password = ? where id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, pass);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean activeById(int id) {
        String sql = "update patients set is_verified = ? where id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmailTel(String email, String tel) {
        String sql = "select * from patients where email = ? and phone = ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, tel);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKey(String key, String email, String tel) {
        String sql = "update patients set verify_key = ? where email = ? and phone = ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, tel);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getPassword(String key, String password) {
        String sql = "update patients set password = ? where verify_key = ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, key);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void resetKey(String key) {
        String sql = "update patients set verify_key = ? where verify_key = ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "");
            preparedStatement.setString(2, key);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PatientDao pt = new PatientDao();
//        pt.insertReview("1", "2", 1, 1);
    }
}
