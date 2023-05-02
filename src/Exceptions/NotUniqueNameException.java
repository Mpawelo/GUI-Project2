package Exceptions;

public class NotUniqueNameException extends Exception {
    private String reason = "Not unique name";

    public NotUniqueNameException(String reason) {
        super(reason);
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NotUniqueNameException{" +
                "reason='" + reason + '\'' +
                '}';
    }

}
