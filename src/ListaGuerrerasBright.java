import javax.swing.*;
import java.util.ArrayList;

public class ListaGuerrerasBright {
    private NodoDoble start;

    public NodoDoble getStart(){
        return start;
    }
    public ListaGuerrerasBright() {
        this.start = null;
    }

    public boolean insertarGuerrera(GuerreraBrightMoon nueva){
        NodoDoble nuevo = new NodoDoble(nueva);
        if(start == null){
            start = nuevo;
            return true;
        }

        NodoDoble actual = start;
        NodoDoble anterior = null;

        while (actual != null && actual.guerreraBri.getId() < nueva.getId()){
            anterior = actual;
            actual = actual.sig;
        }
         return validarDuplicados(anterior,actual,nuevo);
    }

    private boolean validarDuplicados(NodoDoble ant, NodoDoble sig, NodoDoble nuevo){
        if ((sig != null) && (sig.guerreraBri.getId() == nuevo.guerreraBri.getId())) {
            JOptionPane.showMessageDialog(null, "Error, ID ya registrado");
            return false;
        }

        if (ant == null) {
            nuevo.sig = start;
            if (start != null) start.ant = nuevo;
            start = nuevo;
        } else {
            nuevo.sig = sig;
            nuevo.ant = ant;
            ant.sig = nuevo;
            if (sig != null) sig.ant = nuevo;
        }

        return true;
    }




    public  GuerreraBrightMoon buscarBinaria(int id){
        ArrayList<GuerreraBrightMoon> lista = new ArrayList<>();
        NodoDoble actual = start;
       while (actual != null){
           lista.add(actual.guerreraBri);
           actual = actual.sig;
       }

       int derecha = lista.size() -1;
       int izquierda = 0;

       while (izquierda <= derecha){
           int medio = (izquierda + derecha) /2;
           GuerreraBrightMoon guerrera = lista.get(medio);

           if(guerrera.getId() == id){
               return guerrera;
           } else if (guerrera.getId() < id) {
               izquierda = medio +1;

           } else {
               derecha = medio -1;
           }
       }

       return  null;

    }

    public ArrayList<GuerreraBrightMoon> especialidad(String poder){
        ArrayList<GuerreraBrightMoon> lista = new ArrayList<>();
        NodoDoble actual = start;

        while (actual != null) {
            if (actual.guerreraBri.getPoderBatalla().equals(poder)) {
                lista.add(actual.guerreraBri);
            }
            actual = actual.sig;
        }
        return lista;
    }

    public void ordenarPorNivel(ArrayList<GuerreraBrightMoon> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            int max = i;
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j).getNivelEstrategia() > lista.get(max).getNivelEstrategia()) {
                    max = j;
                }
            }
            if (max != i) {
                GuerreraBrightMoon temp = lista.get(i);
                lista.set(i, lista.get(max));
                lista.set(max, temp);
            }
        }
    }


    public int contarUbicacion(String ubicacion) {
        return contarRecursivo(start, ubicacion);
    }

    private int contarRecursivo(NodoDoble nodo, String ubicacion) {
        if (nodo == null) return 0;
        int suma = nodo.guerreraBri.getUbicacion().equals(ubicacion) ? 1 : 0;
        return suma + contarRecursivo(nodo.sig, ubicacion);
    }

    public boolean modificarGuerrera(GuerreraBrightMoon gModificada) {
        NodoDoble aux = start;
        while (aux != null) {
            if (aux.guerreraBri.getId() == gModificada.getId()) {
                aux.guerreraBri.setAlias(gModificada.getAlias());
                aux.guerreraBri.setPoderBatalla(gModificada.getPoderBatalla());
                aux.guerreraBri.setNivelEstrategia(gModificada.getNivelEstrategia());
                aux.guerreraBri.setUbicacion(gModificada.getUbicacion());
                return true;
            }
            aux = aux.sig;
        }
        return false;
    }
}
