package com.nikolastrapp.desafio.controllers;

import com.nikolastrapp.desafio.dtos.RegistroDto;
import com.nikolastrapp.desafio.models.Mail;
import com.nikolastrapp.desafio.models.Registro;
import com.nikolastrapp.desafio.models.Mutante;
import com.nikolastrapp.desafio.models.enums.Especie;
import com.nikolastrapp.desafio.models.enums.StatusEmail;
import com.nikolastrapp.desafio.services.MailService;
import com.nikolastrapp.desafio.services.RegistroService;
import com.nikolastrapp.desafio.services.MutanteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/registro")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private MutanteService mutanteService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping
    public ResponseEntity<List<Registro>> findAll(){
        return ResponseEntity.ok().body(registroService.findAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<Registro> optionalRegistro = registroService.findById(id);

        if (optionalRegistro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No registro found with id " + id);
        } else {
            return ResponseEntity.ok().body(optionalRegistro.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegistroDto registroDto){
        Registro registro = new Registro();
        Mail mail = new Mail();
        Mail mail2 = new Mail();
        Optional<Mutante> optionalMutante = mutanteService.findByUuid(registroDto.getMutanteId());

        if (optionalMutante.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No mutante found with id " + registroDto.getMutanteId());
        } else {
            try{
                BeanUtils.copyProperties(registroDto, registro);
                registro.setMutante(optionalMutante.get());
                if (optionalMutante.get().estaDentro()){
                    registro.setDataSaida(LocalDateTime.now());
                    optionalMutante.get().setDentro(false);

                } else {
                    registro.setDataEntrada(LocalDateTime.now());
                    optionalMutante.get().setDentro(true);

                    mail.setEmailFrom("mansaoX@gmail.com");
                    mail2.setEmailFrom("mansaoX@gmail.com");
                    mail.setEmailTo(optionalMutante.get().getEmail());
                    mail2.setEmailTo("professorxavier@gmail.com");
                    mail.setSubject("Entrada do mutante");
                    mail2.setSubject("Entrada do mutante");
                    mail.setSendDateEmail(LocalDateTime.now());
                    mail2.setSendDateEmail(LocalDateTime.now());
                    mail2.setText("o mutante" + optionalMutante.get().getName() + " entrou na mansão");

                    if (optionalMutante.get().getEspecie() == Especie.HOMO_SUPERIOR){
                        mail.setText("Bem vindo á mansão");
                    } else {
                        mail.setText("Você entrou na mansão");
                    }
                    SimpleMailMessage message = new SimpleMailMessage();
                    SimpleMailMessage message2 = new SimpleMailMessage();
                    message.setFrom(mail.getEmailFrom());
                    message.setTo(mail.getEmailTo());
                    message.setSubject(mail.getSubject());
                    message.setText(mail.getText());

                    message2.setFrom(mail.getEmailFrom());
                    message2.setTo(mail.getEmailTo());
                    message2.setSubject(mail.getSubject());
                    message2.setText(mail.getText());

                    javaMailSender.send(message);
                    javaMailSender.send(message2);
                    mail.setStatusEmail(StatusEmail.SENT);
                    mail2.setStatusEmail(StatusEmail.SENT);
                }
            } catch (MailException err){
                System.out.println(err.getMessage());
                mail.setStatusEmail(StatusEmail.ERROR);
                mail2.setStatusEmail(StatusEmail.ERROR);
            } finally {
                mailService.save(mail);
                mailService.save(mail2);
            }

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(registroService.save(registro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        Optional<Registro> optionalRegistro = registroService.findById(id);

        if (optionalRegistro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No registro found with id " + id);
        } else {
            registroService.delete(optionalRegistro.get());
            return ResponseEntity.ok().body("Registro deleted!");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid RegistroDto registroDto, @PathVariable UUID id){
        Optional<Registro> optionalRegistro = registroService.findById(id);
        Optional<Mutante> optionalMutante = mutanteService.findByUuid(registroDto.getMutanteId());

        if (optionalRegistro.isEmpty() || optionalMutante.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UUID not found: " + id);
        } else {
            if (registroDto.getDataSaida() != null){
                optionalRegistro.get().setDataSaida(registroDto.getDataSaida());
            } else if (optionalRegistro.get().getMutante().isDentro()){
                optionalRegistro.get().setDataSaida(LocalDateTime.now());
                optionalMutante.get().setDentro(false);
            } else {
                optionalRegistro.get().setDataEntrada(LocalDateTime.now());
                optionalMutante.get().setDentro(true);
            }
            return ResponseEntity.ok().body(registroService.save(optionalRegistro.get()));
        }
    }

}
