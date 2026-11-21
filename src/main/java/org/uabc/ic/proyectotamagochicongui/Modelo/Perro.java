package org.uabc.ic.proyectotamagochicongui.Modelo;

public class Perro extends Tamagochi {
    private int nivelImperactividad;
    private int nivelAgresividad;

    public Perro(String nombre, int energia, int hambre, int felicidad, int salud, int agresividad, int imper) {
        super(nombre, energia, hambre, felicidad, salud);
        this.nivelAgresividad = agresividad;
        this.nivelImperactividad = imper;
    }

    public Perro() {
        super("Sansa", 10, 5, 6, 7);
        this.nivelAgresividad = 30;
        this.nivelImperactividad = 60;
    }

    @Override
    public void comer(int tipoAlimento) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede comer.");
            return;
        }
        switch (tipoAlimento) {
            case 1 -> { decir(nombre + " come croquetas."); aplicarCambios(-30, 10, 3); }
            case 2 -> { decir(nombre + " roe un hueso."); aplicarCambios(-15, 5, 10); }
            case 3 -> { decir(nombre + " devora un trozo de carne."); aplicarCambios(-40, 20, 20); }
            default -> decir("Alimento no valido para un perro (1 croquetas, 2 hueso, 3 carne).");
        }
    }

    @Override
    public int jugar(int tipoJuego) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede jugar.");
            return 0;
        }
        if (energia < 10) {
            decir(nombre + " esta muy cansado para jugar.");
            return 0;
        }
        int gasto = 0;
        switch (tipoJuego) {
            case 1 -> { decir(nombre + " juega con la pelota."); gasto = 10; aplicarCambios(5, -gasto, 15); }
            case 2 -> { gasto = 5; aplicarCambios(3, -gasto, 10); perseguirCola(); }
            case 3 -> { decir(nombre + " sale a correr."); gasto = 20; aplicarCambios(10, -gasto, 20); }
            default -> decir("Juego no valido (1 pelota, 2 perseguir cola, 3 correr).");
        }
        return gasto;
    }

    @Override
    public String getMenuComida() {
        return "1. Croquetas\n2. Hueso\n3. Carne";
    }

    @Override
    public String getMenuJuegos() {
        return "1. Jugar con la pelota\n2. Perseguir cola\n3. Salir a correr";
    }

    @Override
    public void hacerSonido() {
        if (estaDormidoYAvisa("hacer sonido")) return;
        decir(nombre + " dice guau guau.");
    }

    public void perseguirCola() {
        if (estaDormidoYAvisa("perseguir su cola")) return;
        decir(nombre + " persigue su cola dando vueltas.");
    }

    @Override
    public void comportamientoEspecifico() {
        perseguirCola();
    }

    public int getNivelImperactividad() { return nivelImperactividad; }
    public void setNivelImperactividad(int n) { this.nivelImperactividad = limite(n); }

    public int getNivelAgresividad() { return nivelAgresividad; }
    public void setNivelAgresividad(int n) { this.nivelAgresividad = limite(n); }

    @Override
    public String getCaracteristicas() {
        return "Agresividad: " + nivelAgresividad + "\n"
                + "Imperactividad: " + nivelImperactividad;
    }

    @Override
    public String toString() {
        return "[Perro] " + super.toString() + " | agresividad: " + nivelAgresividad
                + " | imperactividad: " + nivelImperactividad;
    }
}