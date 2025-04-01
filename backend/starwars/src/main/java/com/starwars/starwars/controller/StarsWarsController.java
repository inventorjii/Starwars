package com.starwars.starwars.controller;


import com.starwars.starwars.dto.StarWarsApiOutput;
import com.starwars.starwars.entity.StarWarsType;
import com.starwars.starwars.service.StarWarsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/starwars")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:80","http://localhost:5174","http://localhost"})
@Validated
public class StarsWarsController {

    @Autowired
    StarWarsService starWarsService;

    @Operation(
            summary = "Fetch Star Wars Data",
            description = "Retrieves Star Wars character or planet data based on type and name."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StarWarsApiOutput.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<Object> getStartsWarsData(@RequestParam @NotBlank(message = "Type cannot be empty") String type,
                                                               @RequestParam @Valid @NotBlank(message = "Name cannot be empty")
                                                               @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces") String name,
                                                               @RequestParam(required = false) boolean offline
    ){
        // Validate `type` against allowed values
        if (!StarWarsType.isValid(type)) {
            throw new IllegalArgumentException("Invalid type. Allowed values: people, planets, starships, vehicles, species, films");
        }

        return ResponseEntity.status(HttpStatus.OK).body(starWarsService.getStarWarsData(type,name,offline));
    }

}
