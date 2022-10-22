module TDA367.Journal {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;

    exports src;
    exports src.MVC;

    opens src;
    opens src.MVC;

}