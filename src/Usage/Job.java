package Usage;

import Brigade.Brigade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Job implements Runnable {

    public static List<Work> workList = new ArrayList<>();

    private Brigade brigade;
    LocalDate startDate;
    LocalDate endDate;
    public long ID;
    public static long IDcounter = 0;

    public enum WorkStatus {
        PLANNED,
        UNPLANNED
    }


    public void addWork(Work work) {  //metoda dodająca pracę do zlecenia
        workList.add(work);
    }

    public boolean addBrigade(Brigade brigade, boolean isAdded) {
        if (isAdded) {
            if (this.brigade == null) {
                this.brigade = brigade;
                return true;
            }
            return false;
        }
        return false;
    }

    public String getJobStatus() {
        if (startDate == null) {
            return "Zlecenie nie rozpoczęte";
        } else if (endDate == null) {
            return "Zlecenie w trakcie realizacji";
        } else {
            return "Zlecenie zakończone";
        }
    }


    public Job(boolean isPlanned) {
        if (isPlanned) {
            WorkStatus status = WorkStatus.PLANNED;
        } else {
            WorkStatus status = WorkStatus.UNPLANNED;
        }
        this.ID = IDcounter++;
    }

    public Job(boolean isPLanned, Brigade brigade) {
        if (isPLanned) {
            WorkStatus status = WorkStatus.PLANNED;
        } else {
            WorkStatus status = WorkStatus.UNPLANNED;
        }
        this.brigade = brigade;
        this.ID = IDcounter++;
    }

    public Job(boolean isPlanned, List<Work> workList) {
        if (isPlanned) {
            WorkStatus status = WorkStatus.PLANNED;
        } else {
            WorkStatus status = WorkStatus.UNPLANNED;
        }
        this.workList = workList;
        this.ID = IDcounter++;
    }

    public Job(boolean isPlanned, Brigade brigade, List<Work> workList) {
        if (isPlanned) {
            WorkStatus status = WorkStatus.PLANNED;
        } else {
            WorkStatus status = WorkStatus.UNPLANNED;
        }
        this.brigade = brigade;
        this.workList = workList;
        this.ID = IDcounter++;
    }

    public void setStartDate(LocalDate startDate) {  //metoda ustawiająca datę rozpoczęcia
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {  //metoda ustawiająca datę zakończenia
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {  //metoda zwracająca datę rozpoczęcia
        return startDate;
    }

    public LocalDate getEndDate() {  //metoda zwracająca datę zakończenia
        return endDate;
    }


    public Brigade getBrigade() {
        return brigade;
    }

    @Override
    public void run() {
        if (brigade != null && !workList.isEmpty()) {
            for (Work work : workList) {
                work.start();
                try {
                    work.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("No more works to do. Goodbye!");
        System.out.println(" ");
    }

    @Override
    public String toString() {
        return "Job{" +
                "brigade=" + brigade +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", ID=" + ID +
                ", workList=" + workList +
                '}';
    }


}
