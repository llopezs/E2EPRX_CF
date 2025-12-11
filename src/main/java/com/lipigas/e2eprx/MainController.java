package com.lipigas.e2eprx;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")

public class MainController {
   @GetMapping(path = "/helloWorld")
   public ResponseEntity<String> getDroneMedications() {
      return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
   }

}