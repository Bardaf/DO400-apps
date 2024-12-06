package com.redhat.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.redhat.training.service.SolverService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiplierResourceTest {
    
    SolverService solverService;
    MultiplierResource multiplierResource;

    @BeforeEach
    public void setup() {
        solverService = mock(SolverService.class);
        multiplierResource = new MultiplierResource(solverService);
    }


    @Test
    public void simpleMultiplication() { 
        // Given
        when(solverService.solve("2")).thenReturn(Float.valueOf("2"));
        when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
            
        // When
        Float result = multiplierResource.multiply("2", "3");
                        
        // Then
        assertEquals( 6.0f, result );
    }

    @Test
    public void negativeMultiplication() { 
        // Given
        when(solverService.solve("-2")).thenReturn(Float.valueOf("-2"));
        when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
            
        // When
        Float result = multiplierResource.multiply("-2", "3");
                        
        // Then
        assertEquals( -6.0f, result );
    }

    @Test
    public void nonValideMultiplication() { 
        // Given
        when(solverService.solve("P")).thenReturn(Float.valueOf("NaN"));
        when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
            
        // When
        Float result = multiplierResource.multiply("P", "3");
                        
        // Then
        assertEquals( true, Float.isNaN(result) );
    }
}
