package Staff;

import Department.StaffDepartment;

public class Specialist extends StaffMember {

    public String specialization;
    public long ID;
    public static long IDcounter = 0;

    public Specialist(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment, String specialization) {
        super(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment);
        this.specialization = specialization;
        this.ID = IDcounter++;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String toString() {
        return "Specialist{" +
                "staffMemberName='" + staffMemberName + '\'' +
                ", staffMemberSurname='" + staffMemberSurname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", staffDepartment=" + staffDepartment +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
