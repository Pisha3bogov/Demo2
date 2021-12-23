package ru.shytov.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.dao.Dao;
import ru.shytov.model.Product;
import ru.shytov.service.ProductDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane BorderPane;

    public ScrollPane ScrolPane;

    private final ObservableList<Product> products = FXCollections.observableArrayList();

    public VBox vBox;

    private MyListener myListener;

    AnchorPane anchorPane;

    @SneakyThrows
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initDate();

        ScrolPane.widthProperty().addListener((obj,oldValue,newValue) -> {
            vBox.setPrefHeight(newValue.doubleValue());
        });


        for(Product product : products){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
            anchorPane = loader.load();

            TileController tileController = loader.getController();
            tileController.setData(product);

            vBox.getChildren().add(anchorPane);
        }
    }

    private void initDate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Product,Integer> productDao = new ProductDaoImpl(factory);
        products.addAll(productDao.findAll());
    }
}
