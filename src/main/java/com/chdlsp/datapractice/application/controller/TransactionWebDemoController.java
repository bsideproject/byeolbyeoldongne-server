package com.chdlsp.datapractice.application.controller;

import com.chdlsp.datapractice.service.TransactionDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/web")
public class TransactionWebDemoController {

    @Autowired
    private TransactionDemoService transactionDemoService;

    @GetMapping("/success")
    public ResponseEntity readNewUserInfoWithDefault() {

        return ResponseEntity.ok("success");
    }

    @GetMapping("/failure")
    public ResponseEntity readNewUserInfoWithReadOnly() {

        return ResponseEntity.ok("failure");
    }

}
