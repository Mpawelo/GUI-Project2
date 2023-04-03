package Staff;

import Department.StaffDepartment;

public class Specialist extends StaffMember{

    public String specialization;

    public Specialist(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment, String specialization) {
        super(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}
