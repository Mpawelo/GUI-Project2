package Staff;

import Department.StaffDepartment;
import Brigade.Brigade;
import Usage.Job;

import java.util.ArrayList;
import java.util.List;

public class Foreman extends User {

    public long ID;
    public static long IDcounter = 0;

    public List<Brigade> brigades = new ArrayList<>();

    public List<Job> jobs = new ArrayList<>();


    public Foreman(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        super(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment);
        this.ID = IDcounter++;
    }

    public List getBrigades() {
        return brigades;
    }

    public List getJobs() {
        return jobs;
    }

    public void add(Brigade brigade) {
        brigades.add(brigade);
    }

    public void add(Job job) {
        jobs.add(job);
    }


    public String toString() {
        return "Foreman{" +
                "staffMemberName='" + staffMemberName + '\'' +
                ", staffMemberSurname='" + staffMemberSurname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", staffDepartment=" + staffDepartment +
                ", ID=" + ID +
                ", brigades=" + brigades +
                ", jobs=" + jobs +
                '}';
    }


}
