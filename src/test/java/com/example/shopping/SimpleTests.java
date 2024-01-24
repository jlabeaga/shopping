package com.example.shopping;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

//@SpringBootTest
public class SimpleTests {

    @Test
    public void javaListContains() {

        @AllArgsConstructor
        @ToString
        class User {
            public int id;
            public String name;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return id == user.id;
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }
        }

        User user1 = new User(1, "one");
        User user2 = new User(2, "two");
        User user1same = new User(1, "one");
        List<User> users = List.of(user1, user2);
        System.out.println(users);
        if (users.contains(user1same)) {
            System.out.println("user1same YES");
        } else {
            System.out.println("user1same NO");
        }
        User user1different = new User(1, "different");
        if (users.contains(user1different)) {
            System.out.println("user1different YES");
        } else {
            System.out.println("user1different NO");
        }

    }
}
