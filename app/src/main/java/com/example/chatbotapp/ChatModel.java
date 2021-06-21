package com.example.chatbotapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatModel {

    @SerializedName("chatBotReply")
    @Expose
    private String chatBotReply;

    public String getChatBotReply() {
        return chatBotReply;
    }

    public void setChatBotReply(String chatBotReply) {
        this.chatBotReply = chatBotReply;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "chatBotReply='" + chatBotReply + '\'' +
                '}';
    }
}
