package com.example.catchclone.user.repository;

import com.example.catchclone.user.entity.User;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User,Long> {

  void save(User user);

  Optional<User> findByUsername(String username);
  boolean existsByUsername(String username);
}
