package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repo.UserRepo;
import uz.pdp.appwarehouse.repo.WarehouseRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    WarehouseRepo warehouseRepo;


    public Result addUser(UserDto userDto) {
        Result checking = checking(userDto);
        if (checking.isSuccess()) {
            User user = new User();
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setPassword(userDto.getPassword());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setWarehouses((Set<Warehouse>) checking.getObject());
            user.setCode(getSpecialCode());
            userRepo.save(user);
            return new Result("Successfully saved", true);
        }
        return checking;
    }

    private String getSpecialCode() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty())
            return "1";
        return String.valueOf(Integer.parseInt(users.get(users.size() - 1).getCode()) + 1);
    }

    private Result checking(UserDto userDto) {
        boolean haveAll = false;
        Set<Warehouse> warehouses = new HashSet<>();
        for (Integer warehouseId : userDto.getWarehouseIds()) {
            if (warehouseRepo.existsById(warehouseId)) {
                warehouses.add(warehouseRepo.findById(warehouseId).orElse(null));
                haveAll = true;
            } else {
                haveAll = false;
                break;
            }
        }
        if (!haveAll)
            return new Result("Some warehouses do not exist", false);
        boolean existsByPhoneNumber = userRepo.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Such a phone number with user already exists", false);
        return new Result("Success", true, warehouses);
    }

    public Result getUsers() {
        return new Result("Success", true, userRepo.findAll());
    }

    public Result getUserById(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty())
            return new Result("Such a user doesn't exist", false);
        return new Result("Success", true, optionalUser.get());
    }

    public Result editUserById(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty())
            return new Result("Such a user doesn't exist", false);
        Result checking = checking(userDto);
        if (checking.isSuccess()) {
            User user = optionalUser.get();
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setPassword(userDto.getPassword());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setWarehouses((Set<Warehouse>) checking.getObject());
//            user.setCode(getSpecialCode()); User code isn't changed when user is edited
            userRepo.save(user);
            return new Result("Successfully edited", true);
        }
        return checking;
    }

    public Result deleteUserById(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty())
            return new Result("Such a user doesn't exist", false);
        userRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
