package org.uabc.ic.proyectotamagochicongui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uabc.ic.proyectotamagochicongui.Modelo.Coleccion;
import org.uabc.ic.proyectotamagochicongui.Modelo.Perro;
import org.uabc.ic.proyectotamagochicongui.VistaConsola.VistaJavaFX;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Coleccion modelo = new Coleccion();
        VistaJavaFX vista = new VistaJavaFX(modelo);
        Scene scene = new Scene(vista.getRoot(), 640, 820);

        stage.setTitle("Proyecto Tamagochi");
        stage.setScene(scene);
        stage.show();
    }
}