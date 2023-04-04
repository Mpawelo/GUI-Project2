import Staff.StaffMember;

import java.util.ArrayList;
import java.util.List;

public class Brigade {

    public static List<StaffMember> staffMemberList = new ArrayList<>();
    public static List<Brigade> brigadeList = new ArrayList<>();

    public String brigadeName;
    public String foreman;

    public void add(StaffMember staffMember) {
        staffMemberList.add(staffMember);
    }

    public void add(List<StaffMember> staffMemberList) {
        staffMemberList.addAll(staffMemberList);
    }


}
