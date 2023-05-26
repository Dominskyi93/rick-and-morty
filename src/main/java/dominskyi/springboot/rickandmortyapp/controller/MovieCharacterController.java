package dominskyi.springboot.rickandmortyapp.controller;

import dominskyi.springboot.rickandmortyapp.dto.CharacterResponseDto;
import dominskyi.springboot.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import dominskyi.springboot.rickandmortyapp.model.MovieCharacter;
import dominskyi.springboot.rickandmortyapp.service.MovieCharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper mapper;

    @GetMapping("/random")
    @ApiOperation(value = "Get a random character")
    public CharacterResponseDto getRandom() {
        MovieCharacter character = movieCharacterService.getRandomCharacter();
        return mapper.toResponseDto(character);
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get a list of characters containing a string in the name")
    public List<CharacterResponseDto> findAllByName(
            @ApiParam(value = "The String that should be contained in the character's name")
            @RequestParam String namePart) {
        return movieCharacterService.findAllByNameContains(namePart).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
