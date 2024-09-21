package dev.raj.paymentapi.Repositories;

import dev.raj.paymentapi.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    public User save(User user);
    public User findByName(String name);
}
