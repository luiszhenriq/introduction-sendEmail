package br.com.luis.sendEmail.service;


import br.com.luis.sendEmail.domain.email.EmailAttachmentDTO;
import br.com.luis.sendEmail.domain.email.EmailDTO;
import br.com.luis.sendEmail.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendMail(EmailDTO details) {
        LOGGER.info("Enviando e-mail simples");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.recipient());
        mailMessage.setText(details.message());
        mailMessage.setSubject(details.subject());

        javaMailSender.send(mailMessage);

        LOGGER.info("E-mail enviado com sucesso!");
        return "E-mail enviado com sucesso!";
    }

    public String sendMailWithAttachment(EmailAttachmentDTO details) {
        LOGGER.info("Enviando e-mail com anexo");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.recipient());
            mimeMessageHelper.setText(details.message());
            mimeMessageHelper.setSubject(details.subject());

            FileSystemResource file = new FileSystemResource(new File(details.attachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(mimeMessage);
            LOGGER.info("E-mail enviado com sucesso!");

            return "E-mail enviado com sucesso!";
        } catch (MessagingException e) {
            LOGGER.error("Erro ao enviar o email", e);

            throw new RuntimeException(e);
        }
    }
}
