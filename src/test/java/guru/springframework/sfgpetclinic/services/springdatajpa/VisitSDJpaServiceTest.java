package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        Visit visit = new Visit();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        assertThat(foundVisits).isNotNull();
        verify(visitRepository).findAll();
        assertThat(foundVisits).hasSize(1);

    }

    @Test
    void save() {
        Visit visit = new Visit();
        Visit anotherVisit = new Visit();
        when(visitRepository.save(visit)).thenReturn(visit);

        service.save(visit);

        assertThat(visit).isNotNull();
        verify(visitRepository).save(any(Visit.class));
        verify(visitRepository).save(visit);
        verify(visitRepository,never()).save(anotherVisit);
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit foundVisitObject = service.findById(1L);
        assertThat(foundVisitObject).isNotNull();

        verify(visitRepository).findById(anyLong());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepository).deleteById(anyLong());
        verify(visitRepository).deleteById(1L);
        verify(visitRepository,never()).deleteById(5L);
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        Visit anotherVisit = new Visit();

        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
        verify(visitRepository).delete(visit);
        verify(visitRepository,never()).delete(anotherVisit);
    }
}