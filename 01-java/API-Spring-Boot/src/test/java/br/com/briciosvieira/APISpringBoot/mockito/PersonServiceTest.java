package br.com.briciosvieira.APISpringBoot.mockito;
import br.com.briciosvieira.APISpringBoot.model.Person;
import br.com.briciosvieira.APISpringBoot.repository.PersonRepository;
import br.com.briciosvieira.APISpringBoot.sevices.PersonService;
import br.com.briciosvieira.APISpringBoot.unittests.mapper.MockPerson;
import br.com.briciosvieira.APISpringBoot.vo.v1.PersonVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }
   @Test
    void testFindAll() {
       List<Person> list = input.mockEntityList();
       when(repository.findAll()).thenReturn(list);
       var people = service.findAll();
       Assertions.assertNotNull(people);
       Assertions.assertEquals(14, people.size());
       var result = people.get(1);
       Assertions.assertNotNull(result);
       Assertions.assertNotNull("Address Test",result.getAddress());
       Assertions.assertNotNull("FirstName Test",result.getFirstName());
       Assertions.assertNotNull("LastName Test",result.getLastName());
       Assertions.assertNotNull("Female",result.getGender());
    }
    @Test
    void testFindById() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getLinks());
        Assertions.assertNotNull(result.toString().contains("links: [<api.person/>;rel=\"self\"]"));
        Assertions.assertNotNull("Address Test",result.getAddress());
        Assertions.assertNotNull("FirstName Test",result.getFirstName());
        Assertions.assertNotNull("LastName Test",result.getLastName());
        Assertions.assertNotNull("Female",result.getGender());
    }
    @Test
    void testCreate(){
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);
        PersonVO vo = input.mockVO(1);
        vo.setId(1L);
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull("Address Test",result.getAddress());
        Assertions.assertNotNull("FirstName Test",result.getFirstName());
        Assertions.assertNotNull("LastName Test",result.getLastName());
        Assertions.assertNotNull("Female",result.getGender());

    }
    @Test
    void testUpdate(){
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        Person persisted = entity;
        persisted.setId(1L);
        PersonVO vo = input.mockVO(1);
        vo.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull("Address Test",result.getAddress());
        Assertions.assertNotNull("FirstName Test",result.getFirstName());
        Assertions.assertNotNull("LastName Test",result.getLastName());
        Assertions.assertNotNull("Female",result.getGender());



    }
    @Test
    void testDelete(){
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }
}
