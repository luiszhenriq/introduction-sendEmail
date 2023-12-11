package br.com.luis.sendEmail.domain.email;

public record EmailDTO(String recipient, String message, String subject) {
}
