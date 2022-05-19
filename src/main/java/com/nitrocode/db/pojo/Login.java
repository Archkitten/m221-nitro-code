package com.nitrocode.db.pojo;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
