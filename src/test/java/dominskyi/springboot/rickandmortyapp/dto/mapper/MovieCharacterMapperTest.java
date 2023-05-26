package dominskyi.springboot.rickandmortyapp.dto.mapper;

import dominskyi.springboot.rickandmortyapp.dto.CharacterResponseDto;
import dominskyi.springboot.rickandmortyapp.dto.external.ApiCharacterDto;
import dominskyi.springboot.rickandmortyapp.model.Gender;
import dominskyi.springboot.rickandmortyapp.model.MovieCharacter;
import dominskyi.springboot.rickandmortyapp.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieCharacterMapperTest {

    private MovieCharacterMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new MovieCharacterMapper();
    }

    @Test
    public void testParseApiCharacterResponseDto() {
        ApiCharacterDto apiCharacterDto = new ApiCharacterDto();
        apiCharacterDto.setId(1L);
        apiCharacterDto.setName("Rick Sanchez");
        apiCharacterDto.setGender("male");
        apiCharacterDto.setStatus("alive");

        MovieCharacter movieCharacter = mapper.parseApiCharacterResponseDto(apiCharacterDto);

        Assertions.assertEquals(apiCharacterDto.getName(), movieCharacter.getName());
        Assertions.assertEquals(Gender.MALE, movieCharacter.getGender());
        Assertions.assertEquals(Status.ALIVE, movieCharacter.getStatus());
        Assertions.assertEquals(apiCharacterDto.getId(), movieCharacter.getExternalId());
    }

    @Test
    public void testToResponseDto() {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setName("Rick Sanchez");
        movieCharacter.setGender(Gender.MALE);
        movieCharacter.setStatus(Status.ALIVE);
        movieCharacter.setExternalId(1001L);

        CharacterResponseDto responseDto = mapper.toResponseDto(movieCharacter);

        Assertions.assertEquals(movieCharacter.getId(), responseDto.getId());
        Assertions.assertEquals(movieCharacter.getExternalId(), responseDto.getExternalId());
        Assertions.assertEquals(movieCharacter.getName(), responseDto.getName());
        Assertions.assertEquals(movieCharacter.getStatus(), responseDto.getStatus());
        Assertions.assertEquals(movieCharacter.getGender(), responseDto.getGender());
    }
}
