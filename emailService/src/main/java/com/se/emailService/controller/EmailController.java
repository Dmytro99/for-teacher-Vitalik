package com.se.emailService.controller;

import com.se.emailService.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

  @Autowired
  private EmailService emailService;

  @GetMapping(value = "/{user-email}")
  public ResponseEntity<String> sendSimpleEmail(@PathVariable("user-email") String email) {

    try {
      emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for your!!");
    } catch (MailException mailException) {
      log.error("Error while sending out email..{}", mailException.getStackTrace());
      log.error("Error while sending out email..{}", mailException.fillInStackTrace());
      return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
  }


}
