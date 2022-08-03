package com.ll.exam.chat;

import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ChatRoomRepository {

    private static List<ChatRoomDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "이름%d".formatted(id);
            String body = "주제%d".formatted(id);
            create(title, body);
        });
    }

    public static long create(String title, String body) {
        long id = ++lastId;
        ChatRoomDto newChatRoomDto = new ChatRoomDto(id, title, body);

        datum.add(newChatRoomDto);

        return id;
    }

    public List<ChatRoomDto> findAll() {
        return datum;
    }

    public ChatRoomDto findById(long id) {

        for (ChatRoomDto chatRoom : datum) {
            if (chatRoom.getId() == id) {
                return chatRoom;
            }
        }

        return null;
    }

    public void modifyRoom(long id, String title, String body) {

        ChatRoomDto chatRoomDto = findById(id);

        if (chatRoomDto == null) return;

        chatRoomDto.setTitle(title);
        chatRoomDto.setBody(body);

    }

    public void delete(long id) {

        ChatRoomDto chatRoom = findById(id);

        if (chatRoom == null) return;

        datum.remove(chatRoom);

    }
}
