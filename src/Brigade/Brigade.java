package Brigade;

import Staff.StaffMember;
import java.util.ArrayList;
import java.util.List;

public class Brigade {

    public static List<StaffMember> staffMemberList = new ArrayList<>();

    public String brigadeName;
    public String foreman;
    public long ID;
    public static long IDcounter = 0;

    public Brigade(String brigadeName, String foreman) {
        this.brigadeName = brigadeName;
        this.foreman = foreman;
        this.ID = IDcounter++;
    }

    public void add(StaffMember staffMember) {
        staffMemberList.add(staffMember);
    }

    public void add(List<StaffMember> newStaffMemberList) {
        staffMemberList.addAll(newStaffMemberList);
    }

    public String getBrigadeName() {
        return brigadeName;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "brigadeName='" + brigadeName + '\'' +
                ", foreman='" + foreman + '\'' +
                ", ID=" + ID +
                ", staffMemberList=" + staffMemberList +
                '}';
    }
}
