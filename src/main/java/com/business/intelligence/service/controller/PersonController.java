package com.business.intelligence.service.controller;

import com.business.intelligence.service.exception.UserAlreadyExist;
import com.business.intelligence.service.exception.WrongPassword;
import com.business.intelligence.service.model.people.Person;
import com.business.intelligence.service.model.people.Role;
import com.business.intelligence.service.repository.PersonRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestHeader final HttpHeaders headers, final Person person) throws Exception {
        final Optional<Person> existedPerson = personRepository.findByLogin(person.getLogin());
        if (existedPerson.isPresent()) {
            throw new UserAlreadyExist("User already exist" + person.getLogin());
        }
        return ResponseEntity.ok(personRepository.save(person));
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> auth(@RequestHeader final HttpHeaders headers, final Person person) throws Exception {
        final Optional<Person> existedPerson = personRepository.findByLogin(person.getLogin());
        if (existedPerson.isEmpty()) {
            throw new UserAlreadyExist("User not exist " + person.getLogin());
        }
        if (!person.getPassword().equals(existedPerson.get().getPassword())) {
            throw new WrongPassword("Wrong password");
        }

        final String jwt = Jwts.builder()
                .setIssuer("BIS")
                .setSubject(person.getLogin())
                .claim("scope", person.getRole())
                // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
                .setIssuedAt(Date.from(Instant.now()))
                // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                .setExpiration(Date.from(Instant.now().plus(10800, ChronoUnit.SECONDS)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        Base64.getDecoder().decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                )
                .compact();

        return ResponseEntity.ok(jwt);
    }

    @RequestMapping(value = "/auth-test", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> auth(@RequestHeader final HttpHeaders headers) throws Exception {

        Person person = new Person();
        person.setId(1);
        person.setSurname("Герман");
        person.setFirstName("Илья");
        person.setFatherName("Владимирович");
        person.setLogin("login");
        person.setRole(Role.DIRECTOR);
        person.setPassword("password");

        try {
            create(headers, person);
        } catch (Exception ex) {
            //NOP
        }

        return auth(headers, person);
    }

}
