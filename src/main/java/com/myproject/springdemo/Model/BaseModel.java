package com.myproject.springdemo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
@Getter
@Setter


@Entity
@MappedSuperclass
public abstract class BaseModel {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private State state;

}
