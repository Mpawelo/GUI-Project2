package Staff;

import Department.StaffDepartment;

public class User extends StaffMember {

    public String login;
    public String password;

    public String inicial;

    public User(String staffMemberName, String staffMemberSurname, String dateOfBirth, StaffDepartment staffDepartment) {
        super(staffMemberName, staffMemberSurname, dateOfBirth, staffDepartment);
        this.login = login;
        this.password = password;
       /* int lenght = inicial.length();
        for (int i = 0; i < inicial; i++)*/
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
