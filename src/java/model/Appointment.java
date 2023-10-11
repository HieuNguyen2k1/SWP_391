package Model;

public class Appointment {
    public int id;
    public int patient_id;
    public String status;
    public String note;
    public int doctor_schedule_id;
    public int doctor_id;
    public String doctor_name;
    public String start;
    public String end;
    public String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDoctor_schedule_id() {
        return doctor_schedule_id;
    }

    public void setDoctor_schedule_id(int doctor_schedule_id) {
        this.doctor_schedule_id = doctor_schedule_id;
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

    public Appointment(int id, int patient_id, String status, String note, int doctor_schedule_id) {
        this.id = id;
        this.patient_id = patient_id;
        this.status = status;
        this.note = note;
        this.doctor_schedule_id = doctor_schedule_id;
    }

    public Appointment(int id, int patient_id, String status, String note, int doctor_schedule_id, int doctor_id, String start, String end) {
        this.id = id;
        this.patient_id = patient_id;
        this.status = status;
        this.note = note;
        this.doctor_schedule_id = doctor_schedule_id;
        this.doctor_id = doctor_id;
        this.start = start;
        this.end = end;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Appointment(int id, int patient_id, String status, String note, int doctor_schedule_id, int doctor_id, String doctor_name, String start, String end) {
        this.id = id;
        this.patient_id = patient_id;
        this.status = status;
        this.note = note;
        this.doctor_schedule_id = doctor_schedule_id;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.start = start;
        this.end = end;
    }
    public Appointment(int id, int patient_id, String status, String note, int doctor_schedule_id, String start, String end, String username) {
        this.id = id;
        this.patient_id = patient_id;
        this.status = status;
        this.note = note;
        this.doctor_schedule_id = doctor_schedule_id;
        this.start = start;
        this.end = end;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", doctor_schedule_id=" + doctor_schedule_id +
                ", doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
