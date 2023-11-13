package br.com.briciosvieira.APISpringBoot.sevices;

import br.com.briciosvieira.APISpringBoot.controllers.PersonController;
import br.com.briciosvieira.APISpringBoot.exceptions.RequiredObjectIsNullException;
import br.com.briciosvieira.APISpringBoot.exceptions.ResourcesNotFoundException;
import br.com.briciosvieira.APISpringBoot.mapper.DozerMapper;
import br.com.briciosvieira.APISpringBoot.model.Person;
import br.com.briciosvieira.APISpringBoot.repository.PersonRepository;
import br.com.briciosvieira.APISpringBoot.repository.UserRepository;
import br.com.briciosvieira.APISpringBoot.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserService.class.getName());

        @Autowired
        UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name "+username+" !");
        var user = repository.findByUserName(username);
        if (user != null) {
            return user;
        }else {
            throw new UsernameNotFoundException("Username "+username+" NotFound");
        }
    }
}
