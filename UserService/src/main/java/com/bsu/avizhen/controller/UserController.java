package com.bsu.avizhen.controller;

import com.bsu.avizhen.dto.UserDto;
import com.bsu.avizhen.dto.XLSXNameDto;
import com.bsu.avizhen.entity.Project;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.entity.UserToFriend;
import com.bsu.avizhen.factory.DtoEntityTransformerFactory;
import com.bsu.avizhen.service.LoadDataFromWorkbookService;
import com.bsu.avizhen.service.UserService;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final LoadDataFromWorkbookService loadDataFromWorkbookService;
    private final DtoEntityTransformerFactory transformerFactory;


    @Autowired
    public UserController(UserService userService, LoadDataFromWorkbookService loadDataFromWorkbookService, DtoEntityTransformerFactory transformerFactory) {
        this.userService = userService;
        this.loadDataFromWorkbookService = loadDataFromWorkbookService;
        this.transformerFactory = transformerFactory;
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public Collection<UserDto> getFilteredUsers(
            @RequestParam("surname") Optional<String> surname,
            @RequestParam("name") Optional<String> name,
            @RequestParam("projectName") Optional<String> projectName,
            @RequestParam("position") Optional<String> position
    ) {
        Collection<User> filteredUsers = userService.filterUsers(userService.getAllUsers(), getPredicates(surname, name, projectName, position));

        return Collections2.transform(filteredUsers, new Function<User, UserDto>() {
            @Override
            public UserDto apply(User user) {
                return (UserDto) transformerFactory.getDtoEntityTransformer(User.class).transformEntityToDto(user);
            }
        });
    }

    private Iterable<Predicate<? super User>> getPredicates(Optional<String> surname, Optional<String> name, Optional<String> projectName, Optional<String> position) {
        Collection<Predicate<? super User>> result = new ArrayList<>();
        if (surname != null && surname.isPresent()) {
            result.add(new Predicate<User>() {
                @Override
                public boolean apply(User user) {
                    return surname.get().equals(user.getSurname());
                }
            });
        }
        if (name != null && name.isPresent()) {
            result.add(new Predicate<User>() {
                @Override
                public boolean apply(User user) {
                    return name.get().equals(user.getName());
                }
            });
        }
        if (projectName != null && projectName.isPresent()) {
            result.add(new Predicate<User>() {
                @Override
                public boolean apply(User user) {
                    if (projectName.get().equals(user.getCurrentProject().getName())) {
                        return true;
                    }
                    if (user.getPreviousProjects() != null) {
                        for (Project project : user.getPreviousProjects()) {
                            if (projectName.get().equals(project.getName())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (position != null && position.isPresent()) {
            result.add(new Predicate<User>() {
                @Override
                public boolean apply(User user) {
                    return user.getPosition().contains(position.get());
                }
            });
        }
        return result;
    }



    @RequestMapping(value = {"/users/{id}"}, method = RequestMethod.GET)
    public Object getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return transformerFactory.getDtoEntityTransformer(User.class).transformEntityToDto(user);
    }

    @RequestMapping(value = {"/users/colleagues/{id}"}, method = RequestMethod.GET)
    public Object getPossibleFriendsByUserId(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        List<UserDto> possibleFriendsDtos = new ArrayList<>();
        if (user != null) {
            Collection<User> possibleFriends = userService.getPossibleFriends(user);
            for (User possibleFriend : possibleFriends) {
                possibleFriendsDtos.add((UserDto) transformerFactory.getDtoEntityTransformer(User.class).transformEntityToDto(possibleFriend));
            }
        }
        return possibleFriendsDtos;
    }

    @RequestMapping(value = {"/users/friends/{id}"}, method = RequestMethod.GET)
    public Object getFriendsByUserId(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        List<UserDto> friendDtos = new ArrayList<>();
        if (user != null) {
            for (User friend : user.getFriends()) {
                friendDtos.add((UserDto) transformerFactory.getDtoEntityTransformer(User.class).transformEntityToDto(friend));
            }
        }
        return friendDtos;
    }

    @RequestMapping(value = {"/users/friends"}, method = RequestMethod.PUT)
    public void addUser(@RequestBody UserToFriend userToFriend) {
        User user = userService.getUserById(userToFriend.getUserId());
        User friend = userService.getUserById(userToFriend.getFriendId());
        if (user != null && friend != null && !user.getId().equals(friend.getId())) {
            if (!user.getFriends().contains(friend)) {
                user.getFriends().add(friend);
            }
            if (!friend.getFriends().contains(user)) {
                friend.getFriends().add(user);
            }
        }
    }



    @RequestMapping(value = {"/users/surname/{surname}"}, method = RequestMethod.GET)
    public Object getUserBySurname(@PathVariable("surname") String surname) {
        User user = userService.getUserBySurname(surname);
        return transformerFactory.getDtoEntityTransformer(User.class).transformEntityToDto(user);
    }

    @RequestMapping(value = {"/users/login/{login}"}, method = RequestMethod.GET)
    public Object getUserByLogin(@PathVariable("login") String login) {
        User user = userService.getUserByLogin(login);
        return transformerFactory.getDtoEntityTransformer(User.class).transformEntityToDto(user);
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @RequestMapping(value = {"/load"}, method = RequestMethod.POST)
    public Integer loadUsers(@RequestBody XLSXNameDto xlsxNameDto) throws IOException {
        String filePath = "C:\\Users\\alav0217\\Documents\\Studying\\PreDiplom\\Projects\\UserService\\src\\main\\resources\\" + xlsxNameDto.getFileName();
        Workbook usersWorkbook = new XSSFWorkbook(filePath);
        return loadDataFromWorkbookService.loadDataFromWorkbook(usersWorkbook);
    }
}
