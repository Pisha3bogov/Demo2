package ru.shytov.controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.shytov.model.Product;

import java.awt.event.MouseEvent;

public class TileController {

    public Label nameView;

    public Label descriptionView;

    public ImageView imageView;

    private Product product;

    private MyListener myListener;

    public void click(MouseEvent mouseEvent){
        myListener.onClickListener(product);
    }

    public void setData(Product product) {
        this.product = product;

        Image image = new Image(String.valueOf(getClass().getResource("/images/" + product.getPath_main())));
        imageView.setImage(image);
        nameView.setText(SubString(product.getName()));
        descriptionView.setText(product.getDescription());
    }

    private String SubString (String name) {
        if (name.length() < 15) {
            return name;
        } else{
            return name.substring(0,15) + "...";
        }
    }

}
