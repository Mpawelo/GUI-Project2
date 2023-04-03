package Staff;

import Department.StaffDepartment;

public class Foreman extends User{
    public Foreman(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        super(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment);
    }
}
