package com.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.enums.Sex;
import com.example.model.Company;
import com.example.model.User;
import com.example.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        User user = new User();
        user.setFirstName("Ertugrul");
        user.setLastName("Sener");
        user.setSex(Sex.M);
        user.setCreationDate(LocalDate.now());
        user.setCreationTime(LocalTime.now());
        user.setTimezone(new Date());
        user.setCompany(new Company("a", "b", "c", "d"));
        userRepository.save(user);
    }
}
