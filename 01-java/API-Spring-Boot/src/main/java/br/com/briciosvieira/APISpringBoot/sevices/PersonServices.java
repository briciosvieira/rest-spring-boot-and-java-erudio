package br.com.briciosvieira.APISpringBoot.sevices;

import br.com.briciosvieira.APISpringBoot.exceptions.ResourcesNotFoundException;
import br.com.briciosvieira.APISpringBoot.mapper.DozerMapper;
import br.com.briciosvieira.APISpringBoot.model.Person;
import br.com.briciosvieira.APISpringBoot.repository.PersonRepository;
import br.com.briciosvieira.APISpringBoot.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

        @Autowired
        PersonRepository repository;

    public List<PersonVO> findAll(){
        return DozerMapper.parseListObjects(repository.findAll(),PersonVO.class);
    }


    public PersonVO findById(Long id){
        logger.info("Finding one person!");
         var entity = repository.findById(id)
                 .orElseThrow(()-> new ResourcesNotFoundException("Usuário não encontrado"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO){
        logger.info("create one person!");
        var entity = DozerMapper.parseObject(personVO, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }
    public PersonVO update(PersonVO personVO ){
        logger.info("update one person!");

        var entity =  repository.findById(personVO.getId()).orElseThrow(()-> new ResourcesNotFoundException("Usuário não atualizado"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;

    }

    public void delete(Long id){
        logger.info("Delete one person!");
        var entity =  repository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("Usuário não Deletado"));
        repository.delete(entity);
    }

}
