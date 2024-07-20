package com.nishchith.emailservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishchith.emailservice.dtos.SendEmailEventDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class SendEmailController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public SendEmailController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    //send email
    @GetMapping("/{emailId}")
    public SendEmailEventDto sendEmail(@PathVariable("emailId") String emailId) {
        SendEmailEventDto sendEmailEventDto = new SendEmailEventDto();
        sendEmailEventDto.setToEmail(emailId);
        sendEmailEventDto.setSubject("Test Email");
        sendEmailEventDto.setBody("This is a test email");
        sendEmailEventDto.setFromEmail("test@gmail.com");
        try {
            kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(sendEmailEventDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendEmailEventDto;
    }
}
