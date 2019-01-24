package com.bsu.avizhen.transformer.impl;

import com.bsu.avizhen.dto.UserDto;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.transformer.DtoEntityTransformer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDtoEntityTransformer implements DtoEntityTransformer<User, UserDto> {

    @Override
    public User transformDtoToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new User(userDto.getId(), userDto.getName(), userDto.getPosition());
    }

    @Override
    public UserDto transformEntityToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto resDto = generateUserDtoWithoutRecursiveFields(user);
        resDto.setFriends(generateUserDtoListForFriends(user.getFriends()));
        return resDto;
    }

    private UserDto generateUserDtoWithoutRecursiveFields(User user) {
        UserDto resDto = new UserDto();
        resDto.setId(user.getId());
        resDto.setName(user.getName());
        resDto.setSurname(user.getSurname());
        resDto.setLogin(user.getLogin());
        resDto.setEmail(user.getEmail());
        String workStartDate = "";
        if (user.getWorkStartDate() != null) {
            workStartDate = new SimpleDateFormat("yyyy-MM-dd").format(user.getWorkStartDate());
        }
        resDto.setWorkStartDate(workStartDate);
        resDto.setPosition(user.getPosition());
        return resDto;
    }

    private List<UserDto> generateUserDtoListForFriends(Collection<User> friends) {
        List<UserDto> friendsDtoList = new ArrayList<>();
        for (User friend : friends) {
            friendsDtoList.add(generateUserDtoWithoutRecursiveFields(friend));
        }
        return friendsDtoList;
    }

    @Override
    public Class getEntityClass() {
        return User.class;
    }

}
