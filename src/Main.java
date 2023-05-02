import Brigade.Brigade;
import Exceptions.NotUniqueNameException;
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
        Brigade brigade1 = new Brigade("Brigade 1", "Brygadzista 1");
        Brigade brigade2 = new Brigade("Brigade 2", "Brygadzista 2");

        List<Work> workList = new ArrayList<>();
        workList.add(new Work(Work.WorkType.GENERAL, "General work", 4000));
        workList.add(new Work(Work.WorkType.INSTALLATION, "Installation work", 5000));
        workList.add(new Work(Work.WorkType.DISASSEMBLY, "Disassembly work", 3000));
        workList.add(new Work(Work.WorkType.REPLACEMENT, "Replacement work", 2000));

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
