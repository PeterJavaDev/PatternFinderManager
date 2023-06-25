package org.patternfinder.dto;

import org.patternfinder.model.SearchTaskStatus;

public record SearchStatusDto (
    SearchTaskStatus status,
    Integer progress,
    Integer position,
    Integer typos
) {};
