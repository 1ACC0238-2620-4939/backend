package com.trakto.traktoroute.trip;

import com.trakto.traktoroute.trip.application.services.TripQueryService;
import com.trakto.traktoroute.trip.domain.repositories.TripRepository;
import com.trakto.traktoroute.trip.interfaces.rest.controllers.TripQueryController;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripQueryErrorTests {
    @Test void missingTripReturns404RatherThanThrowing500() {
        var repository = mock(TripRepository.class);
        when(repository.findById(any())).thenReturn(Optional.empty());
        var controller = new TripQueryController(new TripQueryService(repository));
        var response = controller.getTripById(UUID.randomUUID());
        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
