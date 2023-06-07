import Brigade.Brigade;
import Department.StaffDepartment;
import Exceptions.NotUniqueNameException;
import GUI.S25402;
import Staff.StaffMember;
import Usage.Job;
import Usage.Work;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import static Usage.Job.workList;

public class Main {

    private static final String FILE_NAME = "log.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd--HH:mm:ss");


    public static void main(String[] args) throws NotUniqueNameException {

        List<StaffDepartment> departments = new ArrayList<>();

        departments.add(StaffDepartment.createDepartment("IT"));
        departments.add(StaffDepartment.createDepartment("HR"));

        List<StaffMember> staffMembers = new ArrayList<>();

        staffMembers.add(StaffMember.createStaffMember("Cristiano", "Ronaldo", "1995-02-07", departments.get(0)));
        staffMembers.add(StaffMember.createStaffMember("Lionel", "Messie", "1999-06-12", departments.get(0)));
        staffMembers.add(StaffMember.createStaffMember("Robert", "Lewangoalski", "2000-05-05", departments.get(1)));
        staffMembers.add(StaffMember.createStaffMember("Kylian", "Mekambe", "1998-03-11", departments.get(1)));


        Brigade brigade1 = new Brigade("Brigade1", "Foreman1");
        Brigade brigade2 = new Brigade("Brigade2", "Foreman2");


        List<Work> workList = new ArrayList<>();
        workList.add(new Work(Work.WorkType.GENERAL, "General work", 15000));
        workList.add(new Work(Work.WorkType.INSTALLATION, "Installation work", 20000));
        workList.add(new Work(Work.WorkType.DISASSEMBLY, "Disassembly work", 15000));
        workList.add(new Work(Work.WorkType.REPLACEMENT, "Replacement work", 15000));


        S25402 s25402 = new S25402();

        Job job = new Job(true, brigade1, workList);
        job.run();

        saveJobLog(job);

        readAndPrintJobLog();
    }

    private static void saveJobLog(Job job) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(FILE_NAME), true));
            for (Work work : workList) {
                writer.println(work.getDescription() + " (" + work.getWorkType() + "): " + work.getDateRange());
            }
            writer.println("------------------------------------------");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void readAndPrintJobLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
