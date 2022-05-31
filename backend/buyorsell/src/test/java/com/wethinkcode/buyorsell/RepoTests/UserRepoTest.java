package com.wethinkcode.buyorsell.RepoTests;

// import static org.assertj.core.api.Assertions.asse;

import java.sql.Date;

import com.wethinkcode.buyorsell.user.User;
import com.wethinkcode.buyorsell.user.UserRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepoTest {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepo repo;

    Date date = new Date(0); 
     
    @Test
    public void testCreateUser() {

        User user = new User();
        user.setId(1);
        user.setEmail("ravikumar@gmail.com");
        user.setPassword("ravi2020");
        user.setFirstName("Ravi");
        user.setLastName("Kumar");
        user.setPhoneNumber(076);
        user.setCreatedAt(date);
        user.setModified_at(date);

        
        User savedUser = repo.save(user);
         
        // User existUser = entityManager.find(User.class, savedUser.getId());
         
        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail()); 
         
    }
}
