package org.uabc.ic.proyectotamagochicongui.Modelo;

import java.util.ArrayList;

public class Coleccion {
    private final ArrayList<Tamagochi> misJuguetes = new ArrayList<>();

    public void agregar(Tamagochi t) {
        misJuguetes.add(t);
    }

    public String listaJuguetes() {
        StringBuilder cadena = new StringBuilder();
        for (Tamagochi t : misJuguetes) {
            cadena.append(t).append("\n");
        }
        return cadena.toString();
    }

    public String listaNombres() {
        if (misJuguetes.isEmpty()) {
            return "No hay tamagochis registrados todavia.";
        }
        StringBuilder cadena = new StringBuilder("Tamagochis activos:\n");
        for (Tamagochi t : misJuguetes) {
            cadena.append("- ").append(t.getNombre())
                    .append(" (").append(t.getClass().getSimpleName()).append(")\n");
        }
        return cadena.toString();
    }

    public Tamagochi buscarTamagochi(String encontrar) {
        return misJuguetes.stream()
                .filter(t -> t.getNombre().equalsIgnoreCase(encontrar))
                .findFirst()
                .orElse(null);
    }

    public void pasarTiempoATodos() {
        for (Tamagochi t : misJuguetes) {
            t.pasoDelTiempo();
        }
    }

    public java.util.List<String> eliminarMuertos() {
        java.util.List<String> muertos = new ArrayList<>();
        for (int i = misJuguetes.size() - 1; i >= 0; i--) {
            Tamagochi t = misJuguetes.get(i);
            if (t.morir()) {
                muertos.add(t.getNombre());
                misJuguetes.remove(i);
            }
        }
        return muertos;
    }

    public void agregarPerro(String nombre, int energia, int hambre, int felicidad, int salud,
                             int agresividad, int imper) {
        agregar(new Perro(nombre, energia, hambre, felicidad, salud, agresividad, imper));
    }

    public void agregarPerDorado(String nombre, int energia, int hambre, int felicidad, int salud,
                                 int agua, int controlTemperatura, int oxigenacion) {
        agregar(new PezDorado(nombre, energia, hambre, felicidad, salud, agua, controlTemperatura, oxigenacion));
    }

    public void agregarAguila(String nombre, int energia, int hambre, int felicidad, int salud,
                              int fuerza, int velocidad) {
        agregar(new Aguila(nombre, energia, hambre, felicidad, salud, fuerza, velocidad));
    }

    public void agregarDragon(String nombre, int energia, int hambre, int felicidad, int salud,
                              int tamanio, boolean tirarFuego, int apetito) {
        agregar(new Dragon(nombre, energia, hambre, felicidad, salud, tamanio, tirarFuego, apetito));
    }
}