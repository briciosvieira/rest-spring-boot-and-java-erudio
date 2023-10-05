package br.com.briciosvieira.APISpringBoot.repository;

import br.com.briciosvieira.APISpringBoot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>  {

}
