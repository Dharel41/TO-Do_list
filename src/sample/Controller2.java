package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;

public class Controller2 {
    public ChoiceBox choiceBox;
    public TextField textField;
    public DatePicker dataPicker;
    public TextArea textArea;
    private Controller controller;


    public void addToList(){
        Dane dane=new Dane();
        dane.title=textField.getText();
        dane.priority=choiceBox.getValue();
        dane.date=dataPicker.getValue();
        dane.description=textArea.getText();
        controller.listView1.getItems().add(dane);



        controller.listView1.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
            @Override
            public ListCell<Dane> call(ListView<Dane> param) {
                return new ListCell<Dane>() {
                    @Override
                    protected void updateItem(Dane item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + "derive(-fx-base,80%)" + ";");
                        } else {
                            setText(item.title);
                            if (item.priority.equals("LOW")) {
                                setStyle("-fx-background-color: #7FFF00;");
                            }
                            if (item.priority .equals("MEDIUM")) {
                                setStyle("-fx-background-color: #FFD700;");
                            }
                            if (item.priority .equals("HIGH")) {
                                setStyle("-fx-background-color: #FF0000;");
                            }
                            Tooltip tooltip = new Tooltip();
                            tooltip.setText(item.description);
                            setTooltip(tooltip);
                        }
                    }
                };
            }
        });

        controller.listView2.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
            @Override
            public ListCell<Dane> call(ListView<Dane> param) {
                return new ListCell<Dane>() {
                    @Override
                    protected void updateItem(Dane item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + "derive(-fx-base,80%)" + ";");
                        } else {
                            setText(item.title);
                            if (item.priority.equals("LOW")) {
                                setStyle("-fx-background-color: #7FFF00;");
                            }
                            if (item.priority .equals("MEDIUM")) {
                                setStyle("-fx-background-color: #FFD700;");
                            }
                            if (item.priority .equals("HIGH")) {
                                setStyle("-fx-background-color: #FF0000;");
                            }
                            Tooltip tooltip = new Tooltip();
                            tooltip.setText(item.description);
                            setTooltip(tooltip);


                        }
                    }
                };
            }
        });
        controller.listView3.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
            @Override
            public ListCell<Dane> call(ListView<Dane> param) {
                return new ListCell<Dane>() {
                    @Override
                    protected void updateItem(Dane item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + "derive(-fx-base,80%)" + ";");
                        } else {
                            setText(item.title);
                            if (item.priority .equals("LOW")) {
                                setStyle("-fx-background-color: #7FFF00;");
                            }
                            if (item.priority .equals("MEDIUM")) {
                                setStyle("-fx-background-color: #FFD700;");
                            }
                            if (item.priority .equals("HIGH")) {
                                setStyle("-fx-background-color: #FF0000;");
                            }
                            Tooltip tooltip = new Tooltip();
                            tooltip.setText(item.description);
                            setTooltip(tooltip);


                        }
                    }
                };
            }
        });



    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
