package com.telegram.weatherbot.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageType {

    @JsonProperty("message_id")
    private int messageId;

    @JsonProperty("from")
    private User from;

    @JsonProperty("date")
    private int date;

    @JsonProperty("chat")
    private Chat chat;

    @JsonProperty("forward_from")
    private User forwardFrom;

    @JsonProperty("forward_date")
    private int forwardDate;

    @JsonProperty("reply_to_message")
    private MessageType replyToMessage;

    @JsonProperty("text")
    private String text;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("new_chat_participant")
    private User newChatParticipant;

    @JsonProperty("left_chat_participant")
    private User leftChatParticipant;

    @JsonProperty("new_chat_title")
    private String newChatTitle;

    @JsonProperty("group_chat_created")
    private boolean groupChatCreated;

    ///////////////////////////////////////////////////////////////////////////

    public MessageType() {

    }

    ///////////////////////////////////////////////////////////////////////////

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public void setForwardFrom(User forwardFrom) {
        this.forwardFrom = forwardFrom;
    }

    public int getForwardDate() {
        return forwardDate;
    }

    public void setForwardDate(int forwardDate) {
        this.forwardDate = forwardDate;
    }

    public MessageType getReplyToMessage() {
        return replyToMessage;
    }

    public void setReplyToMessage(MessageType replyToMessage) {
        this.replyToMessage = replyToMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getNewChatParticipant() {
        return newChatParticipant;
    }

    public void setNewChatParticipant(User newChatParticipant) {
        this.newChatParticipant = newChatParticipant;
    }

    public User getLeftChatParticipant() {
        return leftChatParticipant;
    }

    public void setLeftChatParticipant(User leftChatParticipant) {
        this.leftChatParticipant = leftChatParticipant;
    }

    public String getNewChatTitle() {
        return newChatTitle;
    }

    public void setNewChatTitle(String newChatTitle) {
        this.newChatTitle = newChatTitle;
    }

    public boolean isGroupChatCreated() {
        return groupChatCreated;
    }

    public void setGroupChatCreated(boolean groupChatCreated) {
        this.groupChatCreated = groupChatCreated;
    }
}
