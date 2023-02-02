package com.nikolastrapp.blockbuster.controllers;


import com.nikolastrapp.blockbuster.dtos.MailDto;
import com.nikolastrapp.blockbuster.models.Customer;
import com.nikolastrapp.blockbuster.models.Mail;
import com.nikolastrapp.blockbuster.services.CustomerService;
import com.nikolastrapp.blockbuster.services.MailService;
import com.nikolastrapp.blockbuster.services.MovieRentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MovieRentService movieRentService;


    @GetMapping
    public ResponseEntity<List<Mail>> findAll(){
        return ResponseEntity.ok().body(mailService.findAll());
    }

    @PostMapping
    public ResponseEntity<Mail> save(@RequestBody @Valid MailDto mailDto){
        Mail mail = new Mail();
        BeanUtils.copyProperties(mailDto, mail);
        return ResponseEntity.status(HttpStatus.CREATED).body(mailService.save(mail));

    }


}
