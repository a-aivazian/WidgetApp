package com.example.WidgetApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestBody {

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;

    private Integer z;

    @NotNull
    private Integer width;

    @NotNull
    private Integer height;
}
