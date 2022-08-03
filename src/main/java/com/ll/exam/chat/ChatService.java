package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {

    private ChatRoomRepository chatRoomRepository;
    private ChatMessageRepository chatMessageRepository;

    public ChatService() {
        chatRoomRepository = new ChatRoomRepository();
    }

    public long doCreateRoom(String title, String body) {
        return chatRoomRepository.create(title, body);
    }

    public List<ChatRoomDto> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoomDto findRoomById(long id) {
        return chatRoomRepository.findById(id);
    }

    public void modifyRoom(long id, String title, String body) {
        chatRoomRepository.modifyRoom(id, title, body);
    }

    public void delete(long id) {

        chatRoomRepository.delete(id);

    }

    public void writeMessage(long roomId, String body) {

        chatMessageRepository.write(roomId, body);

    }
}
