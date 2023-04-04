package Staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Department.StaffDepartment;

public abstract class StaffMember implements Comparable<StaffMember> {

    private static List<StaffMember> staffMemberList = new ArrayList<>();

    protected final String staffMemberName;
    protected final String staffMemberSurname;
    protected LocalDate dateOfBirth;
    protected final StaffDepartment staffDepartment;

    public StaffMember(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        this.staffMemberName = staffMemberName;
        this.staffMemberSurname = staffMemberSurname;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.staffDepartment = staffDepartment;
        staffMemberList.add(this);
    }

    public static List<StaffMember> getStaffMemberList() {

        return staffMemberList;
    }

    public String getStaffMemberName() {

        return staffMemberName;
    }

    public String getStaffMemberSurname() {

        return staffMemberSurname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public StaffDepartment getStaffDepartment() {

        return staffDepartment;
    }

    @Override
    public int compareTo(StaffMember staffMember) {
        int result = staffMemberName.compareTo(staffMember.staffMemberName);
        if (result != 0) {
            return result;
        }
        result = staffMemberSurname.compareTo(staffMember.staffMemberSurname);
        if (result != 0) {
            return result;
        }
        return dateOfBirth.compareTo(staffMember.dateOfBirth);
    }


}
