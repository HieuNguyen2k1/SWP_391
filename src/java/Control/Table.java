package Control;

import Dao.AppointmentDao;
import Dao.DoctorScheduleDao;
import Model.Appointment;
import Model.DoctorSchedule;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Table {
    public static String[] getDateOfWeek(int year, int week) {
        //khoi tao mang 8 ki tu (0-7)
        String[] dateArray = new String[8];
        //LocalDate được tạo ra từ LocalDate.ofYearDay(year, 1) để đại diện cho ngày đầu tiên của năm. .monday de di chuyen den thu 2 cua tuan do
        //plusweek them so tuan vao ngay dau tuan de tinh cho ngay dau tien cua tuan do.
        LocalDate firstDayOfWeek = LocalDate.ofYearDay(year, 1).with(DayOfWeek.MONDAY).plusWeeks(week);
        for (int i = 1; i < 8; i++) {
            //plusday để tính ra t
            LocalDate date = firstDayOfWeek.plusDays(i - 1);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateArray[i] = formattedDate;
        }
        dateArray[0] = "null";
        return dateArray;
    }

    public static String[][] createScheduleTableForDoctor(int year, int week, int doctor_id) {
        String[][] table = new String[11][8];
        table[0] = new String[]{"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] dateArray = getDateOfWeek(year, week);
        table[1] = dateArray;
        String first_date_week = dateArray[1];
        String last_date_week = dateArray[7];
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        ArrayList<DoctorSchedule> doctorScheduleArrayList = doctorScheduleDao.getScheduleOfWeek(doctor_id, first_date_week, last_date_week);
        for (int i = 2; i < 11; i++) {
            table[i][0] = (i + 6) + "h -> " + (i + 7) + "h";
        }
        if (doctorScheduleArrayList == null) {
            return table;
        } else {
            //duyet qua tat ca gio
            for (int i = 2; i < 11; i++) {  
                //duyet qua tat ca ngay 
                for (int j = 1; j < 8; j++) {
                    int table_date = Integer.parseInt(table[1][j].split("-")[2]);
                    for (int k = 0; k < doctorScheduleArrayList.size(); k++) {
                        int start_time = Integer.parseInt(doctorScheduleArrayList.get(k).start.split(" ")[1].split(":")[0]);
                        int end_time = Integer.parseInt(doctorScheduleArrayList.get(k).end.split(" ")[1].split(":")[0]);
                        int date = Integer.parseInt(doctorScheduleArrayList.get(k).start.split(" ")[0].split("-")[2]);
                        if (table_date == date) {
                            if ((i + 6) >= start_time && (i + 7) <= end_time) {
                                table[i][j] = "doctor busy";
                            }
                        }
                    }
                }
            }
        }
        return table;
    }

    public static String[][] createScheduleTableForDoctorTest(int year, int week, int doctor_id) throws ParseException {
        String[][] table = new String[20][8];
        table[0] = new String[]{"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] dateArray = getDateOfWeek(year, week);
        table[1] = dateArray;
        String first_date_week = dateArray[1];
        String last_date_week = dateArray[7];
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        ArrayList<DoctorSchedule> doctorScheduleArrayList = doctorScheduleDao.getScheduleOfWeek(doctor_id, first_date_week, last_date_week);
        float time = 8;
        for (int i = 2; i < 20; i++) {
            if (time % 1 == 0) {
                table[i][0] = (int)time + ":00:00->" + (int)time + ":30:00";
            } else {
                int temp = (int) time;
                table[i][0] = temp + ":30:00->" + (temp + 1) + ":00:00";
            }
            time += 0.5;
        }
        if (doctorScheduleArrayList == null) {
            return table;
        } else {
            for (int i = 2; i < 20; i++) {
                for (int j = 1; j < 8; j++) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date cell_time_start = simpleDateFormat.parse(table[1][j] + " " + table[i][0].split("->")[0]);
                    Date cell_time_end = simpleDateFormat.parse(table[1][j] + " " + table[i][0].split("->")[1]);
                    for (int k = 0; k < doctorScheduleArrayList.size(); k++) {
                        Date doctor_schedule_start = simpleDateFormat.parse(doctorScheduleArrayList.get(k).start);
                        Date doctor_schedule_end = simpleDateFormat.parse(doctorScheduleArrayList.get(k).end);
                        if (cell_time_start.compareTo(doctor_schedule_start) >=0 && cell_time_end.compareTo(doctor_schedule_end) <=0){
                            if (doctorScheduleArrayList.get(k).getApp_id() != 0){ // co hen
                                table[i][j] = "patient app|" + doctorScheduleArrayList.get(k).getApp_id() + "|" + doctorScheduleArrayList.get(k).getStatus() + "|" + doctorScheduleArrayList.get(k).id;
                                // patient app|1|finished|1
                            } else {
                                table[i][j] = "doctor schedule|" + doctorScheduleArrayList.get(k).id;
                            }
                        }
                    }
                }
            }
        }
        return table;
    }

    public static String[][] createTablePatientWithDoctor(int year, int week, int doctor_id, int patient_id) throws SQLException, ClassNotFoundException {
        String[][] table = new String[11][8];
        table[0] = new String[]{"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] dateArray = getDateOfWeek(year, week);
        table[1] = dateArray;
        String first_date_week = dateArray[1];
        String last_date_week = dateArray[7];
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        ArrayList<DoctorSchedule> doctorScheduleArrayList = doctorScheduleDao.getScheduleOfWeek(doctor_id, first_date_week, last_date_week);
        ArrayList<Appointment> appointments = new AppointmentDao().getAppointmentPatientWithDoctor(patient_id, doctor_id);
        for (int i = 2; i < 11; i++) {
            table[i][0] = (i + 6) + "h -> " + (i + 7) + "h";
        }
        if (doctorScheduleArrayList == null) {
            return table;
        } else {
            for (int i = 2; i < 11; i++) {
                for (int j = 1; j < 8; j++) {
                    int table_date = Integer.parseInt(table[1][j].split("-")[2]);
                    for (int k = 0; k < doctorScheduleArrayList.size(); k++) {
                        int date = Integer.parseInt(doctorScheduleArrayList.get(k).start.split(" ")[0].split("-")[2]);
                        if (table_date == date) {
                            for (int l = 0; l < appointments.size(); l++) {
                                int user_appointment_start = Integer.parseInt(appointments.get(l).start.split(" ")[1].split(":")[0]);
                                int user_appointment_date = Integer.parseInt(appointments.get(l).start.split(" ")[0].split("-")[2]);
                                if ((i + 6) == user_appointment_start && user_appointment_date == table_date) {
                                    table[i][j] = "doctor schedule";
                                }
                            }
                            if (doctorScheduleArrayList.get(k).getApp_id() != 0){
                                table[i][j] = "appointment with patient";
                            }
                        }

                    }
                }
            }
        }
        return table;
    }
}
