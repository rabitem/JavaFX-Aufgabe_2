package de.rabitem.evaluationtool.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @JsonProperty("author")
    private String author;
    @JsonProperty("items")
    private List<Item> items;
}
