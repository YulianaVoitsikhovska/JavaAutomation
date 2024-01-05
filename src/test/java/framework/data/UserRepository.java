package framework.data;

import framework.tools.CSVReader;
import java.util.ArrayList;
import java.util.List;
public final class UserRepository {
    private UserRepository() {
    }

    public static UserData getDefault() {
        return getValidUser();
    }

    public static UserData getValidUser() {
        return new UserData("brisben2006@gmail.com", "Aa12345!", "Ann's comment", "Ann", "A");
    }

    public static UserData getValidUser2() {
        return new UserData("juliana.sergeeva@gmail.com", "Aa12345!", "Yuli comment", "Yuliana", "M");
    }


}
