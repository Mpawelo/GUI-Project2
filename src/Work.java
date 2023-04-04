public class Work extends Thread {

    public enum workType {
        GENERAL,
        INSTALLATION,
        DISASSEMBLY,
        REPLACEMENT
    }

    public int workTime;
    public boolean isDone;
    public String description;




}
