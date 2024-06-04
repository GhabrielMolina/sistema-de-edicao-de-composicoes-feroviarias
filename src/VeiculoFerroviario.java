public abstract class VeiculoFerroviario {
    private int id, capacidadeCarga;
    private Trem trem;
    
    public VeiculoFerroviario(int id, int capacidadeCarga, Trem trem) {
        this.id = id;
        this.capacidadeCarga = capacidadeCarga;
        this.trem = trem;
    }

    public Trem getTrem() {
        return trem;
    }

    public void vincula(Trem t) {
        trem = t;
    }

    public void desvincula() {
        trem = null;
    }

    public int getId() {
        return id;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    @Override
    public String toString() {
        return "id=" + id + ", capacidadeCarga=" + capacidadeCarga + ", trem=" + trem;
    }    
}
