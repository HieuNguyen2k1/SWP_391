package Dao;

import Contact.ContactDB;
import Model.DoctorSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorScheduleDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public static String generateTime(float time) {
        String hours = "";
        if (time % 1 == 0) {
            hours = (int) time + ":00:00";
        } else {
            hours = (int) time + ":30:00";
        }
        return hours;
    }

    public String generateOneDaySql(int doctor_id, String date, float start, float end) throws SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder();
        for (float i = start; i <= end; i += 0.5) {
            sql.append("insert into doctor_schedule(doctor_id, start, [end]) values (?, ?, ?)");
        }
        return sql.toString();
    }
//                                                                      9           10
    public boolean addScheduleOneDay(int doctor_id, String date, float start, float end) {
        String start_time = date + " " + generateTime(start);
        String end_time = date + " " + generateTime(end);
        String sql = "";
        for (float i = start; i < end; i += 0.5) {
            sql += "insert into doctor_schedule(doctor_id, start, [end]) values (?, ?, ?);";
        }
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            int paraNum = 1;
            for (float i = start; i < end; i += 0.5){
                preparedStatement.setInt(paraNum, doctor_id);
                preparedStatement.setString(paraNum + 1, date + " " + generateTime(i));
                preparedStatement.setString(paraNum + 2, date + " " + generateTime((float) (i + 0.5)));
                paraNum += 3;
            }
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean checkPast(ArrayList<String> arrayList){
        Date current_date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date first_date = simpleDateFormat.parse(arrayList.get(0));
            int compare = current_date.compareTo(first_date);
            System.out.println(first_date);
            System.out.println(current_date);
            System.out.println(compare);
            if (compare>0){
                return false;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public boolean checkValidTIme(ArrayList<String> arrayList, int doc_id) {
        if (!checkPast(arrayList)){
            return false;
        }
        StringBuilder sql = new StringBuilder("SELECT * from doctor_schedule WHERE start IN (");
        for (int i = 0; i < arrayList.size(); i++) {
            sql.append(i == 0 ? "?" : ", ?");
        }
        sql.append(") and doctor_id = ?");
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < arrayList.size(); i++) {
                preparedStatement.setString(i + 1, arrayList.get(i));
            }
            preparedStatement.setInt(arrayList.size() + 1, doc_id);
            resultSet = preparedStatement.executeQuery();
            boolean check = resultSet.next();
            return !check;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<LocalDate> getDatesInRange(String startDateString, String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_LOCAL_DATE);

        List<LocalDate> datesInRange = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            datesInRange.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return datesInRange;
    }

    public boolean addManyDays(int doctor_id, String start_date, String end_day, float start, float end) {
        List<LocalDate> datesInRange = getDatesInRange(start_date, end_day);
        String sql = "";
        for (LocalDate date : datesInRange) {
            for (float i = start; i < end; i += 0.5) {
                sql += "insert into doctor_schedule(doctor_id, start, [end]) values (?, ?, ?);";
            }
        }
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            int paraNum = 1;
            for (LocalDate date : datesInRange) {
                for (float i = start; i < end; i += 0.5) {
                    preparedStatement.setInt(paraNum, doctor_id);
                    preparedStatement.setString(paraNum + 1, date + " " + generateTime(i));
                    preparedStatement.setString(paraNum + 2, date + " " + generateTime((float) (i + 0.5)));
                    paraNum += 3;
                }
            }
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addSchedule(int doctor_id, String date, int start, int end) {
        String time_start = date + " " + start + ":00:00";
        String time_end = date + " " + end + ":00:00";
        String sql = "insert into doctor_schedule(doctor_id, start, [end]) values(?, ?, ?)";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctor_id);
            preparedStatement.setString(2, time_start);
            preparedStatement.setString(3, time_end);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<DoctorSchedule> getScheduleOfWeek(int doctor_id, String first_date_week, String last_date_week) {
        ArrayList<DoctorSchedule> doctorScheduleArrayList = new ArrayList<DoctorSchedule>();
        first_date_week = first_date_week + " 07:00:00";
        last_date_week = last_date_week + " 18:00:00";
        String sql = "SELECT doctor_schedule.*, appointments.id as app_id, appointments.* from doctor_schedule left join appointments on doctor_schedule.id = appointments.doctor_schedule_id where doctor_id = ? and start > ? and [end] < ?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctor_id);
            preparedStatement.setString(2, first_date_week);
            preparedStatement.setString(3, last_date_week);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                doctorScheduleArrayList.add(new DoctorSchedule(
                        resultSet.getInt("id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getInt("app_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getString("status"),
                        resultSet.getString("note")
                        )
                );
            }
            return doctorScheduleArrayList;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("2023-6-19 11:00:00");
        System.out.println(checkPast(arrayList));
    }
    public boolean deleteSchedule(int id){
        String sql = "delete from doctor_schedule where id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
