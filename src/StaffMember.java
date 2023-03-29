import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class StaffMember implements Comparable<StaffMember> {

    private static List<StaffMember> staffMemberList = new ArrayList<>();
    private final String staffMemberName;
    private final String staffMemberSurname;
    private final String dateOfBirth;
    private final StaffDepartment staffDepartment;

    public StaffMember(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        this.staffMemberName = staffMemberName;
        this.staffMemberSurname = staffMemberSurname;
        this.dateOfBirth = dateOfBirth;
        this.staffDepartment = staffDepartment;
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

    public String getDateOfBirth() {
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
