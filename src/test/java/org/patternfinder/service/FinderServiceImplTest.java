package org.patternfinder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.patternfinder.dto.SearchStatusDto;
import org.patternfinder.exception.NoSearchTaskFound;
import org.patternfinder.model.SearchTask;
import org.patternfinder.model.SearchTaskStatus;
import org.patternfinder.repository.SearchTaskRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FinderServiceImplTest {

    private FinderServiceImpl finderService;

    @Mock
    private SearchTaskRepository searchTaskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        finderService = new FinderServiceImpl(searchTaskRepository);
    }

    @Test
    public void testCheckStatus() {
        Long id = 1L;
        SearchTask searchTask = new SearchTask();
        searchTask.setId(id);
        searchTask.setStatus(SearchTaskStatus.DONE);
        searchTask.setProgress(100);
        searchTask.setPosition(5);
        searchTask.setTypos(2);

        when(searchTaskRepository.findById(id)).thenReturn(Optional.of(searchTask));

        SearchStatusDto status = finderService.checkStatus(id);

        assertNotNull(status);
        assertEquals(SearchTaskStatus.DONE, status.status());
        assertEquals(100, status.progress());
        assertEquals(5, status.position());
        assertEquals(2, status.typos());

        verify(searchTaskRepository, times(1)).findById(id);
    }

    @Test
    public void testCheckStatusNotFound() {
        Long id = 1L;
        when(searchTaskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSearchTaskFound.class, () -> finderService.checkStatus(id));

        verify(searchTaskRepository, times(1)).findById(id);
    }
}