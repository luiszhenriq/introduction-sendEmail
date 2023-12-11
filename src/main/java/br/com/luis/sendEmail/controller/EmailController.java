package br.com.luis.sendEmail.controller;

import br.com.luis.sendEmail.domain.email.EmailAttachmentDTO;
import br.com.luis.sendEmail.domain.email.EmailDTO;
import br.com.luis.sendEmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public String sendMail(@RequestBody EmailDTO details) {
        return emailService.sendMail(details);
    }

    @PostMapping ("/attachment")
    public String sendMailWithAttachment(@RequestBody EmailAttachmentDTO details) {
        return emailService.sendMailWithAttachment(details);
    }
}
