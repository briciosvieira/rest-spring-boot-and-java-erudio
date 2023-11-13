package br.com.briciosvieira.APISpringBoot.repository;


import br.com.briciosvieira.APISpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    @Query("SELECT uxx FROM User WHERE uxx.userName =:userName")
    User findByUserName(@Param("userName") String userName );

}
