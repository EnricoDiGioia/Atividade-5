import java.util.ArrayList;
import java.util.List;

public class ArvoreMunicipios {
    private NoArvore raiz;

    public void inserir(Municipio m) {
        raiz = inserirRec(raiz, m);
    }

    private NoArvore inserirRec(NoArvore no, Municipio m) {
        if (no == null) return new NoArvore(m);
        if (m.getNome().compareToIgnoreCase(no.municipio.getNome()) < 0)
            no.esquerda = inserirRec(no.esquerda, m);
        else
            no.direita = inserirRec(no.direita, m);
        return no;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        listarRec(raiz, sb);
        return sb.toString();
    }

    private void listarRec(NoArvore no, StringBuilder sb) {
        if (no != null) {
            listarRec(no.esquerda, sb);
            sb.append(no.municipio.getNome()).append("\n");
            listarRec(no.direita, sb);
        }
    }

    public String Densidades() {
        StringBuilder sb = new StringBuilder();
        densidadesRec(raiz, sb);
        return sb.toString();
    }

    private void densidadesRec(NoArvore no, StringBuilder sb) {
        if (no != null) {
            densidadesRec(no.esquerda, sb);
            sb.append(no.municipio.getNome())
              .append(": ")
              .append(String.format("%.2f hab/km²", no.municipio.getDensidade()))
              .append("\n");
            densidadesRec(no.direita, sb);
        }
    }

    public String Porcentagens(double areaNacional) {
        StringBuilder sb = new StringBuilder();
        porcentagensRec(raiz, areaNacional, sb);
        return sb.toString();
    }

    private void porcentagensRec(NoArvore no, double areaNacional, StringBuilder sb) {
        if (no != null) {
            porcentagensRec(no.esquerda, areaNacional, sb);
            double perc = (no.municipio.getArea() / areaNacional) * 100;
            sb.append(no.municipio.getNome())
              .append(": ")
              .append(String.format("%.2f%% do território", perc))
              .append("\n");
            porcentagensRec(no.direita, areaNacional, sb);
        }
    }

    public List<Municipio> getTodosMunicipios() {
        List<Municipio> lista = new ArrayList<>();
        preencherLista(raiz, lista);
        return lista;
    }

    private void preencherLista(NoArvore no, List<Municipio> lista) {
        if (no != null) {
            preencherLista(no.esquerda, lista);
            lista.add(no.municipio);
            preencherLista(no.direita, lista);
        }
    }
}
