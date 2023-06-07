package Staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Department.StaffDepartment;

public abstract class StaffMember implements Comparable<StaffMember> {

    private static List<StaffMember> staffMemberList = new ArrayList<>();

    protected String staffMemberName;
    protected String staffMemberSurname;
    protected LocalDate dateOfBirth;
    protected StaffDepartment staffDepartment;
    public long ID;
    public static long IDcounter = 0;


    public StaffMember(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        this.staffMemberName = staffMemberName;
        this.staffMemberSurname = staffMemberSurname;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.staffDepartment = staffDepartment;
        this.ID = IDcounter++;

    }

    public static StaffMember createStaffMember(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        StaffMember newStaffMember = new StaffMember(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment) {
        };
        staffMemberList.add(newStaffMember);
        return newStaffMember;
    }

    public static List<StaffMember> getStaffMembers() {

        List<StaffMember> sortedList = new ArrayList<>(staffMemberList);
        Collections.sort(sortedList);
        return sortedList;
    }

    public String getStaffMemberName() {

        return staffMemberName;
    }

    public void setStaffMemberName(String staffMemberName) {

        this.staffMemberName = staffMemberName;
    }

    public String getStaffMemberSurname() {

        return staffMemberSurname;
    }

    public void setStaffMemberSurname(String staffMemberSurname) {

        this.staffMemberSurname = staffMemberSurname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {

        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    public void setStaffDepartment(StaffDepartment staffDepartment) {

        this.staffDepartment = staffDepartment;
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

    @Override
    public String toString() {
        return  staffMemberName + "  \n" +
                staffMemberSurname + "  \n" +
                dateOfBirth + "  \n" +
                "Dept: " + staffDepartment.getDepartmentName();
    }



}
