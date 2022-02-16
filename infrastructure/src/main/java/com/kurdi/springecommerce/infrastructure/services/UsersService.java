package com.kurdi.springecommerce.infrastructure.services;

import com.kurdi.security.Permissions;
import com.kurdi.security.Roles;
import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.Authority;
import com.kurdi.springecommerce.infrastructure.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsersRepository userRepository;

    public AppUser register() {
        List<Authority> authorities = new ArrayList<>();
        AppUser user = AppUser.builder()
                .username("sheriff")
                .password(passwordEncoder.encode("123"))
                .authorities(authorities)
                .build();

        authorities.add(Authority
                .builder()
                .appUser(user)
                .name(Roles.ADMIN.getRole())
                .build());

        authorities.add(Authority
                .builder()
                .appUser(user)
                .name(Permissions.EmployeeRead.getPermission())
                .build());

        if (userRepository.findUserByUsername(user.getUsername()).isEmpty()) {
            userRepository.save(user);
            //TODO when creating user make an app event to create his cart instead of making it here.
            user.setCart(new Cart(user));
            userRepository.save(user);

        }

        return user;
    }
}
