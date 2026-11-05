package cl.duoc.maquinaria.repositorio;

import cl.duoc.maquinaria.model.Excavadora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExcavadoraRepository {

    private final Path archivo = Path.of("excavadoras.json");
    private final ObservableList<Excavadora> listaExcavadoras = FXCollections.observableArrayList();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ExcavadoraRepository() throws IOException {
        cargar();
    }

    private void cargar() throws IOException {
        if (!Files.exists(archivo)) {
            Files.writeString(archivo, "[]");
            return;
        }
        String json = Files.readString(archivo);
        Excavadora[] datos = gson.fromJson(json, Excavadora[].class);
        if (datos != null) {
            listaExcavadoras.addAll(datos);
        }
    }

    public ObservableList<Excavadora> obtenerTodas() {
        return listaExcavadoras;
    }

    public void agregar(Excavadora excavadora) throws IOException {
        listaExcavadoras.add(excavadora);
        guardar();
    }

    public void eliminar(Excavadora excavadora) throws IOException {
        listaExcavadoras.remove(excavadora);
        guardar();
    }

    private void guardar() throws IOException {
        Files.writeString(archivo, gson.toJson(listaExcavadoras));
    }


}
