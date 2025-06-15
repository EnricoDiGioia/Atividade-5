public class Municipio {
    private String nome;
    private double area;
    private int populacao;

    public Municipio(String nome, double area, int populacao) {
        this.nome = nome;
        this.area = area;
        this.populacao = populacao;
    }

    public String getNome() {
        return nome;
    }

    public double getArea() {
        return area;
    }

    public int getPopulacao() {
        return populacao;
    }

    public double getDensidade() {
        return populacao / area;
    }
}
