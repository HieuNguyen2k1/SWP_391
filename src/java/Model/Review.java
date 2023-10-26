/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Review {
    private int reviewID;
    
    private String comment;
    private int rating;
    private String dateUp;
    private int doctorid;
    private int patientid;
    private String namePatient;

    public Review() {
    }

    public Review(int reviewID, String comment, int rating, String dateUp, int doctorid, int patientid, String namePatient) {
        this.reviewID = reviewID;
        this.comment = comment;
        this.rating = rating;
        this.dateUp = dateUp;
        this.doctorid = doctorid;
        this.patientid = patientid;
        this.namePatient = namePatient;
    }



    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }


    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDateUp() {
        return dateUp;
    }

    public void setDateUp(String dateUp) {
        this.dateUp = dateUp;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewID=" + reviewID + ", comment=" + comment + ", rating=" + rating + ", dateUp=" + dateUp + ", doctorid=" + doctorid + ", patientid=" + patientid + ", namePatient=" + namePatient + '}';
    }


 
    
    
}
