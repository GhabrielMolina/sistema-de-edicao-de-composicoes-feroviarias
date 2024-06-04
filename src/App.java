public class App {
    public static void exibeVeiculosNaGaragem(GaragemFerroviaria g){
        System.out.println("Veiculos disponiveis na garagem:");
        for(int i = 0; i < g.getQtdadeVeiculos(); i++){
            System.out.println((i + 1) + ")" + g.getVeiculo(i));
        }
    }

    public static void main(String[] args) throws Exception {
        GaragemFerroviaria g = new GaragemFerroviaria();

        exibeVeiculosNaGaragem(g);

        Trem trem = new Trem(999, (Locomotiva)g.saiVeiculo(1));
        trem.engataVagao((Vagao)g.saiVeiculo(6));
        
        // g.entraVeiculo(new Locomotiva(111, 30, null, 5));
        System.out.println(trem);
    }
}
