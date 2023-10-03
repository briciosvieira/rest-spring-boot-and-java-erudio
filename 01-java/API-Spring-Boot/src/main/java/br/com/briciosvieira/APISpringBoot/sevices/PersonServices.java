package br.com.briciosvieira.APISpringBoot.sevices;

import br.com.briciosvieira.APISpringBoot.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++ ){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }


    public Person findById(String id){
        logger.info("Finding one person!");

         Person person = new Person();
         person.setId(counter.incrementAndGet());
         person.setFirstName("Fabricio");
         person.setLastName("Vieira");
         person.setAdress("amendoeira");
         person.setGender("Male");
        return person;
    }

    public Person create(Person person){
        logger.info("create one person!");
        return person;
    }
    public Person update(Person person){
        logger.info("update one person!");

        return person;
    }

    public void delete(String id){
        logger.info("Delete one person!");

    }

    private Person mockPerson(int i) {
        logger.info("Finding all person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("nome "+ i);
        person.setLastName("sobrenome "+ i);
        person.setAdress("endereço "+ i);
        person.setGender("genero" + i);
        return person;
    }
}
