package com.project.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
public class Response {
    @JsonProperty("coord")
    private Coordinate coordinate;

    private Weather[] weather;

    private Main main;
}
