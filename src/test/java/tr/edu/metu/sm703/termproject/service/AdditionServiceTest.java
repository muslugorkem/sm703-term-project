package tr.edu.metu.sm703.termproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdditionServiceTest {

    @Autowired
    private AdditionService additionService;

    @Test
    public void additionServiceAddsCorrectly() {
        int sum = additionService.add(5, 3);
        assertThat(sum).isEqualTo(3);
    }
}

