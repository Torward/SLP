package ru.lomov.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Token {
    private TokenTypeList type;
    private String text;

    @Override
    public String toString() {
        return type +
                " " + text;
    }
}
