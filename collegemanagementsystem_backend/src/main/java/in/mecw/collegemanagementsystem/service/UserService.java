package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.Security.JwtService;
import in.mecw.collegemanagementsystem.dto.LoginRequest;
import in.mecw.collegemanagementsystem.dto.UserResponse;
import in.mecw.collegemanagementsystem.entity.User;
import in.mecw.collegemanagementsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder , JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //find user by user name
    public Optional<User> getUserByName(String userName) {
       return userRepository.findByUserName(userName);
    }

    public UserResponse createUser(User user){
        String hash = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(hash);
        User savedUSer = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(savedUSer.getUserId());
        userResponse.setUserName(savedUSer.getUserName());
        userResponse.setUserRole(savedUSer.getUserRole());
        return userResponse;
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponse> result = new ArrayList<>();
        for(User user : users){
            UserResponse response = new UserResponse();
            response.setUserId(user.getUserId());
            response.setUserName(user.getUserName());
            response.setUserRole(user.getUserRole());
            result.add(response);
        }
        return result;
    }

    public Optional<String > login(LoginRequest loginRequest){
        Optional<User> result = getUserByName(loginRequest.getUserName());

        if(result.isPresent()) {
            User user = result.get();
            if (passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword())) {
                String token = jwtService.generateToken(user.getUserName(), user.getUserRole().toString());
                return Optional.of(token);
            }
            else {
                return Optional.empty();
            }
            }
        else {
                return Optional.empty();
        }
    }

    public Optional<User> deleteUSerById(int id){
        Optional<User> result = userRepository.findById(id);

        if(result.isPresent()){
            User userExist = result.get();
            userRepository.deleteById(id);
            return Optional.of(userExist);
        }
        else{
            return Optional.empty();
        }
    }

    public boolean changePassword(String userName, String currentPassword, String newPassword){
        Optional<User> result = userRepository.findByUserName(userName);
        if(result.isEmpty()){ return false; }
        User user = result.get();
        if(!passwordEncoder.matches(currentPassword, user.getUserPassword())){ return false; }
        user.setUserPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

}
