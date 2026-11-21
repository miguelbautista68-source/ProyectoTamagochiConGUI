package org.uabc.ic.proyectotamagochicongui.Modelo;

public class Dragon extends Tamagochi {
    private int tamanio;
    private boolean tirarFuego;
    private int apetito;

    public Dragon(String nombre, int energia, int hambre, int felicidad, int salud,
                  int tamanio, boolean tirarFuego, int apetito) {
        super(nombre, energia, hambre, felicidad, salud);
        this.tamanio = tamanio;
        this.tirarFuego = tirarFuego;
        this.apetito = apetito;
    }

    public void rugir() {
        decir(nombre + " dice RRAAAAAUGGGHHH");
    }

    public void tiraFuego() {
        tirarFuego = true;
        decir(nombre + " tira fuego.");
    }

    @Override
    public void hacerSonido() {
        rugir();
    }

    @Override
    public void comportamientoEspecifico() {
        tiraFuego();
    }

    @Override
    public void comer(int alimento) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede comer.");
            return;
        }
        switch (alimento) {
            case 1 -> { decir(nombre + " come ovejas."); aplicarCambios(-30, 10, 3); }
            case 2 -> { decir(nombre + " come una vaca."); aplicarCambios(-15, 5, 10); }
            case 3 -> { decir(nombre + " devora un humano."); aplicarCambios(-40, 20, 20); }
            default -> decir("Alimento no valido para un dragon.");
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
            case 1 -> { decir(nombre + " sale a cazar."); gasto = 10; aplicarCambios(5, -gasto, 15); }
            case 2 -> { gasto = 5; aplicarCambios(3, -gasto, 10); tiraFuego(); }
            case 3 -> { decir(nombre + " sale a volar."); gasto = 20; aplicarCambios(10, -gasto, 20); }
            default -> decir("Ese juego no esta disponible para el dragon.");
        }
        return gasto;
    }

    @Override
    public String getMenuComida() {
        return "1. Oveja\n2. Vaca\n3. Humano";
    }

    @Override
    public String getMenuJuegos() {
        return "1. Salir a cazar\n2. Tirar bolas de fuego\n3. Salir a volar";
    }

    public int getTamanio() { return tamanio; }
    public void setTamanio(int tamanio) { this.tamanio = tamanio; }

    public boolean getTirarFuego() { return tirarFuego; }
    public void setTirarFuego(boolean tirarFuego) { this.tirarFuego = tirarFuego; }

    public int getApetito() { return apetito; }
    public void setApetito(int apetito) { this.apetito = apetito; }

    @Override
    public String getCaracteristicas() {
        return "Tamaño: " + tamanio + "\n"
                + "Apetito: " + apetito + "\n"
                + "Tirando fuego: " + (tirarFuego ? "Sí" : "No");
    }

    @Override
    public String toString() {
        return "[Dragon] " + super.toString() + " | tamaño: " + tamanio + " | apetito: " + apetito;
    }
}