package com.example.healthassisstant.dto;


public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;

    // 构造函数、getter和setter
    public PasswordChangeRequest() {}

    public PasswordChangeRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
