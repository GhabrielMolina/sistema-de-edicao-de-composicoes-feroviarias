public class Locomotiva extends VeiculoFerroviario {
    private int nroMaxVagoes;

    public Locomotiva(int id, int capacidadeCarga, Trem trem, int nroMaxVagoes) {
        super(id, capacidadeCarga, trem);
        this.nroMaxVagoes = nroMaxVagoes;
    }

    

    @Override
    public String toString() {
        return super.toString() + ", nroMaxVagoes=" + nroMaxVagoes;
    }



    public void setNroMaxVagoes(int nroMaxVagoes) {
        this.nroMaxVagoes = nroMaxVagoes;
    }



    public int getNroMaxVagoes() {
        return nroMaxVagoes;
    }
}
