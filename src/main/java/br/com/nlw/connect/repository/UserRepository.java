package br.com.nlw.connect.repository;

import br.com.nlw.connect.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);

}
