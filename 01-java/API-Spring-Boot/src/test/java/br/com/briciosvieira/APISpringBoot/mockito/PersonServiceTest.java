package br.com.briciosvieira.APISpringBoot.mockito;

import br.com.briciosvieira.APISpringBoot.sevices.PersonServices;
import br.com.briciosvieira.APISpringBoot.unittests.mapper.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonServices  services;
    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }
   @Test
    void testFindAll(){

    }
    @Test
    void testFindById(){

    }
    @Test
    void testCreate(){

    }
    @Test
    void testUpdate(){

    }
    @Test
    void testDelete(){

    }
}
