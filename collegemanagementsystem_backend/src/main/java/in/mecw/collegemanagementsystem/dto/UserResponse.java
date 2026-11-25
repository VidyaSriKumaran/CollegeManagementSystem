package in.mecw.collegemanagementsystem.dto;

import in.mecw.collegemanagementsystem.entity.Role;
import tools.jackson.core.util.Named;

public class UserResponse {
    private Integer userId;
    private String  userName;
    private Role userRole;

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public Role getUserRole(){
        return userRole;
    }
    public void setUserRole(Role userRole){
        this.userRole = userRole;
    }
}
