package ro.rentamotorcycle.rentamotorcycle.emailservice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;

import jakarta.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Value("classpath:/email/TemplateEmail.html")
    private Resource welcomeEmailResource;

    public EmailSenderService(JavaMailSender javaMailSender, Resource welcomeEmailResource) {
        this.javaMailSender = javaMailSender;
        this.welcomeEmailResource = welcomeEmailResource;
    }

    public void sendWelcomeEmail(String to, String fullName) throws MessagingException, IOException, jakarta.mail.MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        messageHelper.setTo(to);
        messageHelper.setSubject("Bun venit la Bike Madness!");

        String welcomeEmailContent = createWelcomeEmailContent(fullName);
        messageHelper.setText(welcomeEmailContent, true);

        javaMailSender.send(mimeMessage);
    }

    private String createWelcomeEmailContent(String fullName) throws IOException {
        String template = IOUtils.toString(welcomeEmailResource.getInputStream(), StandardCharsets.UTF_8);
        return template.replace("{{fullName}}", fullName);
    }
}