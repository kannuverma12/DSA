//package com.kv.j8.jfx;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//public class TextField_2 extends Application {
//
//    public void start(Stage s) {
//        s.setTitle("creating textfield");
//        TextField b = new TextField("initial text");
//        b.setPrefColumnCount(7);
//
//        TextField c = new TextField("initial text");
//        TilePane r = new TilePane();
//        Label l = new Label("no text");
//
//        EventHandler<ActionEvent> getDataWaitOrExpand = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                if (b.getText().equalsIgnoreCase("getData")) {
//                    l.setText(b.getText());
//                } else if (b.getText().equalsIgnoreCase("wait")) {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                    l.setText(b.getText());
//                } else if (b.getText().equalsIgnoreCase("expand")) {
//                    r.getChildren().add(c);
//                }
//            }
//        };
//        // when enter is pressed
//        b.setOnAction(getDataWaitOrExpand);
//        // add textfield
//        r.getChildren().add(b);
//        r.getChildren().add(l);
//
//        Scene sc = new Scene(r, 200, 200);
//        s.setScene(sc);
//
//        s.show();
//    }
//
//    public static void main(String args[]) {
//
//
//        //launch(args);
//    }
//}
