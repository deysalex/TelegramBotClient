package ru.deysa.telegram.bot.client;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class TelegramBotClient {

    private static String apiUrl = "https://api.telegram.org/bot";

    private final String chatId;

    private final String prefixUrl;

    public TelegramBotClient(String chatId, String token) {
        this.chatId = chatId;
        this.prefixUrl = apiUrl + token;
    }

    private String getPrefixUrl() {
        return prefixUrl;
    }

    public boolean sendMessage(String message) {
        boolean result = true;
        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("chat_id", chatId);
            map.add("text", message);

            RestTemplate rest = new RestTemplate();
            rest.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            rest.postForObject(getPrefixUrl() + "/" + "sendMessage", map, String.class);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
