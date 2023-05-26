package dominskyi.springboot.rickandmortyapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import dominskyi.springboot.rickandmortyapp.dto.CharacterResponseDto;
import dominskyi.springboot.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import dominskyi.springboot.rickandmortyapp.model.MovieCharacter;
import dominskyi.springboot.rickandmortyapp.service.MovieCharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

class MovieCharacterControllerTest {

    @Mock
    private MovieCharacterService movieCharacterService;

    @Mock
    private MovieCharacterMapper mapper;

    private MovieCharacterController movieCharacterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieCharacterController = new MovieCharacterController(movieCharacterService, mapper);
    }

    @Test
    void getRandom_shouldReturnRandomCharacter() {
        MovieCharacter character = new MovieCharacter();
        CharacterResponseDto responseDto = new CharacterResponseDto();
        when(movieCharacterService.getRandomCharacter()).thenReturn(character);
        when(mapper.toResponseDto(character)).thenReturn(responseDto);
        CharacterResponseDto result = movieCharacterController.getRandom();
        assertEquals(responseDto, result);
    }

    @Test
    void findAllByName_shouldReturnMatchingCharacters() {
        String namePart = "Rick";
        MovieCharacter character1 = new MovieCharacter();
        MovieCharacter character2 = new MovieCharacter();
        List<MovieCharacter> characters = Arrays.asList(character1, character2);
        CharacterResponseDto responseDto1 = new CharacterResponseDto();
        CharacterResponseDto responseDto2 = new CharacterResponseDto();
        List<CharacterResponseDto> responseDtos = Arrays.asList(responseDto1, responseDto2);
        when(movieCharacterService.findAllByNameContains(namePart)).thenReturn(characters);
        when(mapper.toResponseDto(character1)).thenReturn(responseDto1);
        when(mapper.toResponseDto(character2)).thenReturn(responseDto2);
        List<CharacterResponseDto> result = movieCharacterController.findAllByName(namePart);
        assertEquals(responseDtos, result);
    }
}
