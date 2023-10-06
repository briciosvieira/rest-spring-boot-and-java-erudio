package br.com.briciosvieira.APISpringBoot.sevices;

import br.com.briciosvieira.APISpringBoot.exceptions.ResourcesNotFoundException;
import br.com.briciosvieira.APISpringBoot.model.Person;
import br.com.briciosvieira.APISpringBoot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

        @Autowired
        PersonRepository repository;

    public List<Person> findAll(){
        return repository.findAll();
    }


    public Person findById(Long id){
        logger.info("Finding one person!");
        return repository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("Usuário não encontrado"));
    }

    public Person create(Person person){
        logger.info("create one person!");
        return repository.save(person);
    }
    public Person update(Person person ){
        logger.info("update one person!");

        var entity =  repository.findById(person.getId()).orElseThrow(()-> new ResourcesNotFoundException("Usuário não atualizado"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);

    }

    public void delete(Long id){
        logger.info("Delete one person!");
        var entity =  repository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("Usuário não Deletado"));

        repository.delete(entity);
    }

}
