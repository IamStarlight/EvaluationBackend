package com.example.evaluation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class EvaluationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testBCrypt(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode("admin");

        System.out.println(encode1);

        System.out.println(passwordEncoder.matches("admin",
                "$2a$10$r4E89r7VwOzaW7jjdtSAzuekd/0NRXqzSp2eX0NLYqrsy.7jWNkgi"));
    }
    /*
    admin
$2a$10$r4E89r7VwOzaW7jjdtSAzuekd/0NRXqzSp2eX0NLYqrsy.7jWNkgi
         */
}
