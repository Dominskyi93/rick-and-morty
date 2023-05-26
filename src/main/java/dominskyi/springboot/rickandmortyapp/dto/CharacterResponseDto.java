package dominskyi.springboot.rickandmortyapp.dto;

import dominskyi.springboot.rickandmortyapp.model.Gender;
import dominskyi.springboot.rickandmortyapp.model.Status;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
