package org.uabc.ic.proyectotamagochicongui.Modelo;

public abstract class Tamagochi implements TamagochiActions {

    protected String nombre;
    protected int energia;
    protected int hambre;      // 0 = satisfecho, 100 = muerto de hambre
    protected int felicidad;
    protected int salud;
    protected boolean durmiendo;
    protected boolean morir;
    private String ultimoMensaje = "";

    public Tamagochi(String nombre, int energia, int hambre, int felicidad, int salud) {
        this.nombre = nombre;
        this.energia = energia;
        this.hambre = hambre;
        this.felicidad = felicidad;
        this.salud = salud;
        this.durmiendo = false;
        this.morir = false;
    }

    public Tamagochi() {
        this("Solovino", 10, 0, 40, 50);
    }

    // Imprime en consola y guarda el mensaje para que la GUI lo muestre
    protected void decir(String mensaje) {
        ultimoMensaje = mensaje;
        System.out.println(mensaje);
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    protected int limite(int valor) {
        return Math.max(0, Math.min(100, valor));
    }

    protected void aplicarCambios(int conHambre, int conEnergia, int conFelicidad) {
        hambre = limite(hambre + conHambre);
        energia = limite(energia + conEnergia);
        felicidad = limite(felicidad + conFelicidad);
    }

    public void pasoDelTiempo() {
        hambre = limite(hambre + 5);
        felicidad = limite(felicidad - 3);
        if (!durmiendo) {
            energia = limite(energia - 3);
        }
        if (hambre >= 80) {
            salud = limite(salud - 5);
        }
    }

    // Comun a todas las mascotas
    @Override
    public int dormir(int horasDeSueno) {
        int antes = energia;
        durmiendo = true;
        energia = limite(energia + horasDeSueno * 10);
        hambre = limite(hambre + horasDeSueno * 2);
        decir(nombre + " duerme " + horasDeSueno + " horas.");
        return energia - antes;
    }

    public void despertar() {
        durmiendo = false;
        decir(nombre + " desperto.");
    }

    public boolean isDurmiendo() {
        return durmiendo;
    }

    public String estadoDeAnimo() {
        if (durmiendo) {
            return "Esta durmiendo";
        }
        if (hambre >= 80 || energia <= 20 || felicidad <= 20 || salud <= 20) {
            return "Esta mal";
        }
        if (hambre >= 50 || energia <= 50 || felicidad <= 50) {
            return "Necesita atencion";
        }
        return "Todo bien";
    }

    public abstract void comportamientoEspecifico();

    public boolean morir() {
        if (salud <= 0) {
            morir = true;
        }
        return morir;
    }

    protected boolean estaDormidoYAvisa(String accion) {
        if (durmiendo) {
            decir(nombre + " esta durmiendo, no puede " + accion + ".");
            return true;
        }
        return false;
    }

    public int sanar() {
        if (estaDormidoYAvisa("sanar")) {
            return 0;
        }
        int antes = salud;
        salud = limite(salud + 20);
        decir(nombre + " recupero " + (salud - antes) + " de salud.");
        return salud - antes;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = limite(energia); }

    public int getHambre() { return hambre; }
    public void setHambre(int hambre) { this.hambre = limite(hambre); }

    public int getFelicidad() { return felicidad; }
    public void setFelicidad(int felicidad) { this.felicidad = limite(felicidad); }

    public int getSalud() { return salud; }
    public void setSalud(int salud) { this.salud = limite(salud); }

    public abstract String getCaracteristicas();

    @Override
    public String toString() {
        return "Tu tamagochi " + nombre + " | energia: " + energia + " | hambre: " + hambre
                + " | felicidad: " + felicidad + " | salud: " + salud;
    }
}