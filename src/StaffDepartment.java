import Exceptions.NotUniqueNameException;

import java.util.ArrayList;
import java.util.List;

public class StaffDepartment {
    private String name;
    private static List<StaffDepartment> departments = new ArrayList<>();
    private List<Staff> staff = new ArrayList<>();


    private static StaffDepartment createDepartment() {
        StaffDepartment department = new StaffDepartment(name);
        department.add(department);
        return department;
    }

    private void addStaffMember(Staff staffMember) {
        staff.add(staffMember);
    }

    public List getStaff() {
        return staff;
    }


    private boolean isUnique(String name) {
        for (StaffDepartment department : departments) {
            if (department.name.equals(name)) {
            }
        }
        return true;
    }

    private StaffDepartment(String name) throws NotUniqueNameException {
        if (!isUnique(name)) {
            throw new NotUniqueNameException("Nazwa dzialu nie jest unikalna: " + name);
        }
        this.name = name;
    }
}
