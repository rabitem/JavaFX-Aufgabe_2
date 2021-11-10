package de.rabitem.evaluationtool.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValueCommentPair {
    @JsonProperty("value")
    private double value;
    @JsonProperty("comment")
    private String comment;

    public ValueCommentPair(double value, String comment) {
        this.value = Math.round(value);
        this.comment = comment;
    }

    public void setValue(double value) {
        this.value = Math.round(value);
    }
}
