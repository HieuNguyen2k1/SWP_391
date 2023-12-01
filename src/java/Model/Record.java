/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Record {
    
    private int idRecord;
    private String Record_text;
    private int id_appointment;
    private int id_patient;
    private String imageRecord;
    private byte[] file;
    private String time;
    private String height;
    private int weight;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Record() {
    }
    
     public Record( int idRecord, int id_appointment, String time,  int id_patient) {
        this.idRecord = idRecord;
       
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
       
        this.time = time;
       
    }
      public Record( int idRecord, String Record_text, int id_appointment, String imageRecord, String time, byte[] file, int id_patient,String height, int weight) {
        this.idRecord = idRecord;
        this.Record_text = Record_text;
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.imageRecord = imageRecord;
        this.file = file;
        this.time = time;
        this.height = height;
        this.weight = weight;
    }

    public Record( int idRecord, String Record_text, int id_appointment, String imageRecord, String time, byte[] file, int id_patient) {
        this.idRecord = idRecord;
        this.Record_text = Record_text;
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.imageRecord = imageRecord;
        this.file = file;
        this.time = time;
    }

    public Record(int idRecord, String Record_text, int id_appointment, int id_patient, String imageRecord, byte[] file) {
        this.idRecord = idRecord;
        this.Record_text = Record_text;
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.imageRecord = imageRecord;
        this.file = file;
    }

   
    public int getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(int idRecord) {
        this.idRecord = idRecord;
    }

    public String getRecord_text() {
        return Record_text;
    }

    public void setRecord_text(String Record_text) {
        this.Record_text = Record_text;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getImageRecord() {
        return imageRecord;
    }

    public void setImageRecord(String imageRecord) {
        this.imageRecord = imageRecord;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    
    
}
