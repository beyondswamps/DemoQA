package ru.nwork.demoqa.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenGenerated {
    private String token;
    private String expires;
    private String status;
    private String result;
}
