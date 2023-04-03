package Exceptions;

public class NotUniqueNameException extends Exception {
    String reason;

    public NotUniqueNameException(String reason) {
        super(reason);
        this.reason = reason;
    }

    @Override
    public String toString() {
        return reason;
    }

}