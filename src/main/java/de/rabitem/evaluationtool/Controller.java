package de.rabitem.evaluationtool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import de.rabitem.evaluationtool.entity.Item;
import de.rabitem.evaluationtool.entity.Items;
import de.rabitem.evaluationtool.entity.ValueCommentPair;
import de.rabitem.evaluationtool.entity.YamlConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Getter
public class Controller {

    @FXML
    public MenuItem quit;
    @FXML
    public AnchorPane anchorPaneContent;

    @FXML
    public Label average;

    @FXML
    private Slider sliderAufgabenangemessenheit;
    @FXML
    private TextField textboxAufgabenangemessenheit;
    @FXML
    private Label labelAufgabenangemessenheit;

    @FXML
    private Slider sliderSelbstbeschreibungsfaehigkeit;
    @FXML
    private TextField textboxSelbstbeschreibungsfaehigkeit;
    @FXML
    private Label labelSelbstbeschreibungsfaehigkeit;

    @FXML
    private Slider sliderSteuerbarkeit;
    @FXML
    private TextField textboxSteuerbarkeit;
    @FXML
    private Label labelSteuerbarkeit;

    @FXML
    private Slider sliderErwartungskonformitaet;
    @FXML
    private TextField textboxErwartungskonformitaet;
    @FXML
    private Label labelErwartungskonformitaet;

    @FXML
    private Slider sliderFehlertoleranz;
    @FXML
    private TextField textboxFehlertoleranz;
    @FXML
    private Label labelFehlertoleranz;

    @FXML
    private Slider sliderIndividualisierbarkeit;
    @FXML
    private TextField textboxIndividualisierbarkeit;
    @FXML
    private Label labelIndividualisierbarkeit;

    @FXML
    private Slider sliderLernfoerderlichkeit;
    @FXML
    private TextField textboxLernfoerderlichkeit;
    @FXML
    private Label labelLernfoerderlichkeit;

    @FXML
    private Menu menubarApplications;

    @FXML
    private Label labelApplicationName;

    @Setter
    private Stage stage;

    private Item currentItem;

    public void initialize() {
        this.sliderSteuerbarkeit.valueProperty().addListener((observable, oldValue, newValue) -> {
                currentItem.setSteuerbarkeit((new ValueCommentPair((Double) newValue, this.getTextboxSteuerbarkeit().getText())));
                updateAverage(currentItem);
        });
        this.sliderLernfoerderlichkeit.valueProperty().addListener((observable, oldValue, newValue) -> {
                currentItem.setLernfoerderlichkeit(new ValueCommentPair((Double) newValue, this.getTextboxLernfoerderlichkeit().getText()));
                updateAverage(currentItem);
        });
        this.sliderIndividualisierbarkeit.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentItem.setIndividualisierbarkeit(new ValueCommentPair((Double) newValue, this.getTextboxIndividualisierbarkeit().getText()));
            updateAverage(currentItem);
        });
        this.sliderFehlertoleranz.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentItem.setFehlertoleranz(new ValueCommentPair((Double) newValue, this.getTextboxFehlertoleranz().getText()));
            updateAverage(currentItem);
        });
        this.sliderErwartungskonformitaet.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentItem.setErwartungskonformitaet(new ValueCommentPair((Double) newValue, this.getTextboxErwartungskonformitaet().getText()));
            updateAverage(currentItem);
        });
        this.sliderAufgabenangemessenheit.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentItem.setAufgabenangemessenheit(new ValueCommentPair((Double) newValue, this.getTextboxAufgabenangemessenheit().getText()));
            updateAverage(currentItem);
        });
        this.sliderSelbstbeschreibungsfaehigkeit.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentItem.setSelbstbeschreibungsfaehigkeit(new ValueCommentPair((Double) newValue, this.getTextboxSelbstbeschreibungsfaehigkeit().getText()));
            updateAverage(currentItem);
        });

        this.setupSample();
    }

    @FXML
    private void onSaveConfig(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialFileName("config");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Yaml", "*.yml"));

        File selectedFile = fileChooser.showSaveDialog(this.stage);
        if (selectedFile != null) {
            var mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
            try {
                var serialize = new Items();
                serialize.setAuthor("system");
                serialize.setItems(items);
                mapper.writeValue(new File(selectedFile.getAbsolutePath()), serialize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private final ArrayList<Item> items = new ArrayList<>();

    @FXML
    private void onLoadConfig(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Config");
        fileChooser.setInitialFileName("config");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("YAML", "*.yml", "*.yaml"));

        File selectedFile = fileChooser.showOpenDialog(this.stage);
        items.addAll(new YamlConfig<>(selectedFile.getAbsolutePath(), Items.class).getConfig().getItems());
        if (!items.isEmpty()) {
            for (Item item : items) {
                setupMenuItem(item.getMenuItem());
            }
            this.setApplication(items.get(0));
        }
    }

    private void setApplication(Item item) {
        this.currentItem = item;

        this.labelApplicationName.setText(item.getName());
        this.sliderAufgabenangemessenheit.setValue(item.getAufgabenangemessenheit().getValue());
        this.sliderErwartungskonformitaet.setValue(item.getErwartungskonformitaet().getValue());
        this.sliderFehlertoleranz.setValue(item.getFehlertoleranz().getValue());
        this.sliderIndividualisierbarkeit.setValue(item.getIndividualisierbarkeit().getValue());
        this.sliderLernfoerderlichkeit.setValue(item.getLernfoerderlichkeit().getValue());
        this.sliderSelbstbeschreibungsfaehigkeit.setValue(item.getSelbstbeschreibungsfaehigkeit().getValue());
        this.sliderSteuerbarkeit.setValue(item.getSteuerbarkeit().getValue());

        this.textboxAufgabenangemessenheit.setText(item.getAufgabenangemessenheit().getComment());
        this.textboxErwartungskonformitaet.setText(item.getErwartungskonformitaet().getComment());
        this.textboxFehlertoleranz.setText(item.getFehlertoleranz().getComment());
        this.textboxIndividualisierbarkeit.setText(item.getIndividualisierbarkeit().getComment());
        this.textboxLernfoerderlichkeit.setText(item.getLernfoerderlichkeit().getComment());
        this.textboxSelbstbeschreibungsfaehigkeit.setText(item.getSelbstbeschreibungsfaehigkeit().getComment());
        this.textboxSteuerbarkeit.setText(item.getSteuerbarkeit().getComment());

        updateAverage(item);
    }

    private void updateAverage(Item item) {
        this.average.setText("Average: " + Math.round(item.getAverage()));
    }

    @FXML
    private void onQuit() {
        System.exit(0);
    }

    public void onNewApplication() {
        var td = new TextInputDialog();
        td.setTitle("Application Name");
        td.showAndWait();

        var item = new Item(td.getEditor().getText());
        items.add(item);
        this.setApplication(item);

        setupMenuItem(item.getMenuItem());
    }

    private void setupMenuItem(MenuItem menuItem) {
        if (!this.menubarApplications.getItems().contains(menuItem))
            this.menubarApplications.getItems().add(menuItem);

        menuItem.setOnAction(event -> {
            var source = (MenuItem) event.getSource();
            this.setApplication(Objects.requireNonNull(this.getByString(source.getText())));
        });
    }

    public void onDelete() {
        this.items.remove(currentItem);
        this.menubarApplications.getItems().remove(currentItem.getMenuItem());

        if (this.items.isEmpty())
            this.setupSample();
        else
            this.setApplication(items.get(0));
    }

    private Item getByString(String name) {
        for (Item item : items) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

    private void setupSample() {
        var sample = new Item("sample");
        this.items.add(sample);
        this.setApplication(sample);
        this.setupMenuItem(sample.getMenuItem());
    }
}
