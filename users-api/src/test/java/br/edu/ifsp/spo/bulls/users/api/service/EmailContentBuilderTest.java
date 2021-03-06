package br.edu.ifsp.spo.bulls.users.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.thymeleaf.TemplateEngine;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailContentBuilderTest {

    @Autowired
    TemplateEngine te;

    @Test
    void verify_message_in_template_engine(){
        EmailContentBuilder ecb = new EmailContentBuilder(te);
        String content = "Confirm your e-mail";
        String result = ecb.build("Confirm your e-mail");
        assertTrue(result.contains(content));
    }
}
