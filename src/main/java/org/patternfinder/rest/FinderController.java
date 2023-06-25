package org.patternfinder.rest;

import jakarta.validation.Valid;
import org.patternfinder.dto.FindPatternRequestDto;
import org.patternfinder.dto.FindPatternResponseDto;
import org.patternfinder.dto.SearchStatusDto;
import org.patternfinder.service.FinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class FinderController {

    private FinderService finderService;

    @Autowired
    public FinderController(FinderService finderService) {
        this.finderService = finderService;
    }

    @PostMapping(value = "/findPattern")
    public ResponseEntity<FindPatternResponseDto> findPattern(@Valid @RequestBody FindPatternRequestDto findPatternRequestDto) {
        return new ResponseEntity<>(finderService.requestPatternSearch(
                findPatternRequestDto.pattern(),
                findPatternRequestDto.input()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/checkStatus/{id}")
    public ResponseEntity<SearchStatusDto> checkStatus(@PathVariable Long id) {
        return new ResponseEntity<>(finderService.checkStatus(id), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
