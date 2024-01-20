package com.project.geocode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    @JsonProperty("geometry")
    private GeoMatry geomatry;

    @JsonProperty("formatted_address")
    private String address;
}
