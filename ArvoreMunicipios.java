import java.util.ArrayList;
import java.util.List;

public class ArvoreMunicipios {
    private NoArvore raiz;

    public void inserir(Municipio m) {
        raiz = addRec(raiz, m);
    }

    private NoArvore addRec(NoArvore no, Municipio m) {
        if (no == null) return new NoArvore(m);
        if (m.getNome().compareToIgnoreCase(no.municipio.getNome()) < 0)
            no.esquerda = addRec(no.esquerda, m);
        else
            no.direita = addRec(no.direita, m);
        return no;
    }

    public String listar() {
        return listarRec(raiz);
    }

    private String listarRec(NoArvore no) {
        if (no == null) return "";
        return listarRec(no.esquerda)
                + no.municipio.getNome() + "\n"
                + listarRec(no.direita);
    }

    public String Densidades() {
        return densidadesRec(raiz);
    }

    private String densidadesRec(NoArvore no) {
        if (no == null) return "";
        return densidadesRec(no.esquerda)
                + String.format("%s: %.2f hab/km²\n", no.municipio.getNome(), no.municipio.getDensidade())
                + densidadesRec(no.direita);
    }

    public String Porcentagens(double areaNacional) {
        return porcentagensRec(raiz, areaNacional);
    }

    private String porcentagensRec(NoArvore no, double areaNacional) {
        if (no == null) return "";
        double perc = (no.municipio.getArea() / areaNacional) * 100;
        return porcentagensRec(no.esquerda, areaNacional)
                + String.format("%s: %.2f%% do território\n", no.municipio.getNome(), perc)
                + porcentagensRec(no.direita, areaNacional);
    }

    public List<Municipio> getTodosMunicipios() {
        List<Municipio> lista = new ArrayList<>();
        preencher(raiz, lista);
        return lista;
    }

    private void preencher(NoArvore no, List<Municipio> lista) {
        if (no != null) {
            preencher(no.esquerda, lista);
            lista.add(no.municipio);
            preencher(no.direita, lista);
        }
    }
}
