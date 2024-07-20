package com.nishchith.emailservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailEventDto {

    private String subject;
    private String body;
    private String toEmail;
    private String ccEmail;
    private String bccEmail;
    private String fromEmail;

}
