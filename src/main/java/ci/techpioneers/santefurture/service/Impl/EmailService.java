package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.service.dto.TicketDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    public void sendTicketEmail(TicketDTO ticketDTO) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(ticketDTO.getEmailPatient());
            helper.setSubject("Confirmation de votre ticket");

            String body = constructEmailBody(ticketDTO);
            helper.setText(body, true);

            helper.addInline("logoImage", new ClassPathResource("static/images/logo.png"));

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de ticket consultation", e);
        }
    }
    private String constructEmailBody(TicketDTO ticketDTO) {
        Context context = new Context();
        context.setVariable("ticket", ticketDTO);
        return templateEngine.process("ticket-success", context);

    }
}