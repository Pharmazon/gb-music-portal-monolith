package ru.geekbrains.musicportal.dto;

import lombok.Data;
import ru.geekbrains.musicportal.entity.database.Group;
import ru.geekbrains.musicportal.entity.security.User;

import java.util.ArrayList;

@Data
public class GroupDTO {
    private Long id;
    private String title;
    private ArrayList<UserDTO> participants;

    public void fillByEntity(Group group) {
        id = group.getId();
        title = group.getTitle();
        if (participants != null) {
            participants.clear();
        } else {
            participants = new ArrayList<>();
        }

        ArrayList<User> participantsTmp = (ArrayList<User>) group.getParticipants();
        for (User user : participantsTmp) {
            UserDTO userDTO = new UserDTO();
            userDTO.fillByEntity(user);
            participants.add(userDTO);
        }
    }
}