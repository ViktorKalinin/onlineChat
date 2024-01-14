package org.example.onlinechat.views.chat;

import com.vaadin.collaborationengine.CollaborationMessageInput;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("")
@PageTitle("Chat")
@PermitAll
public class MainView extends VerticalLayout {
    private final UserInfo userInfo;

    public MainView() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        userInfo = new UserInfo(name, name);
        Component chat = getChatLayout();

        addClassName("center-container");
        add(chat);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private Component getChatLayout(){
        CollaborationMessageList messageList = new CollaborationMessageList(userInfo, "chat");
        CollaborationMessageInput messageInput = new CollaborationMessageInput(messageList);
        VerticalLayout chatLayout = new VerticalLayout(
                new H2("Chat"),
                messageList,
                messageInput
        );

        chatLayout.addClassName("bg-contrast-5");
        chatLayout.setAlignItems(Alignment.CENTER);
        chatLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        chatLayout.setHeight("100vh");
        chatLayout.getStyle().set("max-width", "50%");
        messageList.setHeight("100%");
        messageList.addClassName("word-wrap-break-word");

        return chatLayout;
    }
}
