package org.patternfinder.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FindPatternRequestDto(
        @NotNull @Size(max = 255) String pattern,
        @NotNull @Size(max = 10000) String input) {
}
