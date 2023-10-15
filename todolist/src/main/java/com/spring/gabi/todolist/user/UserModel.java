package com.spring.gabi.todolist.user;

public class UserModel {
    private String userName;
    private String name;
    private String password;

    public UserModel(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsername() {
        return userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
