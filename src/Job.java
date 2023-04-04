import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Job implements Runnable {

    public static List<Work> workList = new ArrayList<>();
    public enum workStatus {
        PLANNED,
        UNPLANNED
    }
    Brigade brigade;
    LocalDate startDate;
    LocalDate endDate;


    public boolean addBrigade(Brigade brigade, boolean isAdded){
        if (isAdded){
            if (this.brigade == null){
                this.brigade = brigade;
                return true;
            }
            return false;
        }
        return false;
    }

public static HashMap<Work.workType, workStatus> workMap = new HashMap<>();
    public static void addWork(Work.workType workType, workStatus workStatus) {
        workMap.put(workType, workStatus);
    }

    public Job(boolean isPlanned){
        if (isPlanned){
            workMap.put(Work.workType.GENERAL, workStatus.PLANNED);
            workMap.put(Work.workType.INSTALLATION, workStatus.PLANNED);
            workMap.put(Work.workType.DISASSEMBLY, workStatus.PLANNED);
            workMap.put(Work.workType.REPLACEMENT, workStatus.PLANNED);
        }else {
            workMap.put(Work.workType.GENERAL, workStatus.UNPLANNED);
            workMap.put(Work.workType.INSTALLATION, workStatus.UNPLANNED);
            workMap.put(Work.workType.DISASSEMBLY, workStatus.UNPLANNED);
            workMap.put(Work.workType.REPLACEMENT, workStatus.UNPLANNED);
        }

    }

    public Job(boolean isWorking, Brigade brigade) {
        if (isWorking) {
            this.brigade = brigade;
            this.startDate = LocalDate.now();
            this.endDate = startDate.plusDays(1);
        } else {
            this.brigade = brigade;
            this.startDate = LocalDate.now();
            this.endDate = startDate.plusDays(1);
        }
    }

    public Job (boolean isDone, List workList){
        if (isDone){
            this.workList = workList;
        }
    }

    public Job(boolean isDone, List workList, Brigade brigade){
        if (isDone){
            this.workList = workList;
            this.brigade = brigade;
        }
    }





    @Override
    public void run() {

    }
}
