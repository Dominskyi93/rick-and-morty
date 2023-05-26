package dominskyi.springboot.rickandmortyapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dominskyi.springboot.rickandmortyapp.dto.external.ApiCharacterDto;
import dominskyi.springboot.rickandmortyapp.dto.external.ApiInfoDto;
import dominskyi.springboot.rickandmortyapp.dto.external.ApiResponseDto;
import dominskyi.springboot.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import dominskyi.springboot.rickandmortyapp.model.MovieCharacter;
import dominskyi.springboot.rickandmortyapp.repository.MovieCharacterRepository;
import dominskyi.springboot.rickandmortyapp.service.HttpClient;
import dominskyi.springboot.rickandmortyapp.service.MovieCharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

class MovieCharacterServiceImplTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private MovieCharacterRepository movieCharacterRepository;

    @Mock
    private MovieCharacterMapper mapper;

    private MovieCharacterService movieCharacterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieCharacterService = new MovieCharacterServiceImpl(httpClient, movieCharacterRepository, mapper);
    }

    @Test
    void syncExternalCharacters_shouldSyncAndSaveCharacters() {
        ApiResponseDto apiResponseDto1 = new ApiResponseDto();
        apiResponseDto1.setResults(new ApiCharacterDto[]{new ApiCharacterDto()});
        apiResponseDto1.setInfo(new ApiInfoDto());
        apiResponseDto1.getInfo().setNext("https://rickandmortyapi.com/api/character?page=2");

        ApiResponseDto apiResponseDto2 = new ApiResponseDto();
        apiResponseDto2.setResults(new ApiCharacterDto[]{new ApiCharacterDto()});
        apiResponseDto2.setInfo(new ApiInfoDto());
        apiResponseDto2.getInfo().setNext(null);

        when(httpClient.get(eq("https://rickandmortyapi.com/api/character"), eq(ApiResponseDto.class)))
                .thenReturn(apiResponseDto1);
        when(httpClient.get(eq("https://rickandmortyapi.com/api/character?page=2"), eq(ApiResponseDto.class)))
                .thenReturn(apiResponseDto2);
        when(movieCharacterRepository.findAllByExternalIdIn(anySet())).thenReturn(List.of(new MovieCharacter()));
        when(mapper.parseApiCharacterResponseDto(any(ApiCharacterDto.class))).thenReturn(new MovieCharacter());
        movieCharacterService.syncExternalCharacters();
        verify(httpClient, times(2)).get(anyString(), eq(ApiResponseDto.class));
    }


    @Test
    void getRandomCharacter_shouldReturnRandomCharacter() {
        long count = 10;
        MovieCharacter movieCharacter = new MovieCharacter();
        when(movieCharacterRepository.count()).thenReturn(count);
        when(movieCharacterRepository.getReferenceById(anyLong())).thenReturn(movieCharacter);
        MovieCharacter result = movieCharacterService.getRandomCharacter();
        assertNotNull(result);
        verify(movieCharacterRepository).count();
        verify(movieCharacterRepository).getReferenceById(anyLong());
    }

    @Test
    void findAllByNameContains_shouldReturnMatchingCharacters() {
        String namePart = "Rick";
        List<MovieCharacter> characters = Arrays.asList(new MovieCharacter(), new MovieCharacter());
        when(movieCharacterRepository.findAllByNameContains(eq(namePart))).thenReturn(characters);
        List<MovieCharacter> result = movieCharacterService.findAllByNameContains(namePart);
        assertEquals(characters, result);
        verify(movieCharacterRepository).findAllByNameContains(eq(namePart));
    }
}
