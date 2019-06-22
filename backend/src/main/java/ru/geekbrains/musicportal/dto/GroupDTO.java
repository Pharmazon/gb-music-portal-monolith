package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.track.MusicGroup;
import ru.geekbrains.musicportal.entity.user.User;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupDto extends AbstractDto {

    private Collection<UserDto> participants;

    public void GroupDto(MusicGroup group) {
        super.setId(group.getId());
        super.setName(group.getName());
        super.setDescription(group.getDescription());
        if (participants != null) {
            participants.clear();
        } else {
            participants = new ArrayList<>();
        }

        ArrayList<User> participantsTmp = (ArrayList<User>) group.getParticipants();
        for (User user : participantsTmp) {
            UserDto userDTO = new UserDto(user);
            participants.add(userDTO);
        }
    }
}