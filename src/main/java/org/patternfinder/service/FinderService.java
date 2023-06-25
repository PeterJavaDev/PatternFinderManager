package org.patternfinder.service;

import org.patternfinder.dto.FindPatternResponseDto;
import org.patternfinder.dto.SearchStatusDto;

public interface FinderService {

    FindPatternResponseDto requestPatternSearch(String pattern, String input);
    SearchStatusDto checkStatus(Long id);

}
