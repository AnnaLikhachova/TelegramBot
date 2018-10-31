package com.telegram.weatherbot.model;

import com.telegram.weatherbot.types.*;
import com.telegram.weatherbot.enums.*;
import com.telegram.weatherbot.utils.Json;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base bot class 
 */
public abstract class Bot {

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    private String apiUrl = "https://api.telegram.org/bot{token}/{request}";
    private String token = "638290016:AAFADZ2vE_f2KyWIqXeoabsEHv8knoe2U-4";

    /**
     * Maximum size of update id
     */
    private long maxUpdateId = 0;
    
    /**
     * Maximum number of messages loaded with getUpdates
     */
    private int updateLimit = 100;

    /**
     * Timeout for long polling in getUpdates (seconds)
     */
    private int updateTimeout = 0;

    public Bot(String token) {
        this.token = token;
    }
    
    /**
     * Get bot user info
     * @return Bot user info
     */
    protected User getMe() throws Exception {
        JSONObject me = makeRequest(RequestType.getMe);
        User user = Json.toObject(me, User.class);

        if(user == null) {
            throw new Exception("Cannot parse bot user info");
        }

        return user;
    }
    
    /**
     * Get available updates
     * @return ArrayList of updates
     */
    protected List<MessageType> getUpdates() {
        return getUpdates(maxUpdateId + 1, updateLimit, updateTimeout);
    } 
    
    /**
     * Send message
     * @param chatId Identifier of user or group
     * @param text MessageType text
     * @return MessageType object sent to server
     * @throws Exception
     */
    protected MessageType sendMessage(int chatId, String text) throws Exception{
        Map<String, Object> query = new HashMap<>();
        query.put("chat_id", chatId);
        query.put("text", text);
        return sendMessage(query);
    }
    
    /**
     * Send message as reply to other message
     * @param chatId Identifier of user or group
     * @param text MessageType text
     * @param replyToMessageId Identifier of message to reply
     * @return MessageType object sent to server
     * @throws Exception
     */
    protected MessageType sendMessage(int chatId, String text, int replyToMessageId) throws Exception {
        Map<String, Object> query = new HashMap<>();
        query.put("chat_id", chatId);
        query.put("text", text);
        query.put("reply_to_message_id", replyToMessageId);
        return sendMessage(query);
    }
    
    /**
     * Send message 
     * @param chatId Identifier of user or group
     * @param text MessageType text
     * @param parseMode Parse mode for markdown
     * @param disableWebPagePreview Disables link previews for links in this message
     * @param replyToMessageId Identifier of message to reply
     * @return MessageType object sent to server
     * @throws Exception
     */
    protected MessageType sendMessage(int chatId, String text, ParseMode parseMode,
                                  boolean disableWebPagePreview, int replyToMessageId) throws Exception {

        Map<String, Object> query = new HashMap<>();
        query.put("chat_id", chatId);
        query.put("text", text);
        query.put("parse_mode", parseMode.toString());
        query.put("disable_web_page_preview", disableWebPagePreview);
        query.put("reply_to_message_id", replyToMessageId);
        return sendMessage(query);
    }

    /**
     * Forward any message
     * @param chatId MessageType recipient
     * @param fromChatId Chat where the original message was sent
     * @param messageId MessageType identifier
     * @return MessageType object sent
     * @throws Exception
     */
    protected MessageType forwardMessage(int chatId, int fromChatId, int messageId) throws Exception {
        Map<String, Object> query = new HashMap<>();
        query.put("chat_id", chatId);
        query.put("from_chat_id", fromChatId);
        query.put("message_id", messageId);

        JSONObject messageObject = makeRequest(RequestType.forwardMessage, query);
        MessageType message = Json.toObject(messageObject, MessageType.class);

        if(message == null) {
            throw new Exception("Cannot parse sent message");
        }

        return message;
    }

    /**
     * Send location
     * @param chatId Chat
     * @param latitude Latitude
     * @param longitude Longitude
     * @return MessageType sent to server
     */
    protected MessageType sendLocation(int chatId, double latitude, double longitude) throws Exception {
        Map<String, Object> query = new HashMap<>();
        query.put("chat_id", chatId);
        query.put("latitude", latitude);
        query.put("longitude", longitude);
        return sendLocation(query);
    }

    /**
     * Send location as reply to a message
     * @param chatId Chat
     * @param latitude Latitude
     * @param longitude Longitude
     * @param replyToMessageId MessageType id to reply
     * @return MessageType sent to server
     * @throws Exception
     */
    protected MessageType sendLocation(int chatId, double latitude, double longitude, int replyToMessageId) throws Exception {
        Map<String, Object> query = new HashMap<>();
        query.put("chat_it", chatId);
        query.put("latitude", latitude);
        query.put("longitude", longitude);
        query.put("reply_to_message_id", replyToMessageId);
        return sendLocation(query);
    }
    
    /**
     * Make request to telegram bot api
     * @param requestType Type of request
     * @return Request result
     */
    private JSONObject makeRequest(RequestType requestType, Map<String, Object> parameters) throws Exception {
        try {
            HttpResponse<JsonNode> response = Unirest.get(apiUrl)
                    .routeParam("token", token)
                    .routeParam("request", requestType.toString())
                    .queryString(parameters)
                    .asJson();
            JSONObject result = response.getBody().getObject();

            boolean ok = result.getBoolean("ok");
            if(!ok) {
                String description = result.getString("description");
                throw new Exception("Telegram API error: " + description);
            }

            return result.optJSONObject("result");
        } catch(Exception e) {
            logger.error("Failed to make request: " + requestType);
            throw e;
        }
    }

    /**
     * Send location with given parameters
     * @param query Query parameters
     * @return MessageType sent to server
     * @throws Exception
     */
    private MessageType sendLocation(Map<String, Object> query) throws Exception {
        JSONObject messageObject = makeRequest(RequestType.sendLocation, query);
        MessageType message = Json.toObject(messageObject, MessageType.class);

        if(message == null) {
            throw new Exception("Cannot parse sent message");
        }

        return message;
    }
    
    /**
     * Make api request without parameters
     * @param requestType Type of request
     * @return Request result
     */
    private JSONObject makeRequest(RequestType requestType) throws Exception {
        return makeRequest(requestType, new HashMap<>());
    }

    /**
     * Get updates from Telegram
     * @return Array of updates
     */
    private List<MessageType> getUpdates(long offset, int limit, int timeout) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(apiUrl)
                    .routeParam("token", token)
                    .routeParam("request", RequestType.getUpdates.toString())
                    .queryString("offset", offset)
                    .queryString("limit", limit)
                    .queryString("timeout", timeout)
                    .asJson();
            JSONObject result = response.getBody().getObject();

            boolean ok = result.getBoolean("ok");
            if(!ok) {
                String description = result.getString("description");
                throw new Exception("Telegram API error: " + description);
            }

            JSONArray array = result.getJSONArray("result");
            List<MessageType> updat = new ArrayList<>();

            for(int idx = 0; idx < array.length(); idx++) {
                Update update = Json.toObject(array.getJSONObject(idx), Update.class);

                if(update == null) {
                    logger.warn("Cannot update");
                    continue;
                }

                if(update.getMessage() == null) {
                    continue;
                }

                maxUpdateId = Math.max(maxUpdateId, update.getUpdateId());

                updat.add(update.getMessage());
            }

            return updat;
        } catch(Exception e) {
            logger.error("Failed to get updates", e);
            return new ArrayList<>();
        }
    }

    /**
     * Send message to user or group described by given parameters
     * @param query MessageType parameters
     * @return MessageType sent to server
     * @throws Exception
     */
    private MessageType sendMessage(Map<String, Object> query) throws Exception {
        JSONObject messageObject = makeRequest(RequestType.sendMessage, query);
        MessageType message = Json.toObject(messageObject, MessageType.class);

        if(message == null) {
            throw new Exception("Cannot sent message");
        }

        return message;
    }
    
    
    public String getApiUrl() {
        return apiUrl;
    }

    public String getToken() {
        return token;
    }

    public int getUpdateLimit() {
        return updateLimit;
    }

    public int getUpdateTimeout() {
        return updateTimeout;
    }

    public void setUpdateLimit(int updateLimit) {
        if(updateLimit < 1 || updateLimit > 100) {
            throw new IllegalArgumentException("The bounds for updating limit are [1, 100]");
        }
        this.updateLimit = updateLimit;
    }

    public void setUpdateTimeout(int updateTimeout) {
        if(updateTimeout < 0) {
            throw new IllegalArgumentException("Update timeout cannot be negative");
        }
        this.updateTimeout = updateTimeout;
    }
}