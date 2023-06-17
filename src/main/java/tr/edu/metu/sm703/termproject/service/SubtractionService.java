package tr.edu.metu.sm703.termproject.service;

import org.springframework.stereotype.Service;

@Service
public class SubtractionService {

    public int subtract(int a, int b) {
        return a - b;
    }
}
