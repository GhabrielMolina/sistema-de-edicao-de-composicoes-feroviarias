import java.util.ArrayList;

public class GaragemFerroviaria {
    private static final int MAX_VEICULOS = 100;
    private ArrayList<VeiculoFerroviario> veiculos;

    private void carregaVeiculos() {
        for(int i = 0; i < 5; i++) {
            veiculos.add(new Locomotiva(i + 1, (i + 1) * 10000, null,  100));
            veiculos.add(new Vagao(i + 6, (i + 1) * 10000, null));
        }
    }

    public GaragemFerroviaria() {
        veiculos = new ArrayList<>(MAX_VEICULOS);
        carregaVeiculos();
    }

    public boolean entraVeiculo(VeiculoFerroviario veiculo) {
        if(veiculos.size() == MAX_VEICULOS) {
            return false;
        }
        veiculos.add(veiculo);
        return true;
    }

    public VeiculoFerroviario saiVeiculo(int id) {
        for(int i = 0; i < veiculos.size(); i++) {
            if(veiculos.get(i).getId() == id) {
                if(veiculos.get(i) instanceof Locomotiva) {
                    Locomotiva l = (Locomotiva) veiculos.get(i);
                    veiculos.remove(i);
                    l.vincula(l.getTrem());
                    return l;
                }
                if(veiculos.get(i) instanceof Vagao) {
                    Vagao v = (Vagao) veiculos.get(i);
                    veiculos.remove(i);
                    return v;
                }
            }
        }
        return null;
    }

    public int getQtdadeVeiculos() {
        return veiculos.size();
    }

    public VeiculoFerroviario getVeiculo(int index){
        if (index<0 || index>= veiculos.size()){
            return null;
        }
        return veiculos.get(index);
    }
}
