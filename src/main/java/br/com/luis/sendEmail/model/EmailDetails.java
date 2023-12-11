package br.com.luis.sendEmail.model;


import br.com.luis.sendEmail.domain.email.EmailAttachmentDTO;
import br.com.luis.sendEmail.domain.email.EmailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDetails {

    private String recipient;

    private String message;

    private String subject;

    private String attachment;

    public EmailDetails(EmailDTO emailDTO) {
        this.recipient = emailDTO.recipient();
        this.message = emailDTO.message();
        this.subject = emailDTO.subject();
    }

    public EmailDetails(EmailAttachmentDTO emailAttachmentDTO) {
        this.recipient = emailAttachmentDTO.recipient();
        this.message = emailAttachmentDTO.message();
        this.subject = emailAttachmentDTO.subject();
        this.attachment = emailAttachmentDTO.attachment();
    }
}
