package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.dto.ChangePasswordRequest;
import in.mecw.collegemanagementsystem.dto.LoginRequest;
import in.mecw.collegemanagementsystem.dto.UserResponse;
import in.mecw.collegemanagementsystem.entity.User;
import in.mecw.collegemanagementsystem.service.UserService;
import io.jsonwebtoken.security.Jwks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final  UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody User user){
        System.out.println("Register API called");
        return userService.createUser(user);
    }

   @PostMapping("/login")
   public ResponseEntity<String> loginAccess(@RequestBody LoginRequest loginRequest){
        Optional<String> result = userService.login(loginRequest);
        if(result.isPresent()) {
            Optional<String> token = userService.login(loginRequest);
            return ResponseEntity.ok(token.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Username or Password");
        }
   }

   @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
       Optional<User> result = userService.deleteUSerById(id);

       if(result.isPresent()){
           User userExist = result.get();
           userService.deleteUSerById(id);
           return ResponseEntity.ok(userExist);
       }
       else{
           return ResponseEntity.notFound().build();
       }
   }

    @PutMapping("/users/password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, Authentication authentication){
        if(request.getNewPassword() == null || request.getNewPassword().length() < 6){
            return ResponseEntity.badRequest().body("New password must have at least 6 characters");
        }
        boolean changed = userService.changePassword(authentication.getName(), request.getCurrentPassword(), request.getNewPassword());
        if(changed){
            return ResponseEntity.ok("Password changed");
        }
        return ResponseEntity.badRequest().body("Current password is wrong");
    }
}
