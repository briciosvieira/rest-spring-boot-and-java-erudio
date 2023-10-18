package br.com.briciosvieira.APISpringBoot.controllers;


import br.com.briciosvieira.APISpringBoot.sevices.PersonService;
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
    PersonService personService;

    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @GetMapping( value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonVO findById(@PathVariable (value = "id") Long id ) throws Exception{
      return personService.findById(id);
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public PersonVO create(@RequestBody PersonVO personVo ){
        return personService.create(personVo);
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonVO update( @RequestBody PersonVO personVo ){
        return personService.update(personVo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id ) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
