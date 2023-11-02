package Model;

//import javax.xml.crypto.Data;
import java.util.Date;

public class DoctorSchedule {
    public int id;
    public int doctor_id;
    public String start;
    public String end;
    public int app_id;
    public int patient_id;
    public String status;
    public String status_app;

    public String getStatus_app() {
        return status_app;
    }

    public void setStatus_app(String status_app) {
        this.status_app = status_app;
    }
    public String note;

    public DoctorSchedule(int id, int doctor_id, String start, String end, int app_id, int patient_id, String status, String note) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.start = start;
        this.end = end;
        this.app_id = app_id;
        this.patient_id = patient_id;
        this.status = status;
        this.note = note;
    }
    
      public DoctorSchedule(int id, int doctor_id, String start, String end, int app_id, int patient_id, String status_schedule,String status_app, String note) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.start = start;
        this.end = end;
        this.app_id = app_id;
        this.patient_id = patient_id;
        this.status = status_schedule;
          this.status_app = status_app;
        this.note = note;
    }
    
   

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public DoctorSchedule(int id, int doctor_id, String start, String end) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

//    @Override
//    public String toString() {
//        return "DoctorSchedule{" +
//                "id=" + id +
//                ", doctor_id=" + doctor_id +
//                ", start='" + start + '\'' +
//                ", end='" + end + '\'' +
//                ", app_id=" + app_id +
//                ", patient_id=" + patient_id +
//                ", status='" + status + '\'' +
//                ", note='" + note + '\'' +
//                '}';
//    }
@Override
public String toString() {
    String sb = "{" +
            "\"id\":" + id + "," +
            "\"doctor_id\":" + doctor_id + "," +
            "\"start\":\"" + start + "\"," +
            "\"end\":\"" + end + "\"," +
            "\"app_id\":" + app_id + "," +
            "\"patient_id\":" + patient_id + "," +
            "\"status\":\"" + status + "\"," +
            "\"note\":\"" + note + "\"" +
            "}";
    return sb;
}
}
