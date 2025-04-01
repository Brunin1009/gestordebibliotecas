package com.example.gestordebibliotecas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.Libro;
import models.LibroDigital;
import models.LibroDigitalPirata;
import models.LibroNuevo;
import models.LibroUsado;

public class MainActivity extends AppCompatActivity {
    private List<Libro> listaLibros = new ArrayList<>();
    private TextView tvResultado;
    private EditText etBusqueda;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
        etBusqueda = findViewById(R.id.etBusqueda);

        inicializarLibrosEjemplo();

        Button btnMostrarInfo = findViewById(R.id.btnMostrarInfo);
        Button btnCalcularPrecios = findViewById(R.id.btnCalcularPrecios);
        Button btnAplicarDescuento = findViewById(R.id.btnAplicarDescuento);
        Button btnBuscarLibros = findViewById(R.id.btnBuscarLibros);
        Button btnOrdenarPorPrecio = findViewById(R.id.btnOrdenarPorPrecio);
        Button btnGenerarResumen=findViewById(R.id.btnGenerarResumen);
        btnMostrarInfo.setOnClickListener(v -> mostrarInformacionLibros());
        btnCalcularPrecios.setOnClickListener(v -> calcularPreciosLibros());
        btnAplicarDescuento.setOnClickListener(v -> aplicarDescuentoAutor("Gabriel García Márquez", 10));
        btnBuscarLibros.setOnClickListener(v -> buscarLibros());
        btnOrdenarPorPrecio.setOnClickListener(v -> ordenarLibrosPorPrecio());

        btnGenerarResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarResumenPorTipo();


            }
        });


    }

    private void generarResumenPorTipo() {
        // Crear un mapa para almacenar las sumas por tipo
        Map<String, Integer> resumen = new LinkedHashMap<>();

        // Inicializar los tipos que conoces
        resumen.put("Libro Regular", 0);
        resumen.put("Libro Nuevo", 0);
        resumen.put("Libro Usado", 0);
        resumen.put("Libro Digital", 0);
        resumen.put("Libro Digital Pirata", 0);

        // Recorrer todos los libros y sumar por tipo
        for (Libro libro : listaLibros) {
            String tipo = obtenerTipoLibro(libro);
            int sumaActual = resumen.get(tipo);
            resumen.put(tipo, sumaActual + libro.getNumero());
        }

        // Construir el texto del resumen
        StringBuilder sb = new StringBuilder();
        sb.append("RESUMEN POR TIPO DE LIBRO\n\n");
        sb.append(String.format("%-20s %10s\n", "TIPO", "CANTIDAD"));
        sb.append("--------------------------------\n");

        for (Map.Entry<String, Integer> entry : resumen.entrySet()) {
            sb.append(String.format("%-20s %10d\n", entry.getKey(), entry.getValue()));
        }

        // Mostrar el resultado
        tvResultado.setText(sb.toString());
    }

    // Método auxiliar para determinar el tipo de libro
    private String obtenerTipoLibro(Libro libro) {
        if (libro instanceof LibroDigitalPirata) {
            return "Libro Digital Pirata";
        } else if (libro instanceof LibroDigital) {
            return "Libro Digital";
        } else if (libro instanceof LibroUsado) {
            return "Libro Usado";
        } else if (libro instanceof LibroNuevo) {
            return "Libro Nuevo";
        } else {
            return "Libro Regular";
        }
    }

    private void inicializarLibrosEjemplo() {
        listaLibros.add(new LibroNuevo(
                "Cien años de soledad",
                "LIB001",
                50.0,
                "Gabriel García Márquez",
                1967,
                100,
                15.0,
                12
        ));

        listaLibros.add(new LibroNuevo(
                "Vendra la muerte y tendra tus ojos",
                "LIB009",
                50.0,
                "Luigi Vincenzo",
                1967,
                200,
                15.0,
                12
        ));

        listaLibros.add(new LibroUsado(
                "El Principito",
                "LIB002",
                30.0,
                "Antoine de Saint-Exupéry",
                1943,
                200,
                5,
                0.8,
                "Juan Pérez"
        ));

        listaLibros.add(new LibroUsado(
                "House of leaves",
                "LIB007",
                30.0,
                "Mark Z. Danielvesky",
                1943,
                200,
                5,
                0.8,
                "SAntiago Camacho"
        ));

        listaLibros.add(new LibroDigital(
                "Clean Code",
                "LIB003",
                40.0,
                "Robert C. Martin",
                2008,
                9,
                "PDF",
                5.2,
                Arrays.asList("iOS", "Android")
        ));
        listaLibros.add(new LibroDigital(
                "Clean Code 2",
                "LIB008",
                40.0,
                "Robert C. Martin",
                2008,
                100,
                "PDF",
                5.2,
                Arrays.asList("iOS", "Android")
        ));

        listaLibros.add(new LibroDigitalPirata(
                "Design Patterns",
                "LIB004",
                45.0,
                "Erich Gamma",
                1994,
                150,
                "EPUB",
                3.8,
                Arrays.asList("Windows"),
                "Amazon"
        ));

    }
    private void mostrarInformacionLibros() {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : listaLibros) {
            sb.append(libro.obtenerInformacion()).append("\n\n");
        }
        tvResultado.setText(sb.toString());
    }
    private void calcularPreciosLibros() {
        StringBuilder sb = new StringBuilder("Precios de los libros:\n\n");
        for (Libro libro : listaLibros) {
            sb.append(libro.getTitulo())
                    .append(": $")
                    .append(String.format("%.2f", libro.calcularPrecio()))
                    .append("\n");
        }
        tvResultado.setText(sb.toString());
    }
    private void aplicarDescuentoAutor(String autor, double descuento) {
        for (Libro libro : listaLibros) {
            if (libro.getAutor().equals(autor)) {
                if (libro instanceof LibroNuevo) {
                    LibroNuevo libroNuevo = (LibroNuevo) libro;
                    libroNuevo.setDescuentoPromocional(libroNuevo.getDescuentoPromocional() + descuento);
                } else if (libro instanceof LibroUsado) {
                    LibroUsado libroUsado = (LibroUsado) libro;
                    libroUsado.setFactorDesgaste(libroUsado.getFactorDesgaste() * (1 - descuento/100));
                }
            }
        }
        tvResultado.setText("Descuento aplicado a libros de " + autor);
    }
    private void buscarLibros() {
        String textoBusqueda = etBusqueda.getText().toString().trim().toLowerCase();

        if (textoBusqueda.isEmpty()) {
            Toast.makeText(this, "Ingrese un término de búsqueda", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        boolean encontrado = false;

        for (Libro libro : listaLibros) {
            if (libro.getTitulo().toLowerCase().contains(textoBusqueda) ||
                    libro.getAutor().toLowerCase().contains(textoBusqueda)) {

                sb.append(libro.obtenerInformacion())
                        .append("\n——————————————————————————\n\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            sb.append("No se encontraron libros con: ").append(textoBusqueda);
        }

        tvResultado.setText(sb.toString());
    }
    private void ordenarLibrosPorPrecio() {
        // Crear una copia de la lista para no modificar la original
        List<Libro> librosOrdenados = new ArrayList<>(listaLibros);

        // Ordenar por precio base
        librosOrdenados.sort((libro1, libro2) ->
                Double.compare(libro1.getPrecioBase(), libro2.getPrecioBase())
        );

        StringBuilder sb = new StringBuilder("Libros ordenados por precio:\n\n");
        for (Libro libro : librosOrdenados) {
            sb.append(libro.getTitulo())
                    .append(": $")
                    .append(String.format("%.2f", libro.getPrecioBase()))
                    .append("\n");
        }

        tvResultado.setText(sb.toString());
    }
}