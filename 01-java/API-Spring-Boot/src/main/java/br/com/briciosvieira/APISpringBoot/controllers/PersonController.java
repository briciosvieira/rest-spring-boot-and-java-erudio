package br.com.briciosvieira.APISpringBoot.controllers;


import br.com.briciosvieira.APISpringBoot.model.Person;
import br.com.briciosvieira.APISpringBoot.sevices.PersonServices;
import br.com.briciosvieira.APISpringBoot.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonServices personServices;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){
        return personServices.findAll();
    }

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable (value = "id") Long id ) throws Exception{
      return personServices.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO personVo ){
        return personServices.create(personVo);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update( @RequestBody PersonVO personVo ){
        return personServices.update(personVo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id ) {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
