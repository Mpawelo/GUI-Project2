package Department;

import Exceptions.NotUniqueNameException;
import Staff.StaffMember;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StaffDepartment implements Comparable<StaffDepartment> {
    private String departmentName;
    public long ID;
    public static long IDcounter = 0;
    private static List<StaffDepartment> departments = new ArrayList<>();

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

    public StaffDepartment(String departmentName) throws NotUniqueNameException {
        if (!isUnique(departmentName)) {
            throw new NotUniqueNameException("Name of the department is not unique " + departmentName);
        }
        this.departmentName = departmentName;
        this.ID = IDcounter++;
    }


    public static List<StaffDepartment> getDepartments() {
        List<StaffDepartment> sortedList = new ArrayList<>(departments);
        Collections.sort(sortedList);
        return sortedList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) throws NotUniqueNameException {
        if (!isUnique(departmentName)) {
            throw new NotUniqueNameException("Name of the department is not unique: " + departmentName);
        }
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Dept Name: " + departmentName + "\n" +
                "  ID: " + ID + "\n";
    }

    @Override
    public int compareTo(StaffDepartment o) {
        return this.departmentName.compareTo(o.departmentName);
    }
}
