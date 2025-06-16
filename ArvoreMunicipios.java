import java.util.ArrayList;
import java.util.List;

public class ArvoreMunicipios {
    private NoArvore raiz;

    public void inserir(Municipio m) {
        if (raiz == null) {
            raiz = new NoArvore(m);
            return;
        }
        NoArvore atual = raiz;
        while (true) {
            if (m.getNome().compareToIgnoreCase(atual.municipio.getNome()) < 0) {
                if (atual.esquerda == null) {
                    atual.esquerda = new NoArvore(m);
                    break;
                }
                atual = atual.esquerda;
            } else {
                if (atual.direita == null) {
                    atual.direita = new NoArvore(m);
                    break;
                }
                atual = atual.direita;
            }
        }
    }

    public String listar() {
        return listar(raiz);
    }

    private String listar(NoArvore no) {
        if (no == null) return "";
        return listar(no.esquerda)
                + no.municipio.getNome() + "\n"
                + listar(no.direita);
    }

    public String Densidades() {
        return densidades(raiz);
    }

    private String densidades(NoArvore no) {
        if (no == null) return "";
        return densidades(no.esquerda)
                + String.format("%s: %.2f hab/km2\n", no.municipio.getNome(), no.municipio.getDensidade())
                + densidades(no.direita);
    }

    public String Porcentagens(double areaNacional) {
        return porcentagens(raiz, areaNacional);
    }

    private String porcentagens(NoArvore no, double areaNacional) {
        if (no == null) return "";
        double perc = (no.municipio.getArea() / areaNacional) * 100;
        return porcentagens(no.esquerda, areaNacional)
                + String.format("%s: %.2f%% do território\n", no.municipio.getNome(), perc)
                + porcentagens(no.direita, areaNacional);
    }

    public List<Municipio> getMunicipios() {
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
