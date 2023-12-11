package br.com.luis.sendEmail.domain.email;

public record EmailAttachmentDTO(String recipient, String message, String subject, String attachment) {
}
