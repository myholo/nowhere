/*
package com.holo.web.oauth2;

import com.holo.domain.my.User;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

*/
/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/29 11:01
 * 4
 *//*

@Primary
@Service("userService")
public class UserServiceImpl implements UserService {
    private final static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "test-user1", "123451"));
        users.add(new User(2, "test-user2", "123452"));
        users.add(new User(3, "test-user3", "123453"));
        users.add(new User(4, "test-user4", "123454"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = users.stream()
                .filter((u) -> u.getUsername().equals(s))
                .findFirst();
        if (!user.isPresent())
            throw new UsernameNotFoundException("there's no user founded!");
        else
            return UserDetailConverter.convert(user.get());
    }

    private static class UserDetailConverter {
        static UserDetails convert(User user) {
            return new MyUserDetails(user);
        }
    }

}
*/
