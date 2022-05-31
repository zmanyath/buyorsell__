package com.wethinkcode.buyorsell.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.wethinkcode.buyorsell.dto.loginDto;
import com.wethinkcode.buyorsell.exceptions.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Transactional
public class UserService {

    private UserRepo userRepository;
    @Autowired
    public UserService(UserRepo userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User registerUser(User user) throws UserException {

        if(!getUserByEmail(user.getEmail())){
            throw new UserException("User already exists for this email");
        }
        
        User newUser = new User(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getPassword(),
                        user.getCreatedAt(),
                        user.getModified_at()
                );
        return userRepository.save(newUser);
    }

    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    public boolean getUserByEmail(String email) throws UserException {

        return userRepository.findByEmail(email) !=null ? true : false;
    }

    public loginDto loginUser(loginDto login) {

        Object foundDuplicate = userRepository.findByEmail(login.getEmail());
        if(foundDuplicate != login.getEmail() && foundDuplicate != login.getPassword()) {
            return null;
        }
        return login;
        
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
		for(User user : userRepository.findAll()) {
			users.add(user);
		}
		return users;
    }		


    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Object find(String email) {
        return userRepository.findByEmail(email);
    }


}