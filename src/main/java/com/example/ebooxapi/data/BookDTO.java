package com.example.ebooxapi.data;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDTO {

    @NotNull(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Author cannot be empty")
    private String author;

    @NotNull(message = "Genre cannot be empty")
    private String genre;
}