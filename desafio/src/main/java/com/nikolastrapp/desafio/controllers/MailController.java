package com.nikolastrapp.desafio.controllers;

import com.nikolastrapp.desafio.models.Mail;
import com.nikolastrapp.desafio.models.Registro;
import com.nikolastrapp.desafio.models.enums.StatusEmail;
import com.nikolastrapp.desafio.services.MailService;
import com.nikolastrapp.desafio.services.MutanteService;
import com.nikolastrapp.desafio.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private MutanteService mutanteService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping
    public ResponseEntity<Object> sendMails(){
        List<Registro> registros = registroService.findAll();

        for (int i = 0; i < registros.size(); i++){
            Mail mail = new Mail();
            try{
                mail.setEmailFrom("mansaoX@gmail.com");
                mail.setEmailTo("professorxavier@gmail.com");
                mail.setSubject("Logs de entrada e saida");
                mail.setSendDateEmail(LocalDateTime.now());
                mail.setText("O montro: " + registros.get(i).getMutante().getName() + " está " +
                        (((registros.get(i).getMutante().isDentro()) ? "dentro" : "fora") + " da mansão.\n\n" +
                        "Data de entrada: " + registros.get(i).getDataEntrada() +
                        "\n\nData de saída: " + "Data de entrada: " + registros.get(i).getDataSaida()));

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(mail.getEmailFrom());
                message.setTo(mail.getEmailTo());
                message.setSubject(mail.getSubject());
                message.setText(mail.getText());

                javaMailSender.send(message);

                mail.setStatusEmail(StatusEmail.SENT);
            } catch (MailException err){
                System.out.println(err.getMessage());
                mail.setStatusEmail(StatusEmail.ERROR);
            } finally {
                mailService.save(mail);
            }
        }
        return ResponseEntity.ok().body("Emails enviados");
    }
}
