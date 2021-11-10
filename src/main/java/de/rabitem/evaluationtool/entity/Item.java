package de.rabitem.evaluationtool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.control.MenuItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Item {
    private String name;
    private ValueCommentPair aufgabenangemessenheit;
    private ValueCommentPair selbstbeschreibungsfaehigkeit;
    private ValueCommentPair steuerbarkeit;
    private ValueCommentPair erwartungskonformitaet;
    private ValueCommentPair fehlertoleranz;
    private ValueCommentPair individualisierbarkeit;
    private ValueCommentPair lernfoerderlichkeit;

    @JsonIgnore
    private MenuItem menuItem;

    public Item(String name) {
        this.name = name;
        this.aufgabenangemessenheit = new ValueCommentPair(0 , "");
        this.selbstbeschreibungsfaehigkeit = new ValueCommentPair(0 , "");
        this.steuerbarkeit = new ValueCommentPair(0 , "");
        this.erwartungskonformitaet = new ValueCommentPair(0 , "");
        this.fehlertoleranz = new ValueCommentPair(0 , "");
        this.individualisierbarkeit = new ValueCommentPair(0 , "");
        this.lernfoerderlichkeit = new ValueCommentPair(0 , "");
    }

    @JsonIgnore
    public double getAverage() {
        return (this.aufgabenangemessenheit.getValue() + this.selbstbeschreibungsfaehigkeit.getValue() + this.steuerbarkeit.getValue() +
                this.erwartungskonformitaet.getValue() + this.fehlertoleranz.getValue() + this.individualisierbarkeit.getValue() + this.lernfoerderlichkeit.getValue())/7;
    }

    public MenuItem getMenuItem() {
        if (this.menuItem == null)
            this.menuItem = new MenuItem(this.getName());
        return menuItem;
    }
}
