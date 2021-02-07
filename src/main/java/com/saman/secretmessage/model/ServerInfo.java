package com.saman.secretmessage.model;

public class ServerInfo {
    private String welcomeMsg ;
    private String secretMsg;
    private String secretCode;

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getSecretMsg() {
        return secretMsg;
    }

    public void setSecretMsg(String secretMsg) {
        this.secretMsg = secretMsg;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }
}
