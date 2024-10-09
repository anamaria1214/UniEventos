package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.EmailDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.simplejavamail.config.ConfigLoader.Property.SMTP_PORT;

@Service
public class EmailServicioImpl implements EmailServicio {

    @Override
    @Async
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {

        Email email = EmailBuilder.startingBlank()
                .from("unieventosq@gmail.com")
                .to(emailDTO.destinatario())
                .withSubject(emailDTO.asunto())
                .withPlainText(emailDTO.cuerpo())
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "unieventosq@gmail.com", "uljn laxv yiwk eerv")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);
        }

    }


}

