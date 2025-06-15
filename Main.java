import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArvoreMunicipios arvore = new ArvoreMunicipios();
        String[] opcoes = {
                "Inserir município",
                "Listar todos os municípios",
                "Exibir densidade demográfica",
                "Exibir porcentagem da área",
                "Top 5 maiores densidades",
                "Encerrar"
        };

        int escolha;
        do {
            escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu INPT", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0 -> {
                    String nome = JOptionPane.showInputDialog("Nome do município:");
                    double area = Double.parseDouble(JOptionPane.showInputDialog("Área (km²):"));
                    int pop = Integer.parseInt(JOptionPane.showInputDialog("População:"));
                    arvore.inserir(new Municipio(nome, area, pop));
                    JOptionPane.showMessageDialog(null, "Município inserido com sucesso!");
                }
                case 1 -> {
                    String resultado = arvore.listar();
                    JOptionPane.showMessageDialog(null, "Municípios em ordem alfabética:\n\n" + resultado);
                }
                case 2 -> {
                    String resultado = arvore.Densidades();
                    JOptionPane.showMessageDialog(null, "Densidade demográfica:\n\n" + resultado);
                }
                case 3 -> {
                    double areaNacional = Double.parseDouble(JOptionPane.showInputDialog("Área total do território nacional (km²):"));
                    String resultado = arvore.Porcentagens(areaNacional);
                    JOptionPane.showMessageDialog(null, "Porcentagem da área:\n\n" + resultado);
                }
                case 4 -> {
                    List<Municipio> todos = arvore.getTodosMunicipios();
                    todos.sort((a, b) -> Double.compare(b.getDensidade(), a.getDensidade()));

                    String resultado = "";
                    double limite = 0;
                    int i = 0;
                    for (Municipio m : todos) {
                        if (i < 5) {
                            resultado += m.getNome() + ": " +
                                    String.format("%.2f hab/km²", m.getDensidade()) + "\n";
                            limite = m.getDensidade();
                            i++;
                        } else if (m.getDensidade() == limite) {
                            resultado += m.getNome() + ": " +
                                    String.format("%.2f hab/km²", m.getDensidade()) + "\n";
                        } else {
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Top 5 maiores densidades demográficas:\n\n" + resultado);
                }
            }
        } while (escolha != 5);
    }
}
