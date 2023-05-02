package Usage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Work extends Thread {

    public enum WorkType {
        GENERAL,
        INSTALLATION,
        DISASSEMBLY,
        REPLACEMENT
    }


    private WorkType workType;
    private int workTime;
    private boolean isDone;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private String action;
    public long ID;
    public static long IDcounter = 0;


    public Work(WorkType workType, String description, int workTime) {
        this.workType = workType;
        this.description = description;
        this.workTime = workTime;
        this.ID = IDcounter++;


    }

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
        startDateTime = LocalDateTime.now();
        System.out.println(this.description + " started: " + startDateTime.format(formatter));
        System.out.println("------------------------------------------");
        System.out.println(toString());
        System.out.println("------------------------------------------");
        try {
            Thread.sleep(workTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endDateTime = LocalDateTime.now();
        System.out.println(this.description + " finished: " + endDateTime.format(formatter));
        isDone = true;
        System.out.println(" ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(" ");

    }

    public String getDateRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
        String startDate = startDateTime.format(formatter);
        String endDate = endDateTime.format(formatter);
        return startDate + " - " + endDate;
    }


    public WorkType getWorkType() {
        return workType;
    }

    public int getWorkTime() {
        return workTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getWorkName() {
        return this.description;
    }

    public String getAction() {
        return action;
    }


    @Override
    public String toString() {
        return "Work{" +
                "workType=" + workType +
                ", workTime=" + workTime +
                ", isDone=" + isDone +
                ", description='" + description + '\'' +
                ", ID=" + ID +
                '}';
    }

}
