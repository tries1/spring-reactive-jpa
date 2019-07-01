package com.example.reactive.springreactivejpa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
//@Setter(AccessLevel.PUBLIC)
@Builder
//@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
}
