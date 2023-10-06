package br.com.briciosvieira.APISpringBoot.controllers;


import br.com.briciosvieira.APISpringBoot.model.Person;
import br.com.briciosvieira.APISpringBoot.sevices.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
        return  personServices.findAll();
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable (value = "id") Long id ) throws Exception{
      return personServices.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person ){
        return personServices.create(person);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update( @RequestBody Person person ){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id ) {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
