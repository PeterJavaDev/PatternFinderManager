package org.patternfinder.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.patternfinder.dto.FindPatternResponseDto;
import org.patternfinder.dto.SearchStatusDto;
import org.patternfinder.exception.NoSearchTaskFound;
import org.patternfinder.model.SearchTask;
import org.patternfinder.model.SearchTaskStatus;
import org.patternfinder.repository.SearchTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinderServiceImpl implements FinderService {

    private static final Logger log = LogManager.getLogger(FinderServiceImpl.class);

    private SearchTaskRepository searchTaskRepository;

    @Autowired
    public FinderServiceImpl(SearchTaskRepository searchTaskRepository) {
        this.searchTaskRepository = searchTaskRepository;
    }

    public FindPatternResponseDto requestPatternSearch(String pattern, String input) {
        log.debug("Requesting search of " + pattern);

        SearchTask searchTask = new SearchTask();
        searchTask.setPattern(pattern);
        searchTask.setInputText(input);
        searchTask.setStatus(SearchTaskStatus.READY);

        searchTaskRepository.save(searchTask);

        return new FindPatternResponseDto(searchTask.getId(), "success");
    }

    public SearchStatusDto checkStatus(Long id) {
        log.debug("Checking status of " + id);

        Optional<SearchTask> searchTaskOptional = searchTaskRepository.findById(id);
        if (searchTaskOptional.isPresent()) {
            SearchTask searchTask = searchTaskOptional.get();
            return new SearchStatusDto(
                    searchTask.getStatus(),
                    searchTask.getProgress(),
                    searchTask.getPosition(),
                    searchTask.getTypos());
        } else {
            log.error("Search task with ID: " + id + " not found.");
            throw new NoSearchTaskFound();
        }
    }

}
