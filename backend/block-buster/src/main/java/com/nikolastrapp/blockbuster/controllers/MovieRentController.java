package com.nikolastrapp.blockbuster.controllers;

import com.nikolastrapp.blockbuster.dtos.MovieRentDto;
import com.nikolastrapp.blockbuster.models.Customer;
import com.nikolastrapp.blockbuster.models.Mail;
import com.nikolastrapp.blockbuster.models.MovieRent;
import com.nikolastrapp.blockbuster.models.enums.StatusEmail;
import com.nikolastrapp.blockbuster.services.CustomerService;
import com.nikolastrapp.blockbuster.services.MailService;
import com.nikolastrapp.blockbuster.services.MovieRentService;
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
@RequestMapping(value = "/movie-controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MovieRentController {

    @Autowired
    private MovieRentService movieRentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender javaMailSender;



    @GetMapping
    public ResponseEntity<List<MovieRent>> findAll(){
        return ResponseEntity.ok().body(movieRentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<MovieRent> optionalMovieRent = movieRentService.findById(id);

        if (optionalMovieRent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie rent found with UUID \"" + id + "\"");
        } else {
            return ResponseEntity.ok().body(optionalMovieRent.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save (@RequestBody @Valid MovieRentDto movieRentDto){
        MovieRent movieRent = new MovieRent();
        Mail mail = new Mail();
        BeanUtils.copyProperties(movieRentDto, movieRent);

        Optional<Customer> optionalCustomer = customerService.findById(movieRentDto.getCustomerId());

        if (optionalCustomer.isPresent()){
            try{
                movieRent.setOrderDate(LocalDateTime.now());
                movieRent.setCustomer(optionalCustomer.get());

                mail.setEmailFrom("nikolastrapp@gmail.com");
                mail.setEmailTo(optionalCustomer.get().getEmail());
                mail.setSubject("Testing the mail service from blockbuster REST API");
                mail.setSendDateEmail(LocalDateTime.now());
                mail.setText("Salve pai eu tou aqui fazendo um teste de leve só pra ver se essa bomba ta funcionando tlgd?\n"
                        + "Tu pegou esse filme aqui: " + movieRent.getMovieTitle() + "\n Pelo precinho de apenas: " + movieRent.getMoviePrice()
                        + "\n no dia: " + movieRent.getOrderDate());

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
            return ResponseEntity.status(HttpStatus.CREATED).body(movieRentService.save(movieRent));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer UUID not found!");
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<MovieRent> optionalMovieRent = movieRentService.findById(id);

        if (optionalMovieRent.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie rent found with UUID \"" + id + "\"");
        } else {
            movieRentService.delete(id);
            return ResponseEntity.ok().body("Movie rent deleted!");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid MovieRentDto movieRentDto){
        Optional<MovieRent> optionalMovieRent = movieRentService.findById(id);

        if (optionalMovieRent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie rent found with UUID \"" + id + "\"");
        } else {
            MovieRent movieRent = new MovieRent();
            BeanUtils.copyProperties(movieRentDto, movieRent);
            return ResponseEntity.status(HttpStatus.OK).body(movieRentService.update(movieRent));
        }
    }


}

