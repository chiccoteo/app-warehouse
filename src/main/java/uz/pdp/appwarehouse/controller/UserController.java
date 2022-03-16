package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping
    public Result getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public Result editUserById(@PathVariable Integer id, UserDto userDto) {
        return userService.editUserById(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }
}
