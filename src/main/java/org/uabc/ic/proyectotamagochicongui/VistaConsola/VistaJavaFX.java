package org.uabc.ic.proyectotamagochicongui.VistaConsola;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.uabc.ic.proyectotamagochicongui.Modelo.Coleccion;
import org.uabc.ic.proyectotamagochicongui.Modelo.Tamagochi;

import java.util.List;
import java.util.Random;

public class VistaJavaFX {

    private VBox root;
    private final Coleccion modelo;
    private Tamagochi actual;

    private ComboBox<String> selectorMascota;
    private ComboBox<Integer> cbComida, cbJuego;
    private Label lblCara, lblEstado, lblMensaje, lblMenuComida, lblMenuJuego, lblCaracteristicas;
    private ProgressBar pbEnergia, pbHambre, pbFelicidad, pbSalud;
    private Button btnSonido, btnSanar;

    public VistaJavaFX(Coleccion modelo) {
        this.modelo = modelo;
        inicializarComponentes();
        iniciarRelojTiempoReal();
    }

    private void inicializarComponentes() {
        root = new VBox(12);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Registro
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del nuevo Tamagotchi");

        ComboBox<String> cbTipo = new ComboBox<>();
        cbTipo.getItems().addAll("Perro", "Pez Dorado", "Aguila", "Dragon");
        cbTipo.setValue("Perro");

        Button btnCrear = new Button("Registrar");
        btnCrear.setOnAction(e -> {
            registrarTamagochi(txtNombre.getText(), cbTipo.getValue());
            txtNombre.clear();
        });

        HBox panelRegistro = new HBox(10, txtNombre, cbTipo, btnCrear);
        panelRegistro.setAlignment(Pos.CENTER);

        //  Selector
        selectorMascota = new ComboBox<>();
        //selectorMascota.getItems().add("Solovino");
        //selectorMascota.setValue("Solovino");
        selectorMascota.setOnAction(e -> seleccionarActual());

        //  Estado de animo
        lblCara = new Label("😄");
        lblCara.setStyle("-fx-font-size: 48px;");
        lblEstado = new Label("Estado de ánimo: Todo bien");

        //  Estadisticas
        pbEnergia = new ProgressBar(1.0);
        pbHambre = new ProgressBar(0.0);
        pbFelicidad = new ProgressBar(1.0);
        pbSalud = new ProgressBar(1.0);

        GridPane gridStats = new GridPane();
        gridStats.setHgap(10);
        gridStats.setVgap(8);
        gridStats.setAlignment(Pos.CENTER);
        gridStats.addRow(0, new Label("Energía:"), pbEnergia);
        gridStats.addRow(1, new Label("Hambre:"), pbHambre);
        gridStats.addRow(2, new Label("Felicidad:"), pbFelicidad);
        gridStats.addRow(3, new Label("Salud:"), pbSalud);

        //  Caracteristicas propias de cada especie
        lblCaracteristicas = new Label();
        lblCaracteristicas.setStyle("-fx-text-alignment: center; -fx-font-style: italic;");

        lblMensaje = new Label("Interfaz de tamagochis");

        //  Menus de comida y juego
        cbComida = new ComboBox<>();
        cbComida.getItems().addAll(1, 2, 3);
        cbComida.setValue(1);
        lblMenuComida = new Label();

        cbJuego = new ComboBox<>();
        cbJuego.getItems().addAll(1, 2, 3);
        cbJuego.setValue(1);
        lblMenuJuego = new Label();

        VBox cajaComida = new VBox(4, new Label("COMIDA"), lblMenuComida, cbComida);
        VBox cajaJuego = new VBox(4, new Label("JUEGO"), lblMenuJuego, cbJuego);
        cajaComida.setAlignment(Pos.CENTER);
        cajaJuego.setAlignment(Pos.CENTER);
        HBox panelMenus = new HBox(40, cajaComida, cajaJuego);
        panelMenus.setAlignment(Pos.CENTER);

        //  Botones
        Button btnComer = new Button("Comer");
        btnComer.setOnAction(e -> {
            if (puedeActuar()) {
                actual.comer(cbComida.getValue());
                mostrarMensaje(actual.getUltimoMensaje());
                actualizarPantalla();
            }
        });

        Button btnJugar = new Button("Jugar");
        btnJugar.setOnAction(e -> {
            if (puedeActuar()) {
                actual.jugar(cbJuego.getValue());
                mostrarMensaje(actual.getUltimoMensaje());
                actualizarPantalla();
            }
        });

        Button btnDormir = new Button("Dormir / Despertar");
        btnDormir.setOnAction(e -> {
            if (puedeActuar()) {
                if (actual.isDurmiendo()) {
                    actual.despertar();
                } else {
                    actual.dormir(8);
                }
                mostrarMensaje(actual.getUltimoMensaje());
                actualizarPantalla();
            }
        });

        btnSonido = new Button("Sonido");
        btnSonido.setOnAction(e -> {
            if (puedeActuar()) {
                actual.hacerSonido();
                String sonido = actual.getUltimoMensaje();
                if (actual.isDurmiendo()) {
                    mostrarMensaje(sonido);
                } else {
                    actual.comportamientoEspecifico();
                    mostrarMensaje(sonido + "\n" + actual.getUltimoMensaje());
                }
                actualizarPantalla();
            }
        });

        btnSanar = new Button("Sanar");
        btnSanar.setOnAction(e -> {
            if (puedeActuar()) {
                actual.sanar();
                mostrarMensaje(actual.getUltimoMensaje());
                actualizarPantalla();
            }
        });

        HBox panelBotones = new HBox(10, btnComer, btnJugar, btnDormir, btnSonido, btnSanar);
        panelBotones.setAlignment(Pos.CENTER);

        //  Botones de prueba
        Button btnMoribundo = new Button("Crear Solovino");
        btnMoribundo.setOnAction(e -> crearSolovino());

        Button btnPasarTiempo = new Button("Pasar tiempo");
        btnPasarTiempo.setOnAction(e -> {
            modelo.pasarTiempoATodos();
            actualizarPantalla();
            revisarMuertos();
        });

        HBox panelPrueba = new HBox(10, btnMoribundo, btnPasarTiempo);
        panelPrueba.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                new Label("REGISTRAR NUEVO TAMAGOTCHI"), panelRegistro,
                new Separator(),
                new Label("SELECCIONAR MASCOTA"), selectorMascota,
                lblCara, lblEstado,
                gridStats,
                new Label("CARACTERÍSTICAS"), lblCaracteristicas,
                panelMenus, panelBotones, lblMensaje,
                panelPrueba
        );

        seleccionarActual();
    }

    private boolean puedeActuar() {
        return actual != null && !actual.morir();
    }

    private void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    private void registrarTamagochi(String nombre, String tipo) {
        if (nombre == null || nombre.isBlank()) {
            mostrarMensaje("Escribe un nombre para el Tamagotchi.");
            return;
        }
        nombre = nombre.trim();
        if (modelo.buscarTamagochi(nombre) != null) {
            mostrarMensaje("Ya existe un Tamagotchi llamado " + nombre + ".");
            return;
        }
        Random rdm = new Random();
        switch (tipo) {
            case "Perro" -> modelo.agregarPerro(nombre, 100, 0, 100, 100, rdm.nextInt(101), rdm.nextInt(101));
            case "Pez Dorado" -> modelo.agregarPerDorado(nombre, 100, 0, 100, 100,
                    rdm.nextInt(101), rdm.nextInt(101), rdm.nextInt(101));
            case "Aguila" -> modelo.agregarAguila(nombre, 100, 0, 100, 100, rdm.nextInt(101), rdm.nextInt(101));
            case "Dragon" -> modelo.agregarDragon(nombre, 100, 0, 100, 100, 50, false, 0);
        }
        selectorMascota.getItems().add(nombre);
        selectorMascota.setValue(nombre);
        seleccionarActual();
        mostrarMensaje(nombre + " fue registrado.");
    }

    private void crearSolovino() {
        String nombre = "Solovino";
        if (modelo.buscarTamagochi(nombre) != null) {
            mostrarMensaje("Ya existe un Solovino");
            return;
        }
        // Mismos valores que la opcion 8 de la consola
        modelo.agregarPerro(nombre, 10, 90, 10, 5, 50, 50);
        selectorMascota.getItems().add(nombre);
        selectorMascota.setValue(nombre);
        seleccionarActual();
        mostrarMensaje("Se creó un perro Solovino con salud 5, hambre 90");
    }

    private void seleccionarActual() {
        String nombre = selectorMascota.getValue();
        if (nombre != null) {
            actual = modelo.buscarTamagochi(nombre);
            if (actual != null) {
                lblMenuComida.setText(actual.getMenuComida());
                lblMenuJuego.setText(actual.getMenuJuegos());
            }
            actualizarPantalla();
        }
    }

    private void actualizarPantalla() {
        if (actual == null) return;
        pbEnergia.setProgress(actual.getEnergia() / 100.0);
        pbHambre.setProgress(actual.getHambre() / 100.0);
        pbFelicidad.setProgress(actual.getFelicidad() / 100.0);
        pbSalud.setProgress(actual.getSalud() / 100.0);
        lblCaracteristicas.setText(actual.getCaracteristicas());

        // Dormido: Sonido y Sanar se apagan
        btnSonido.setDisable(actual.isDurmiendo());
        btnSanar.setDisable(actual.isDurmiendo());

        if (actual.morir()) {
            lblCara.setText("💀");
            lblEstado.setText("Estado de ánimo: TU MASCOTA HA MUERTO");
            lblEstado.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        } else {
            String animo = actual.estadoDeAnimo();
            lblEstado.setText("Estado de ánimo: " + animo);
            switch (animo) {
                case "Esta durmiendo" -> {
                    lblCara.setText("😴");
                    lblEstado.setStyle("-fx-text-fill: #4a6fa5; -fx-font-weight: bold;");
                }
                case "Esta mal" -> {
                    lblCara.setText("😢");
                    lblEstado.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
                }
                case "Necesita atencion" -> {
                    lblCara.setText("😐");
                    lblEstado.setStyle("-fx-text-fill: #d68910; -fx-font-weight: bold;");
                }
                default -> {
                    lblCara.setText("😄");
                    lblEstado.setStyle("-fx-text-fill: #1e8449; -fx-font-weight: bold;");
                }
            }
        }
    }

    // Avisa de las mascotas que murieron y las quita de la coleccion y del selector
    private void revisarMuertos() {
        List<String> muertos = modelo.eliminarMuertos();
        if (muertos.isEmpty()) return;

        for (String nombre : muertos) {
            selectorMascota.getItems().remove(nombre);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Tamagotchi");
            alerta.setHeaderText("💀 " + nombre + " ha muerto");
            alerta.setContentText(nombre + " fue eliminado de tu colección");
            alerta.show();   // show(), no showAndWait(): el reloj corre dentro de una animacion
            mostrarMensaje(nombre + " murió y fue eliminado");
        }

        if (selectorMascota.getItems().isEmpty()) {
            actual = null;
            selectorMascota.setValue(null);
            mostrarSinMascotas();
        } else {
            selectorMascota.setValue(selectorMascota.getItems().get(0));
            seleccionarActual();
        }
    }

    private void mostrarSinMascotas() {
        lblCara.setText("💀");
        lblEstado.setText("No quedan mascotas vivas");
        lblEstado.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        lblCaracteristicas.setText("");
        lblMenuComida.setText("");
        lblMenuJuego.setText("");
        pbEnergia.setProgress(0);
        pbHambre.setProgress(0);
        pbFelicidad.setProgress(0);
        pbSalud.setProgress(0);
    }

    private void iniciarRelojTiempoReal() {
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(120), e -> {
            modelo.pasarTiempoATodos();
            actualizarPantalla();
            revisarMuertos();
        }));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }

    public VBox getRoot() {
        return root;
    }
}