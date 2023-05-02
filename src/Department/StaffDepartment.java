package Department;

import Exceptions.NotUniqueNameException;
import Staff.StaffMember;

import java.util.ArrayList;
import java.util.List;

public class StaffDepartment {
    private String departmentName;
    public long ID;
    public static long IDcounter = 0;

    private static List<StaffDepartment> departments = new ArrayList<>();
    private List<StaffMember> staffMembers = new ArrayList<>();

    public static StaffDepartment createDepartment(String departmentName) throws NotUniqueNameException {
        StaffDepartment newDepartment = new StaffDepartment(departmentName);
        departments.add(newDepartment);
        return newDepartment;
    }


    private boolean isUnique(String departmentName) {
        for (StaffDepartment department : departments) {
            if (department.departmentName.equals(departmentName)) {
                return false;
            }
        }
        return true;
    }

    private StaffDepartment(String departmentName) throws NotUniqueNameException {
        if (!isUnique(departmentName)) {
            throw new NotUniqueNameException("Name of the department is not unique " + departmentName);
        }
        this.departmentName = departmentName;
        departments.add(this);
        this.ID = IDcounter++;
    }

    public List<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", ID=" + ID +
                ", staffMembers=" + staffMembers +
                '}';
    }

}
