package Dao;

import Contact.ContactDB;
import Model.Doctor;
import Model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ArrayList<Doctor> getTop3() throws SQLException, ClassNotFoundException {
        String sql = "select top 3 doctors.*,speciality.id as speciality_name from doctors JOIN speciality on doctors.speciality_id = speciality.id ORDER by experience DESC;";
        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        while (resultSet.next()) {
            doctorArrayList.add(new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("degree"),
                    resultSet.getInt("experience"),
                    resultSet.getString("speciality_name"),
                    resultSet.getString("image"),
                    resultSet.getString("phone"),
                    resultSet.getString("dob"),
                    resultSet.getBoolean("gender"),
                    resultSet.getString("address")));
        }
        return doctorArrayList;
    }

    public boolean createDoctor(String name, String email, String password, String degree, int experience, String phone, String dob, boolean gender, String address, boolean status) {
        String sql = "insert into doctors(name,email, password,degree,experience,phone, dob, gender,address,status ) values(?, ?, ?, ?, ?,?,?, ?, ?,?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, degree);
            preparedStatement.setInt(5, experience);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, dob);
            preparedStatement.setBoolean(8, gender);
            preparedStatement.setString(9, address);
             preparedStatement.setBoolean(10, status);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createDoctornoImage(String name, String email, String password, String degree, int experience, int speciality_id, String phone, String dob, boolean gender, String address) {
        String sql = "insert into doctors(name,email, password,degree,experience,speciality_id,phone, dob, gender,address ) values(?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, degree);
            preparedStatement.setInt(5, experience);
            preparedStatement.setInt(6, speciality_id);
            preparedStatement.setString(7, phone);
            preparedStatement.setString(8, dob);
            preparedStatement.setBoolean(9, gender);
            preparedStatement.setString(10, address);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
     public ArrayList<Doctor> getAllDoctorID() throws ClassNotFoundException, SQLException {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        String sql = "select id , email from doctors";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            doctorArrayList.add(new Doctor(
                    resultSet.getInt("id"),
                    
                    resultSet.getString("email")
                    ));
        }
        return doctorArrayList;
    }


    public ArrayList<Doctor> getAllDoctor() throws ClassNotFoundException, SQLException {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        String sql = "select * from doctors";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            doctorArrayList.add(new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("degree"),
                    resultSet.getInt("experience"),
                    resultSet.getString("phone"),
                    resultSet.getString("dob"),
                    resultSet.getBoolean("gender"),
                    resultSet.getString("address"),
                    resultSet.getBoolean("status")
            ));
        }
        return doctorArrayList;
    }

    public boolean deleteDoctor(int id) {
        String sql = " UPDATE doctors SET status = ? WHERE id = ?";
        try {
            connection =  ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
      public boolean onStatus(int id) {
        String sql = " UPDATE doctors SET status = ? WHERE id = ?";
        try {
            connection =  ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Doctor> searchDoctor(String name, int spe_id) throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        connection = ContactDB.makeConnection();
        if (spe_id == 0) {
            String sql = "select doctors.*, speciality.name as speciality_name from doctors JOIN speciality on doctors.speciality_id = speciality.id where doctors.name like ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
        } else {
            String sql = "select doctors.*, speciality.name as speciality_name from doctors JOIN speciality on doctors.speciality_id = speciality.id where doctors.name like ? and speciality_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setInt(2, spe_id);
        }
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            doctorArrayList.add(new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("degree"),
                    resultSet.getInt("experience"),
                    resultSet.getString("speciality_name"),
                    resultSet.getString("image"),
                    resultSet.getString("phone"),
                    resultSet.getString("dob"),
                    resultSet.getBoolean("gender"),
                    resultSet.getString("address")));
        }
        return doctorArrayList;
    }

    public Doctor findById(int id) {
        String sql = "SELECT doctors.*, speciality.name as speciality_name FROM doctors inner join speciality on doctors.speciality_id = speciality.id where doctors.id = ?;";
        try {
            connection = new ContactDB().makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Doctor doctor = null;
            while (resultSet.next()) {
                doctor = new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("degree"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("speciality_id"),
                        resultSet.getString("speciality_name"),
                        resultSet.getString("image"),
                        resultSet.getString("phone"),
                        resultSet.getString("dob"),
                        resultSet.getBoolean("gender"),
                        resultSet.getString("address"));
            }
            return doctor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean UpdateDoctorNoImg(int id, String name, String email, String password, String degree, int experience, int speciality_id, String phone, String dob, boolean gender, String address) {
        try {
            String sql = "update doctors set name = ?, email = ?, password = ?, degree = ?, experience = ?, speciality_id = ?, phone = ?, dob = ?, gender = ?, address = ? where id = ? ;";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, degree);
            preparedStatement.setInt(5, experience);
            preparedStatement.setInt(6, speciality_id);
            preparedStatement.setString(7, phone);
            preparedStatement.setString(8, dob);
            preparedStatement.setBoolean(9, gender);
            preparedStatement.setString(10, address);
            preparedStatement.setInt(11, id);
            preparedStatement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateDoctorImg(int id, String name, String email, String password, String degree, int experience, int speciality_id, String phone, String dob, boolean gender, String address, String img) {
        try {
            String sql = "update doctors set name = ?, email = ?, password = ?, degree = ?, experience = ?, speciality_id = ?, phone = ?, dob = ?, gender = ?, address = ?,image = ?  where id = ? ;";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, degree);
            preparedStatement.setInt(5, experience);
            preparedStatement.setInt(6, speciality_id);
            preparedStatement.setString(7, phone);
            preparedStatement.setString(8, dob);
            preparedStatement.setBoolean(9, gender);
            preparedStatement.setString(10, address);
            preparedStatement.setString(11, img);
            preparedStatement.setInt(12, id);
            preparedStatement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Review> getReviewByIdDoctor(int doctorid) throws ClassNotFoundException, SQLException {
        ArrayList<Review> reviewArray = new ArrayList<>();
        String sql = "select r.* , p.name from review r, patients p \n"
                + "where r.patientid = p.id and r.doctorid = ?";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, doctorid);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            reviewArray.add(new Review(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)));
        }
        return reviewArray;
    }

    public boolean updateDoctor(String name, String degree, int experience,
            int speciality_id, String phone, String dob, boolean gender, String address, int id) {
        String sql = "update doctors set name = ?, degree = ?, experience = ?, speciality_id = ?,"
                + "phone = ?,  dob = ?, gender = ?, address = ? where id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, degree);
            preparedStatement.setInt(3, experience);
            preparedStatement.setInt(4, speciality_id);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, dob);
            preparedStatement.setBoolean(7, gender);
            preparedStatement.setString(8, address);
            preparedStatement.setInt(9, id);
            preparedStatement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassDoctor(int id, String pass) {
        String sql = "update doctors set password = ?  where id = ?";
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
    
    
     public boolean emailExist(String email) throws SQLException, ClassNotFoundException {
        String sql = "select count(id) from doctors where email = ?";
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
        String sql = "select count(id) from doctors where phone = ?";
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DoctorDao a = new DoctorDao();
        a.updateDoctor("son", "thac si", 10, 2, "099921232", "2012/12/30", true, "Quang nam", 1);

    }
}
