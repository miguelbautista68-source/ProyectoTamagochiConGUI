package org.uabc.ic.proyectotamagochicongui.Modelo;

public class Aguila extends Tamagochi {
    private int nivelFuerza;
    private int velocidad;
    private boolean estaVolando;

    public Aguila(String nombre, int energia, int hambre, int felicidad, int salud, int fuerza, int velocidad) {
        super(nombre, energia, hambre, felicidad, salud);
        this.nivelFuerza = fuerza;
        this.velocidad = velocidad;
        this.estaVolando = false;
    }

    public void volar() {
        estaVolando = true;
        decir(nombre + " esta volando.");
    }

    @Override
    public void comer(int alimento) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede comer.");
            return;
        }
        switch (alimento) {
            case 1 -> { decir(nombre + " come gusanos."); aplicarCambios(-30, 10, 3); }
            case 2 -> { decir(nombre + " come un raton."); aplicarCambios(-15, 5, 10); }
            case 3 -> { decir(nombre + " devora una serpiente."); aplicarCambios(-40, 20, 20); }
            default -> decir("Alimento no valido para un aguila.");
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
            case 1 -> { decir(nombre + " juega con su muñeco."); gasto = 10; aplicarCambios(5, -gasto, 15); }
            case 2 -> { gasto = 5; aplicarCambios(3, -gasto, 10); volar(); }
            case 3 -> { decir(nombre + " juega con su amigo."); gasto = 20; aplicarCambios(10, -gasto, 20); }
            default -> decir("Ese juego no esta disponible para el aguila.");
        }
        return gasto;
    }

    @Override
    public String getMenuComida() {
        return "1. Gusanos\n2. Raton\n3. Serpiente";
    }

    @Override
    public String getMenuJuegos() {
        return "1. Jugar con su muñeco\n2. Salir a volar\n3. Jugar con su amigo";
    }

    @Override
    public void hacerSonido() {
        decir(nombre + " dice: AAAAHHHHHH");
    }

    @Override
    public void comportamientoEspecifico() {
        volar();
    }

    public int getNivelFuerza() { return nivelFuerza; }
    public void setNivelFuerza(int nivelFuerza) { this.nivelFuerza = nivelFuerza; }

    public boolean getEstaVolando() { return estaVolando; }
    public void setEstaVolando(boolean estaVolando) { this.estaVolando = estaVolando; }

    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }

    @Override
    public String getCaracteristicas() {
        return "Fuerza: " + nivelFuerza + "\n"
                + "Velocidad: " + velocidad + "\n"
                + "Volando: " + (estaVolando ? "Sí" : "No");
    }

    @Override
    public String toString() {
        return "[Aguila] " + super.toString() + " | fuerza: " + nivelFuerza + " | velocidad: " + velocidad;
    }
}