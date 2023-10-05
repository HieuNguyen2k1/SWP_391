package Model;

public class Doctor {
    public int id;
    public String name;
    public String email;

    public String password;
    public String degree;
    public int experience;
    public int speciality_id;
    public String speciality_name;
    public String image;
    public String phone;
    public String dob;
    public boolean gender;
    public String address;
    public String getSpeciality_name() {
        return speciality_name;
    }

    public void setSpeciality_name(String speciality_name) {
        this.speciality_name = speciality_name;
    }

    public Doctor() {
    }

    public Doctor(String password) {
        this.password = password;
    }

    public Doctor(int id, String name, String email, String degree, int experience, String speciality_name, String image, String phone, String dob, boolean gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.experience = experience;
        this.speciality_name = speciality_name;
        this.image = image;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public Doctor(int id, String name, String email, String password, String degree, int experience, int speciality_id, String image, String phone, String dob, boolean gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.degree = degree;
        this.experience = experience;
        this.speciality_id = speciality_id;
        this.image = image;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }
    public Doctor(int id, String name, String email, String degree, int experience, int speciality_id, String image, String phone, String dob, boolean gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.experience = experience;
        this.speciality_id = speciality_id;
        this.image = image;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public Doctor(int id, String name, String email, String password, String degree, int experience, int speciality_id, String speciality_name, String image, String phone, String dob, boolean gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.degree = degree;
        this.experience = experience;
        this.speciality_id = speciality_id;
        this.speciality_name = speciality_name;
        this.image = image;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(int speciality_id) {
        this.speciality_id = speciality_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddresses() {
        return address;
    }

    public void setAddresses(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", degree='" + degree + '\'' +
                ", experience=" + experience +
                ", speciality_id=" + speciality_id +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                ", gender=" + gender +
                ", addresses='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
