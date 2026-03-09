package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public class Stringg {
    @NotBlank(message = "Your input enter is invalid!!")
    String name;

    public Stringg(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
