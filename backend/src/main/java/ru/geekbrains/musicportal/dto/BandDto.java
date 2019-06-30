package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.user.Band;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BandDto extends AbstractDto {

    private Collection<UserDto> participants;

    public void GroupDto(Band band) {
        super.setId(band.getId());
        super.setName(band.getName());
        super.setDescription(band.getDescription());
        if (participants != null) {
            participants.clear();
        } else {
            participants = new ArrayList<>();
        }

    }
}