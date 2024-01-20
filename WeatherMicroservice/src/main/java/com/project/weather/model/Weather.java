package com.project.weather.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    private long id;
    private String description;
    private String main;

}
