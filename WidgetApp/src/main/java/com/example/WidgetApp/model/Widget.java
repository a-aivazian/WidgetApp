package com.example.WidgetApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Widget {
    private Integer id;
    private Integer x;
    private Integer y;
    private Integer z;
    private Integer width;
    private Integer height;
    private LocalDateTime lastModificationDate;
}
