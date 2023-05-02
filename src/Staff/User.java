package Staff;

import Department.StaffDepartment;

public class User extends StaffMember {

    public String login;
    public String password;
    public String inicial;
    public long ID;
    public static long IDcounter = 0;


    public User(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        super(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment);
        this.login = login;
        this.password = password;
        this.inicial = staffMemberName.charAt(0) + "." + staffMemberSurname.charAt(0) + ".";
        updateInicial();
        this.ID = IDcounter++;
    }

    public void setStaffMemberName(String StaffMemberName) {
        this.staffMemberName = StaffMemberName;
        updateInicial();
    }

    public void setStaffMemberSurname(String StaffMemberSurname) {
        this.staffMemberSurname = StaffMemberSurname;
        updateInicial();
    }

    public void updateInicial() {
        this.inicial = staffMemberName.charAt(0) + "." + staffMemberSurname.charAt(0) + ".";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getInicial() {
        return inicial;
    }

    public String toString() {
        return "User{" +
                "staffMemberName='" + staffMemberName + '\'' +
                ", staffMemberSurname='" + staffMemberSurname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", staffDepartment=" + staffDepartment +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", inicial='" + inicial + '\'' +
                '}';
    }

}
