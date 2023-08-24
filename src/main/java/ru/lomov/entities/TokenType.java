package ru.lomov.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenType {
    private String name;
    private String regularExpression;
}
