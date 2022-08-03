package com.ll.exam.chat;

import com.ll.exam.Rq;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatController {

    private ChatService chatService;

    public ChatController() {
        chatService = new ChatService();
    }

    public void createRoom(Rq rq) {

        rq.view("/usr/chat/createRoom");
    }

    public void doCreateRoom(Rq rq) {

        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        if (title.length() == 0) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if (body.length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        long id = chatService.doCreateRoom(title, body);

        rq.replace("/usr/chat/room/%d".formatted(id), "%d번 채팅방이 생성 되었습니다.".formatted(id));

    }


    public void showRoomList(Rq rq) {

        List<ChatRoomDto> chatRoomDtos = chatService.findAll();

        rq.setAttr("rooms", chatRoomDtos);
        rq.view("/usr/chat/roomList");
    }
}