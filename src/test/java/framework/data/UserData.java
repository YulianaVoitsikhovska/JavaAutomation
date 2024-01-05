package framework.data;

import java.util.ArrayList;
import java.util.List;
public class UserData {
        private String email;
        private String password;
        private String comment;
        private String username;
        private String initial;


    public UserData(String email, String password, String comment, String username, String initial) {
        this.email = email;
        this.password = password;
        this.comment = comment;
        this.username = username;
        this.initial = initial;
    }

        public String getEmail() {
        return email;
    }

        public String getPassword() {
        return password;
    }

    public String getComment() {

        return comment;
    }

    public String getInitial() {
        return initial;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", comment='" + comment + '\'' +
                ", username='" + username + '\'' +
                ", initial='" + initial + '\'' +
                '}';
    }
   }
