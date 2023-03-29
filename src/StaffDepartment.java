import Exceptions.NotUniqueNameException;

import java.util.ArrayList;
import java.util.List;

public class StaffDepartment {
    private final String departmentName;
    private static List<StaffDepartment> departments = new ArrayList<>();
    private static List<StaffMember> staffMembers = new ArrayList<>();

    public static StaffDepartment createDepartment(String departmentName) throws NotUniqueNameException {
        StaffDepartment newDepartment = new StaffDepartment(departmentName);
        departments.add(newDepartment);
        return newDepartment;
    }


    private boolean isUnique(String departmentName) {
        for (StaffDepartment department : departments) {
            if (department.departmentName.equals(departmentName)) {
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
    }

    public List<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public String getDepartmentName() {
        return departmentName;
    }


}
