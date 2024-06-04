import java.util.ArrayList;

public class Trem {
    private int id;
    private ArrayList<VeiculoFerroviario> veiculos;

    public Trem(int id, Locomotiva locomotiva) {
        this.id = id;
        this.veiculos = new ArrayList<>();
        engataLocomotiva(locomotiva);   
    }

    public boolean engataLocomotiva(Locomotiva locomotiva) {
        if(veiculos.size() > 0) {
            return false;
        }
        veiculos.add(locomotiva);
        locomotiva.vincula(this);
        return true;        
    }

    public boolean engataVagao(Vagao vagao){
        if (limiteDeVagoesAtingido() || pesoMaximoDosVagoesAtingido()){
            return false;
        }
        veiculos.add(vagao);
        vagao.vincula(this);
        return true;
    }

    // Métodos auxiliares
    private boolean limiteDeVagoesAtingido(){
        int qtdade = 0;
        for(VeiculoFerroviario l:veiculos){
            if(l instanceof Locomotiva) {
                Locomotiva loc = (Locomotiva)l;
                qtdade += loc.getNroMaxVagoes();
            }
        }
        return veiculos.size() >= qtdade;
    }

    private double pesoMaximoLocomotivasTracionam(){
        double peso = 0;
        double tx = 1.0;
        for(VeiculoFerroviario l:veiculos){
            if(l instanceof Locomotiva) {
                Locomotiva loc = (Locomotiva)l;
                peso += loc.getCapacidadeCarga() * tx;
                tx *= 0.9;
            }
        }
        return peso;
    }

    private boolean pesoMaximoDosVagoesAtingido(){
        double peso = 0.0;
        for(VeiculoFerroviario v : veiculos){
            if(v instanceof Vagao) {
                Vagao vag = (Vagao)v;
                peso += vag.getCapacidadeCarga();
            }
        } 
        return peso >= pesoMaximoLocomotivasTracionam();
    }

    public boolean desengataVagao(Vagao vagao) {
        if (veiculos.remove(vagao)) {
            vagao.desvincula();
            return true;
        }
        return false;
    }

    public boolean editaTrem(int id, Locomotiva novaLocomotiva, ArrayList<Vagao> novosVagoes) {
        // Procura o trem com o ID fornecido
        for (VeiculoFerroviario veiculo : veiculos) {
            if (veiculo instanceof Locomotiva && veiculo.getId() == id) {
                Locomotiva locomotivaAtual = (Locomotiva) veiculo;
                
                // Desvincula a locomotiva atual
                locomotivaAtual.desvincula();

                // Engata a nova locomotiva
                if (!engataLocomotiva(novaLocomotiva)) {
                    // Se falhar em engatar a nova locomotiva, mantém a locomotiva atual
                    engataLocomotiva(locomotivaAtual);
                    return false;
                }

                // Remove todos os vagões do trem
                veiculos.removeAll(novosVagoes);

                // Engata os novos vagões
                for (Vagao vagao : novosVagoes) {
                    if (!engataVagao(vagao)) {
                        // Se falhar em engatar um vagão, remove os vagões já engatados e mantém o estado original
                        for (Vagao v : novosVagoes) {
                            desengataVagao(v);
                        }
                        engataLocomotiva(locomotivaAtual);
                        return false;
                    }
                }

                // Edição bem-sucedida
                return true;
            }
        }

        // Não encontrou o trem com o ID fornecido
        return false;
    }

    @Override
    public String toString(){
        String aux = "T"+id+"+";
        for(VeiculoFerroviario l : veiculos){
            aux += "[L"+l.getId()+"]+";
        }
        return aux;
    }
 }