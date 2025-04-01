package models;

import java.util.List;

public class LibroDigitalPirata extends LibroDigital {
    private String procedencia;

    public LibroDigitalPirata(String titulo, String codigo, double precioBase, String autor,
                              int añoPublicacion,int numero, String formato, double tamañoMB,
                              List<String> restriccionesDispositivos, String procedencia) {
        super(titulo, codigo, precioBase, autor, añoPublicacion,numero, formato, tamañoMB, restriccionesDispositivos);
        this.procedencia = procedencia;
    }

    @Override
    public double calcularPrecio() {
        return getPrecioBase() * 0.7 - (getTamañoMB() * 0.01);
    }

    @Override
    public String obtenerInformacion() {
        return super.obtenerInformacion() + "\n" +
                "Tipo: Digital Pirata\n" +
                "Procedencia: " + procedencia + "\n" +
                "Precio Final (sin restricciones): " + calcularPrecio();
    }
    public String getProcedencia() {return procedencia;}
}
