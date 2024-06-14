package com.business.intelligence.service.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class PingController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> auth(@RequestHeader final HttpHeaders headers) throws Exception {
        return ResponseEntity.ok("pong");
    }
}
