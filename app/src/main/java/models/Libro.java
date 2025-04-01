package models;

public class Libro {
    private String titulo;
    private double precioBase;
    private String codigo;
    private String autor;
    private int añoPublicacion;

    public  int numero;

    public Libro(String titulo, String codigo, double precioBase, String autor, int añoPublicacion,int numero) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.precioBase = precioBase;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.numero=numero;
    }

    public double calcularPrecio() {
        return precioBase;
    }

    public String obtenerInformacion() {
        return "Título: " + titulo + "\n" +
                "Código: " + codigo + "\n" +
                "Precio Base: " + precioBase + "\n" +
                "Autor: " + autor + "\n" +
                "Año Publicación: " + añoPublicacion+"\n"+
                "Numero: "+numero;
    }

    // Getters y setters
    public String getTitulo() { return titulo; }
    public String getCodigo() { return codigo; }
    public double getPrecioBase() { return precioBase; }
    public String getAutor() { return autor; }
    public int getAñoPublicacion() { return añoPublicacion; }

    public int getNumero(){return numero;}
}
