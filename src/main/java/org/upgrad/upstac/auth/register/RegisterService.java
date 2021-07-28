package org.upgrad.upstac.auth.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.models.AccountStatus;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.UserService;
import org.upgrad.upstac.users.roles.UserRole;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class RegisterService {

    @Autowired
    private UserService userService;


    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);


    public User addUser(RegisterRequest registerRequest) {
        String registerRequestUserName = registerRequest.getUserName();
        String registerRequestEmail = registerRequest.getEmail();
        String registerRequestPhoneNumber = registerRequest.getPhoneNumber();
        if ((userService.findByUserName(registerRequestUserName) != null)
                || (userService.findByEmail(registerRequestEmail) != null)
                        || (userService.findByPhoneNumber(registerRequestPhoneNumber) != null)) {
            log.info("Duplicate user details");
            throw  new AppException("Username , email and phone number should be unique ");
        }
        User user = new User();
        user.setUserName(registerRequestUserName);
        user.setEmail(registerRequestEmail);
        user.setPhoneNumber(registerRequestPhoneNumber);
        user.setAddress(registerRequest.getAddress());
        LocalDate date = LocalDate.parse(registerRequest.getDateOfBirth());
        user.setDateOfBirth(date);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
        user.setPinCode(registerRequest.getPinCode());
        user.setCreated(LocalDateTime.now());
        user.setGender(registerRequest.getGender());
        user.setStatus(AccountStatus.APPROVED);

        return userService.saveInDatabase(user);

    }

    public User addDoctor(RegisterRequest registerRequest) {
        String registerRequestUserName = registerRequest.getUserName();
        String registerRequestEmail = registerRequest.getEmail();
        String registerRequestPhoneNumber = registerRequest.getPhoneNumber();
        if ((userService.findByUserName(registerRequestUserName) != null)
                || (userService.findByEmail(registerRequestEmail) != null)
                || (userService.findByPhoneNumber(registerRequestPhoneNumber) != null)) {
            log.info("Duplicate user details");
            throw  new AppException("Username , email and phone number should be unique ");
        }
        User user = new User();
        user.setUserName(registerRequestUserName);
        user.setEmail(registerRequestEmail);
        user.setRoles(userService.getRoleFor(UserRole.DOCTOR));
        user.setPhoneNumber(registerRequestPhoneNumber);
        user.setAddress(registerRequest.getAddress());
        LocalDate date = LocalDate.parse(registerRequest.getDateOfBirth());
        user.setDateOfBirth(date);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
        user.setPinCode(registerRequest.getPinCode());
        user.setCreated(LocalDateTime.now());
        user.setGender(registerRequest.getGender());
        user.setStatus(AccountStatus.INITIATED);

        return userService.saveInDatabase(user);


    }


    public User addTester(RegisterRequest registerRequest) {
        String registerRequestUserName = registerRequest.getUserName();
        String registerRequestEmail = registerRequest.getEmail();
        String registerRequestPhoneNumber = registerRequest.getPhoneNumber();
        if ((userService.findByUserName(registerRequestUserName) != null)
                || (userService.findByEmail(registerRequestEmail) != null)
                || (userService.findByPhoneNumber(registerRequestPhoneNumber) != null)) {
            log.info("Duplicate user details");
            throw  new AppException("Username , email and phone number should be unique ");
        }
        User user = new User();
        user.setUserName(registerRequestUserName);
        user.setEmail(registerRequestEmail);
        user.setPhoneNumber(registerRequestPhoneNumber);
        user.setAddress(registerRequest.getAddress());

        user.setCreated(LocalDateTime.now());
        user.setRoles(userService.getRoleFor(UserRole.TESTER));
        LocalDate date = LocalDate.parse(registerRequest.getDateOfBirth());
        user.setDateOfBirth(date);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
        user.setPinCode(registerRequest.getPinCode());
        user.setGender(registerRequest.getGender());
        user.setStatus(AccountStatus.INITIATED);

        return userService.saveInDatabase(user);

    }


}
