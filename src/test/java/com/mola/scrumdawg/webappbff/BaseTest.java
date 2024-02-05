package com.mola.scrumdawg.webappbff;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseTest {

    private int currentBaseValue = 0;


    protected String nextString(){
        return String.valueOf(currentBaseValue++);
    }

    protected Long nextLong(){
        return (long) currentBaseValue++;
    }

    protected Integer nextInt(){
        return currentBaseValue++;
    }

    protected Double nextDouble(){
        return (double) currentBaseValue++;
    }
    protected boolean nextBoolean(){
        return currentBaseValue++ % 2 == 0;
    }
}
