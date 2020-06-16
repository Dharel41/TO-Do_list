package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller implements Serializable{

    public ListView listView1;
    public ListView listView2;
    public ListView listView3;
    public Stage a;
    public Scene b;
    private Controller2 controller;
    private FXMLLoader loader2;
    private Parent loaded;
    private ContextMenu contextMenu;


    public void close() {
        System.exit(0);
    }

    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacje o autorze");
        alert.setHeaderText("Marcin Oczkowicz\nInformatyka Stosowana gr.lab4 ");
        alert.showAndWait();
    }

    public void newTask() {
        a = new Stage();
        a.setTitle("Add new task");
        if (b == null) {
            b = new Scene(loaded, 350, 400);
        }
        a.setScene(b);
        a.show();
        ObservableList<String> priority = FXCollections.observableArrayList("LOW", "MEDIUM", "HIGH");
        controller.choiceBox.setItems(priority);
        controller.choiceBox.getSelectionModel().select(0);
    }

    public void handleMouseClick(MouseEvent mouseEvent) {
        if (contextMenu != null) {
            contextMenu.hide();
        }
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {

            Dane selectedItem = (Dane) listView1.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                contextMenu = new ContextMenu();

                MenuItem item1 = new MenuItem("Delete");
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        listView1.getItems().remove(selectedItem);
                    }
                });
                MenuItem item2 = new MenuItem("Edit");
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        controller.textField.setText(selectedItem.title);
                        controller.choiceBox.setValue(selectedItem.priority);
                        controller.dataPicker.setValue(selectedItem.date);
                        controller.textArea.setText(selectedItem.description);
                        a.show();
                        listView1.getItems().remove(selectedItem);
                    }
                });

                MenuItem item3 = new MenuItem("Move");
                item3.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        Dane dane=new Dane();
                        dane.title=selectedItem.title;
                        dane.priority=selectedItem.priority;
                        dane.date=selectedItem.date;
                        dane.description=selectedItem.description;
                        listView2.getItems().add(dane);
                        listView1.getItems().remove(selectedItem);

                    }
                });

                contextMenu.getItems().addAll(item1, item2,item3);
                contextMenu.show(listView1, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            }
        }
    }


    public void handleMouseClick2(MouseEvent mouseEvent) {
        if (contextMenu != null) {
            contextMenu.hide();
        }
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {

            Dane selectedItem = (Dane) listView2.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                contextMenu = new ContextMenu();

                MenuItem item1 = new MenuItem("Delete");
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        listView2.getItems().remove(selectedItem);
                    }
                });
                MenuItem item2 = new MenuItem("Edit");
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        controller.textField.setText(selectedItem.title);
                        controller.choiceBox.setValue(selectedItem.priority);
                        controller.dataPicker.setValue(selectedItem.date);
                        controller.textArea.setText(selectedItem.description);
                        a.show();
                        listView2.getItems().remove(selectedItem);
                    }
                });

                MenuItem item3 = new MenuItem("Move");
                item3.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        Dane dane=new Dane();
                        dane.title=selectedItem.title;
                        dane.priority=selectedItem.priority;
                        dane.date=selectedItem.date;
                        dane.description=selectedItem.description;
                        listView3.getItems().add(dane);
                        listView2.getItems().remove(selectedItem);

                    }
                });

                contextMenu.getItems().addAll(item1, item2,item3);
                contextMenu.show(listView2, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            }
        }
    }


    public void handleMouseClick3(MouseEvent mouseEvent) {
        if (contextMenu != null) {
            contextMenu.hide();
        }
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {

            Dane selectedItem = (Dane) listView3.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                contextMenu = new ContextMenu();

                MenuItem item1 = new MenuItem("Delete");
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        listView3.getItems().remove(selectedItem);
                    }
                });
                MenuItem item2 = new MenuItem("Edit");
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        controller.textField.setText(selectedItem.title);
                        controller.choiceBox.setValue(selectedItem.priority);
                        controller.dataPicker.setValue(selectedItem.date);
                        controller.textArea.setText(selectedItem.description);
                        a.show();
                        listView3.getItems().remove(selectedItem);
                    }
                });


                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(listView3, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            }
        }
    }
    public void save() throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(a);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
            out.writeObject(listView1.getItems().size());
            for(int i=0;i<listView1.getItems().size();i++) {
                Dane dane = (Dane) listView1.getItems().get(i);
                out.writeObject(dane.title);
                out.writeObject(dane.priority);
                out.writeObject(dane.date);
                out.writeObject(dane.description);
            }
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects2.txt"))) {
            out.writeObject(listView2.getItems().size());
            for(int i=0;i<listView2.getItems().size();i++) {
                Dane dane = (Dane) listView2.getItems().get(i);
                out.writeObject(dane.title);
                out.writeObject(dane.priority);
                out.writeObject(dane.date);
                out.writeObject(dane.description);
            }
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects3.txt"))) {
            out.writeObject(listView3.getItems().size());
            for(int i=0;i<listView3.getItems().size();i++) {
                Dane dane = (Dane) listView3.getItems().get(i);
                out.writeObject(dane.title);
                out.writeObject(dane.priority);
                out.writeObject(dane.date);
                out.writeObject(dane.description);
            }
        }

    }

    public void open()throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(a);



        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(selectedFile))) {
            String number;
            LocalDate da;
            Integer a;
            a=(Integer) inputStream.readObject();
            listView1.getItems().remove(0,listView1.getItems().size());
            for(int i=0;i<a;i++) {
                Dane dane = new Dane();
                number = (String) inputStream.readObject();
                dane.title = number;
                number = (String) inputStream.readObject();
                dane.priority = number;
                da = (LocalDate) inputStream.readObject();
                dane.date = da;
                number = (String) inputStream.readObject();
                dane.description = number;
                listView1.getItems().add(dane);
            }
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objects2.txt"))) {
            String number;
            LocalDate da;
            Integer a;
            a=(Integer)inputStream.readObject();
            listView2.getItems().remove(0,listView2.getItems().size());
            for(int i=0;i<a;i++) {
                Dane dane = new Dane();
                number = (String) inputStream.readObject();
                dane.title = number;
                number = (String) inputStream.readObject();
                dane.priority = number;
                da = (LocalDate) inputStream.readObject();
                dane.date = da;
                number = (String) inputStream.readObject();
                dane.description = number;
                listView2.getItems().add(dane);
            }
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objects3.txt"))) {
            String number;
            LocalDate da;
            Integer a;
            a=(Integer)inputStream.readObject();
            listView3.getItems().remove(0,listView3.getItems().size());
            for(int i=0;i<a;i++) {
                Dane dane = new Dane();
                number = (String) inputStream.readObject();
                dane.title = number;
                number = (String) inputStream.readObject();
                dane.priority = number;
                da = (LocalDate) inputStream.readObject();
                dane.date = da;
                number = (String) inputStream.readObject();
                dane.description = number;
                listView3.getItems().add(dane);
            }
        }




        listView1.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
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

        listView2.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
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
        listView3.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
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

    public void export()throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(a);

        String newLine = System.getProperty("line.separator");
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile))) {
            out.writeBytes(Integer.toString(listView1.getItems().size()));
            out.writeBytes(newLine);
            for(int i=0;i<listView1.getItems().size();i++) {
                Dane dane = (Dane) listView1.getItems().get(i);
                out.writeBytes(dane.title);
                out.writeBytes(",");
                String s=(String)dane.priority;
                out.writeBytes(s);
                out.writeBytes(",");
                out.writeBytes(dane.description);
                out.writeBytes(newLine);

            }
            out.writeBytes(Integer.toString(listView2.getItems().size()));
            out.writeBytes(newLine);
            for(int i=0;i<listView2.getItems().size();i++) {
                Dane dane = (Dane) listView2.getItems().get(i);
                out.writeBytes(dane.title);
                out.writeBytes(",");
                String s=(String)dane.priority;
                out.writeBytes(s);
                out.writeBytes(",");
                out.writeBytes(dane.description);
                out.writeBytes(newLine);
            }
            out.writeBytes(Integer.toString(listView3.getItems().size()));
            out.writeBytes(newLine);
            for(int i=0;i<listView3.getItems().size();i++) {
                Dane dane = (Dane) listView3.getItems().get(i);
                out.writeBytes(dane.title);
                out.writeBytes(",");
                String s=(String)dane.priority;
                out.writeBytes(s);
                out.writeBytes(",");
                out.writeBytes(dane.description);
                out.writeBytes(newLine);


            }
        }

    }

    public void importt()throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(a);


        try (DataInputStream in = new DataInputStream(new FileInputStream(selectedFile))) {
            listView1.getItems().remove(0,listView1.getItems().size());
            listView2.getItems().remove(0,listView2.getItems().size());
            listView3.getItems().remove(0,listView3.getItems().size());
            int si = Integer.valueOf(in.readLine());
            for (int i = 0; i < si; i++) {
                String[] c=in.readLine().split(",");
                Dane dane=new Dane();
                dane.title=c[0];
                dane.priority=c[1];
                dane.description=c[2];
                listView1.getItems().add(dane);
            }
            si = Integer.valueOf(in.readLine());
            for (int i = 0; i < si; i++){
                String[] c=in.readLine().split(",");
                Dane dane=new Dane();
                dane.title=c[0];
                dane.priority=c[1];
                dane.description=c[2];
                listView2.getItems().add(dane);
            }
            si = Integer.valueOf(in.readLine());
            for (int i = 0; i < si; i++){
                String[] c=in.readLine().split(",");
                Dane dane=new Dane();
                dane.title=c[0];
                dane.priority=c[1];
                dane.description=c[2];
                listView3.getItems().add(dane);
            }
        }
        listView1.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
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

        listView2.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
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
        listView3.setCellFactory(new Callback<ListView<Dane>, ListCell<Dane>>() {
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




    public Controller2 getController() {


        if (controller == null) {
            loader2 = new FXMLLoader(getClass().getResource("add.fxml"));
            try {
                loaded = loader2.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            controller = loader2.getController();
        }
        return controller;
    }










}
