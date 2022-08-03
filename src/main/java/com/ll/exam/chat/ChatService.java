package com.ll.exam.chat;

import com.ll.exam.Rq;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {

    private ChatRoomRepository chatRoomRepository;

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
}
