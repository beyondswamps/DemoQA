package ru.nwork.demoqa.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserFull {
    public String userName;
    public String userId;
    public String password;
    public String token;
    public List<Book> books;
}
