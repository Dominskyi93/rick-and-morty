package dominskyi.springboot.rickandmortyapp.dto.mapper;

import dominskyi.springboot.rickandmortyapp.dto.CharacterResponseDto;
import dominskyi.springboot.rickandmortyapp.dto.external.ApiCharacterDto;
import dominskyi.springboot.rickandmortyapp.model.Gender;
import dominskyi.springboot.rickandmortyapp.model.MovieCharacter;
import dominskyi.springboot.rickandmortyapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiCharacterResponseDto(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(dto.getName());
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setExternalId(dto.getId());
        return movieCharacter;
    }

    public CharacterResponseDto toResponseDto(MovieCharacter movieCharacter) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(movieCharacter.getId());
        dto.setExternalId(movieCharacter.getExternalId());
        dto.setName(movieCharacter.getName());
        dto.setStatus(movieCharacter.getStatus());
        dto.setGender(movieCharacter.getGender());
        return dto;
    }
}
