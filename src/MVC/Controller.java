package src.MVC;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import src.Data.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class Controller {
    private Model model;

    public Controller() {
        this.startUp();
    }

    private void startUp(){
        this.model = new Model();
    }

    //matching shutdown-method goes here
    private void shutdown(){
        model.savePosts();
    }

    public Model getModel() {
        return model;
    }
}
