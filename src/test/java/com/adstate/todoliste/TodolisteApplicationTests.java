package com.adstate.todoliste;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(
        classes = TodolisteApplication.class,
        loader = SpringBootContextLoader.class
)
@SpringBootTest(classes = TodolisteApplication.class)
public abstract class TodolisteApplicationTests {
}
