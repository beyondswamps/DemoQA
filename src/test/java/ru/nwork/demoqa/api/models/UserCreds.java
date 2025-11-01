package ru.nwork.demoqa.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserForRegister {
    public String userName;
    public String password;
}
