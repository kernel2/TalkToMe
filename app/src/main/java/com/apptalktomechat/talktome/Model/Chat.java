package com.apptalktomechat.talktome.Model;

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private String mediaId;
    private boolean isseen;

    public Chat(String sender, String receiver, String message, String mediaId, boolean isseen) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.mediaId = mediaId;
        this.isseen = isseen;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMediaId() { return mediaId; }

    public void setMediaId(String mediaId) { this.mediaId = mediaId; }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }
}


