package org.uabc.ic.proyectotamagochicongui.Modelo;

public class PezDorado extends Tamagochi {
    private int calidadAgua;
    private int controlTemperatura;
    private int oxigenacion;

    public PezDorado(String nombre, int energia, int hambre, int felicidad, int salud,
                     int agua, int controlTemperatura, int oxigenacion) {
        super(nombre, energia, hambre, felicidad, salud);
        this.calidadAgua = agua;
        this.controlTemperatura = controlTemperatura;
        this.oxigenacion = oxigenacion;
    }

    public void salirNadar() {
        decir(nombre + " nada alrededor de la pecera.");
    }

    @Override
    public void comer(int alimento) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede comer.");
            return;
        }
        switch (alimento) {
            case 1 -> { decir(nombre + " come hojuelas."); aplicarCambios(-30, 10, 3); }
            case 2 -> { decir(nombre + " come granulos."); aplicarCambios(-15, 5, 10); }
            case 3 -> { decir(nombre + " devora un trozo de alga."); aplicarCambios(-40, 20, 20); }
            default -> decir("Alimento no valido para un pez dorado.");
        }
    }

    @Override
    public int jugar(int tipoDeJuego) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede jugar.");
            return 0;
        }
        if (energia < 10) {
            decir(nombre + " esta muy cansado para jugar.");
            return 0;
        }
        int gasto = 0;
        switch (tipoDeJuego) {
            case 1 -> { decir(nombre + " se esconde en su castillo."); gasto = 10; aplicarCambios(5, -gasto, 15); }
            case 2 -> { gasto = 5; aplicarCambios(3, -gasto, 10); salirNadar(); }
            case 3 -> { decir(nombre + " juega con su companiero."); gasto = 20; aplicarCambios(10, -gasto, 20); }
            default -> decir("Juego no valido (1 castillo, 2 nadar, 3 companiero).");
        }
        return gasto;
    }

    @Override
    public String getMenuComida() {
        return "1. Hojuelas\n2. Granulos\n3. Trozo de alga";
    }

    @Override
    public String getMenuJuegos() {
        return "1. Esconderse en el castillo\n2. Salir a nadar\n3. Jugar con su compañero";
    }

    @Override
    public void hacerSonido() {
        decir(nombre + " dice: glu... glu... glu");
    }

    @Override
    public void comportamientoEspecifico() {
        salirNadar();
    }

    public int getCalidadAgua() { return calidadAgua; }
    public void setCalidadAgua(int calidadAgua) { this.calidadAgua = calidadAgua; }

    public int getControlTemperatura() { return controlTemperatura; }
    public void setControlTemperatura(int controlTemperatura) { this.controlTemperatura = controlTemperatura; }

    public int getOxigenacion() { return oxigenacion; }
    public void setOxigenacion(int oxigenacion) { this.oxigenacion = oxigenacion; }

    @Override
    public String getCaracteristicas() {
        return "Calidad del agua: " + calidadAgua + "\n"
                + "Control de temperatura: " + controlTemperatura + "\n"
                + "Oxigenación: " + oxigenacion;
    }

    @Override
    public String toString() {
        return "[PezDorado] " + super.toString() + " | agua: " + calidadAgua
                + " | temperatura: " + controlTemperatura + " | oxigenacion: " + oxigenacion;
    }
}