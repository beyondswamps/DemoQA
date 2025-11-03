package ru.nwork.reqres.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    public String id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;
}
