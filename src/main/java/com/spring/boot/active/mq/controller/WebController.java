package com.spring.boot.active.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.boot.active.mq.model.MyCustomObject;

@RestController
public class WebController {

  @Autowired
  private JmsTemplate jmsTemplate;

  @GetMapping("/")
  public ResponseEntity<Void> sendMessage() {

    MyCustomObject input = new MyCustomObject();

    input.setValue1("Message 1");
    input.setValue2("Message 2");
    input.setValue3("Message 3");
    input.setValue4("Message 4");
    input.setValue5("Message 5");

    jmsTemplate.convertAndSend("my-test-queue", input);

    return ResponseEntity.noContent().build();
  }

}
