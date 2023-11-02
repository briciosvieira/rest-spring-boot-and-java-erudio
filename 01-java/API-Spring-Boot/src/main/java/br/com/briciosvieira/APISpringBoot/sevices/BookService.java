package br.com.briciosvieira.APISpringBoot.sevices;

import br.com.briciosvieira.APISpringBoot.controllers.BookController;
import br.com.briciosvieira.APISpringBoot.exceptions.ResourcesNotFoundException;
import br.com.briciosvieira.APISpringBoot.mapper.DozerMapper;
import br.com.briciosvieira.APISpringBoot.model.Book;
import br.com.briciosvieira.APISpringBoot.repository.BookRepository;
import br.com.briciosvieira.APISpringBoot.vo.v1.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookService {
    @Autowired
    private BookRepository repository;


    @GetMapping
    public List<BookVO> getAll() {
        var books = DozerMapper.parseListObjects(repository.findAll(),BookVO.class);
        books.stream().forEach(b->{
           try {
               b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel());
           } catch (Exception ex) {
               throw new RuntimeException(ex);
           }
        });
        return books;
    }

    public BookVO findById(Long id) throws Exception {
        var entity = repository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("Livro não encontrado"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;

    }

    public BookVO save(BookVO bookVO) {
        var entity = DozerMapper.parseObject(bookVO, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity),BookVO.class);
        return vo;
    }

    public BookVO update(BookVO bookVO) {
        var entity = repository.findById(bookVO.getId()).orElseThrow(()->new ResourcesNotFoundException("Livro não encontrado"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setDate(bookVO.getDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        var entity = repository.findById(id).orElseThrow(()-> new ResourcesNotFoundException("Livro não encontrado"));
        repository.delete(entity);
    }
}
