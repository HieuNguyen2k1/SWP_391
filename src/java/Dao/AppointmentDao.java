package Dao;

import Contact.ContactDB;
import Model.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
public class AppointmentDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public int cancelAppointment(int app_id) throws SQLException, ClassNotFoundException, ParseException {
        Data data = adminGetDetail(app_id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date end = simpleDateFormat.parse(((String[])(data.object3))[1]);
        Date date = new Date();
        int compare = date.compareTo(end);
        if (compare > 0){
            return 0;//lịch của quá khứ
        } else {
            String sql = "select * FROM appointments WHERE id = ?";
            try {
                connection = ContactDB.makeConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, app_id);
                resultSet = preparedStatement.executeQuery();
                Appointment appointment;
                if (resultSet.next()){
                    appointment = new Appointment(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    );
                    if (Status.valueOf(appointment.status) == Status.finished){
                        return -1;// đã khám thì khỏi xoá
                    } else {
                        connection = null;
                        preparedStatement = null;
                        resultSet = null;
                        sql = "delete FROM appointments WHERE id = ?";
                        connection = ContactDB.makeConnection();
                        assert connection != null;
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, app_id);
                        int row = preparedStatement.executeUpdate();
                        if (row > 0){
                            return 1;
                        }
                    }
                } else {
                    return -2;// id tào lao
                }
            } catch (Exception e){
                e.printStackTrace();
                return -3; // lỗi sql
            }
        }
        return 2;
    }
    public Data getAvailableAppointment(int doctor_id) throws SQLException, ClassNotFoundException {
        // lấy all lịch của bs từ giờ -> hết
        // lấy lịch của bệnh nhân từ giờ
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String sql = "select * from doctor_schedule left join appointments on doctor_schedule.id = appointments.doctor_schedule_id where appointments.id is null and doctor_schedule.start > ? and doctor_schedule.doctor_id = ? order by doctor_schedule.start; select doctors.*, speciality.name as speciality_name from doctors JOIN speciality on doctors.speciality_id = speciality.id where doctors.id = ?;";
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, formattedDateTime);
        preparedStatement.setInt(2, doctor_id);
        preparedStatement.setInt(3, doctor_id);
        boolean check = preparedStatement.execute();
        ArrayList<DoctorSchedule> arrayList = new ArrayList<>();
        Doctor doctor = new Doctor();
        ResultSet resultSet = preparedStatement.getResultSet();
        if (check){
            while (resultSet.next()){
                arrayList.add(new DoctorSchedule(
                        resultSet.getInt("id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("start"),
                        resultSet.getString("end")
                ));
            }
            if (preparedStatement.getMoreResults()){
                resultSet = preparedStatement.getResultSet();
                while (resultSet.next()){
                    doctor = new Doctor(
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
                            resultSet.getString("address")
                    );
                }
            }
        }
        Data data = new Data(arrayList, doctor);
        return data;
    }
    public boolean addAppointment(int patient_id, String note, int doctor_schedule_id) throws SQLException, ClassNotFoundException {
        String sql = "insert into appointments(patient_id, note, status, doctor_schedule_id) values(?, ?, ?, ?)";
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, patient_id);
        preparedStatement.setString(2, note);
        preparedStatement.setString(3, Status.not_yet.toString());
        preparedStatement.setInt(4, doctor_schedule_id);
        return preparedStatement.execute();
    }
    public ArrayList<Appointment> getAppointmentPatientWithDoctor(int patient_id, int doctor_id) throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "select appointments.*, doctor_schedule.doctor_id, doctor_schedule.start, doctor_schedule.[end] FROM appointments JOIN doctor_schedule on appointments.doctor_schedule_id = doctor_schedule.id where appointments.patient_id = ? and doctor_schedule.doctor_id = ? order by start;";
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,patient_id);
        preparedStatement.setInt(2,doctor_id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            appointments.add(new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getInt("patient_id"),
                    resultSet.getString("status"),
                    resultSet.getString("note"),
                    resultSet.getInt("doctor_schedule_id"),
                    resultSet.getInt("doctor_id"),
                    resultSet.getString("start"),
                    resultSet.getString("end")
            ));
        }
        return appointments;
    }
    public Data getDetail(int id, int doc_id) throws SQLException, ClassNotFoundException {
        String sql = "select appointments.*, patients.*, doctor_schedule.* from appointments inner join patients on patients.id = appointments.patient_id inner join doctor_schedule on doctor_schedule.id = appointments.doctor_schedule_id where appointments.id = ?;";
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            if (resultSet.getInt("doctor_id") != doc_id){
                return null;
            } else {
                Appointment appointment = new Appointment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                User user = new User(
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getBoolean(12),
                        resultSet.getString(13),
                        resultSet.getBoolean(14),
                        resultSet.getString(15),
                        resultSet.getBoolean(16)
                );
                Data data = new Data(appointment, user, new String[]{resultSet.getString("start"), resultSet.getString("end")});
                return data;
            }
        }
        return null;
    }
    public Data adminGetDetail(int id) throws SQLException, ClassNotFoundException {
        String sql = "select appointments.*, patients.*, doctor_schedule.* from appointments inner join patients on patients.id = appointments.patient_id inner join doctor_schedule on doctor_schedule.id = appointments.doctor_schedule_id where appointments.id = ?;";
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
                Appointment appointment = new Appointment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                User user = new User(
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getBoolean(12),
                        resultSet.getString(13),
                        resultSet.getBoolean(14),
                        resultSet.getString(15),
                        resultSet.getBoolean(16)
                );
                Data data = new Data(appointment, user, new String[]{resultSet.getString("start"), resultSet.getString("end")});
                return data;
        }
        return null;
    }
    public boolean updateAppointmentStatus(String status, int app_id, int doc_id){
        String sql = "select doctors.id from appointments join doctor_schedule on doctor_schedule.id = appointments.doctor_schedule_id join doctors on doctors.id = doctor_schedule.doctor_id where appointments.id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,app_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getInt("id") == doc_id){
                    sql = "update appointments set status = ? where id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, status);
                    preparedStatement.setInt(2, app_id);
                    preparedStatement.execute();
                    return true;
                } else return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean adminUpdateAppointmentStatus(String status, int app_id){
        try {
            String sql = "update appointments set status = ? where id = ?";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, app_id);
            return preparedStatement.execute();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Appointment> getAllAppointmentsOfPatient(int user_id){
        ArrayList<Appointment> appointments = new ArrayList<>();
        try{
            String sql = "select appointments.*, doctor_schedule.*, patients.* from appointments inner join patients on patients.id = appointments.patient_id inner join doctor_schedule on doctor_schedule.id = appointments.doctor_schedule_id where appointments.patient_id = ?";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                appointments.add(new Appointment(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    Status.valueOf(resultSet.getString(3)).getDetail(),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt("doctor_id"),
                    resultSet.getString("name"),
                    resultSet.getString("start"),
                    resultSet.getString("end")
                ));
            }
            return appointments;
        }catch (Exception e){
            e.printStackTrace();
        }
        return appointments;
    }
    public ArrayList<Appointment> getAllAppointmentOfDoctor(int doc_id){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "select *, patients.name as username from appointments inner join doctor_schedule on appointments.doctor_schedule_id = doctor_schedule.id inner join patients on appointments.patient_id = patients.id where doctor_schedule.doctor_id = ? and appointments.status = 'finished'";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doc_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                appointments.add(new Appointment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getString("username")
                ));
            }
            return appointments;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
