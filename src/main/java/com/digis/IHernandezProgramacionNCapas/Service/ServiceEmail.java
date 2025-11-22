package com.digis.IHernandezProgramacionNCapas.Service;

import com.digis.IHernandezProgramacionNCapas.Component.JwtUtil;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class ServiceEmail {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JwtUtil jwtUtil;

    public void sendEmail(Usuarios usuario) throws MessagingException, UnsupportedEncodingException {
        String token = jwtUtil.generarToken(usuario.getIdUsuario());
        String enlace = "http://localhost:8080/activar-cuenta?token=" + token;

        Context context = new Context();
        context.setVariable("usuario", usuario);
        context.setVariable("enlace", enlace);

        // Procesar la plantilla con los datos del usuario
        String htmlContent = templateEngine.process("EmailTem", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom("soporte@tudominio.com", "Soporte Técnico");
        helper.setSubject("Activación de Cuenta");
        helper.setTo(usuario.getEmail());
        helper.setText(htmlContent, true);

        // Enviar el correo
        mailSender.send(mimeMessage);
    }
}
