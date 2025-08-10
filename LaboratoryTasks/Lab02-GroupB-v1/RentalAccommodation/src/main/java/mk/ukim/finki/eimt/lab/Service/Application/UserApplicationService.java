package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;

public interface UserApplicationService {

    void register(CreateUserDTO createUserDTO);

    DisplayUserDTO login(String username, String password);
}