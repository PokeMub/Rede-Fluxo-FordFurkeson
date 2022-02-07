package br.edu.ifes.si.tpa.trabalho01;

import br.edu.ifes.si.tpa.trabalho01.FordFulkerson;
import br.edu.ifes.si.tpa.trabalho01.RedeFluxo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class RedeFluxo {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int A;
    private List<ArestaFluxo>[] adj;
    
    
    public RedeFluxo(int V) {
        if (V < 0) throw new IllegalArgumentException("Número de vértices do grafo deve ser não negativo");
        this.V = V;
        this.A = 0;
        adj = new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    public RedeFluxo(In in ) {
        
        this(in.readInt());
        int A = in.readInt();
        if (A < 0) throw new IllegalArgumentException("número de arestas no grafo deve ser não negativa");
        int j = 0;
        for (int i = 0; i < A; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double capacity = in.readDouble();
            addAresta(new ArestaFluxo(v, w, capacity));
        }
    }

    public int V() {
        return V;
    }

    public int A() {
        return A;
    }

    public void addAresta(ArestaFluxo a) {
        int v = a.de();
        int w = a.para();
        adj[v].add(a);
        adj[w].add(a);
        A++;
    }

    public Iterable<ArestaFluxo> adj(int v) {
        return adj[v];
    }


    public Iterable<ArestaFluxo> arestas() {
        List<ArestaFluxo> list = new ArrayList();
        for (int v = 0; v < V; v++)
            for (ArestaFluxo e : adj(v)) {
                if (e.para() != v)
                    list.add(e);
            }
        return list;
    }


    public String toString() {
        int valorMax ;
        int valorMin ;
        
        StringBuilder s = new StringBuilder();
        s.append(V + " " + A + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ":  ");
            for (ArestaFluxo e : adj[v]) {
                if (e.para() != v) s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        valorMax = valorMax(0);
        valorMin = valorMin(valorMax);
        FordFulkerson(valorMax , valorMin);
        return s.toString();
        
        
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        RedeFluxo G = new RedeFluxo(in);
        System.out.println(G);    
    }
    
    public void FordFulkerson (int valorMax , int valorMin){
        int valorMarcacao =0; // Para receber tamanho da marcacao
        double [][] caminho = new double [valorMax][3]; // variavel para receber os valores , [posicao] [de()] , [posicao] [para()] , [posicao] [qtdFalta]
        String restCapacidadeString;  // para a conversao da quantidae que falta
        double restCapacidadeDouble = 0 ;   // para a conversao da quantidae que falta
        ArrayList<Integer> marcacao = new ArrayList<>();    // Lista para A marcacao
        int qtdMarcacao =0; // Para receber tamanho da marcacao
        int cont5 = 0;  // Contador de possicao de movimento
        double auxiliar;    // Variavel para ajudar, pega um valor no momento para pode fazer o movimento da fila
        int tamanhomarcacao; // Para receber tamanho da marcacao
        double fluxoMenor = -100 ; // Para calcular o fluxo Menor
        double delta = 0; // Para calcular o fluxo menor
        int cont10 =0; // Contador para Atribuicao de residuo
        int zz =0; // Contador do For de Atribuicao de valor de Fluxo na Matriz
        int aux =0; // Contador para Atribuicao de valor de Fluxo na Matriz
        ArrayList<Integer> fila = new ArrayList<>(); // Fila de Movimentacao da marcacao
        int tamanhoFila = 0; // Pega o tamanho da Fila
        int sei =0;  // Contador para Zerar a fila e ela poder entrar no loop dnv
        int cont11 =0; // Contador para Remover o 1 elemento da fila
        int cont12 = 0;  // Contador para Remover o 1 elemento da fila
        int saida =0; // Contador para Sair do Loop
        for (int v = 0; v < (V); v++) {
            for (ArestaFluxo e : adj[v] ) {
                qtdMarcacao = marcacao.size();
                for (int p = 0; p < V; p++) {
                    /////////////////////////////////////////////////////////////////////////
                    /// Remover o 1 elemento da Fila para ela andar
                    if(p==0){
                    }else{
                        if(cont12 == cont11){
                        }else{
                            if(p>1 ){
                                fila.remove(0);
                            }
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////////////////
                    /// Criterito de parada do Loop para ele finalizar
                    if(p != valorMax && p != valorMin ){
                        if(fila.isEmpty()){
                            saida = 1;
                            break;
                        }    
                    }
                    /////////////////////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////////////////////
                    //// Adicicao dos valores para a Matriz
                    cont11 =0;
                    cont12 =0;
                    for (ArestaFluxo eee : adj[p]) {
                        cont11++;
                        if(p==valorMin){
                            if(eee.de() == p){
                                
                                restCapacidadeString = String.valueOf(eee.capacidadeResidualPara(eee.para()));
                                restCapacidadeDouble = Double.valueOf(restCapacidadeString );
                                //System.out.println(restCapacidadeDouble);
                                if(restCapacidadeDouble <= 0){
                                }else{
                                    if(qtdMarcacao == 0){
                                        marcacao.add(eee.para());
                                        //System.out.println(marcacao);
                                        fila.add(eee.para());
                                        caminho[cont5][0] = eee.de();
                                        caminho[cont5][1] = eee.para();
                                        caminho[cont5][2] = ( eee.capacidade() - eee.fluxo());
                                        //System.out.println(eee.capacidade());
                                        //System.out.println(eee.fluxo());
                                        cont5++;
                                        qtdMarcacao = marcacao.size();
                                    }else{
                                        for(zz =0 ; zz < marcacao.size() ; zz++){
                                            if(marcacao.get(zz) == eee.para()){
                                                aux = 1;
                                            }
                                            if(aux == 1){
                                                break;
                                            }
                                            if(zz == (marcacao.size() - 1) ){
                                                marcacao.add(eee.para());
                                                fila.add(eee.para());
                                                caminho[cont5][0] = eee.de();
                                                caminho[cont5][1] = eee.para();
                                                caminho[cont5][2] = ( eee.capacidade() - eee.fluxo());
                                                cont5++;
                                            }
                                        }
                                        aux =0;
                                    }  
                                }
                            }
                        }else{
                            if(eee.de() == fila.get(0)){
                                restCapacidadeString = String.valueOf(eee.capacidadeResidualPara(eee.para()));
                                restCapacidadeDouble = Double.valueOf(restCapacidadeString );
                                if(restCapacidadeDouble <= 0){
                                }else{
                                    if(qtdMarcacao == 0){
                                        marcacao.add(eee.para());
                                        fila.add(eee.para());
                                        caminho[cont5][0] = eee.de();
                                        caminho[cont5][1] = eee.para();
                                        caminho[cont5][2] = ( eee.capacidade() - eee.fluxo());
                                        cont5++;
                                        qtdMarcacao = marcacao.size();
                                    }else{
                                        for(zz =0 ; zz < marcacao.size() ; zz++){
                                            if(marcacao.get(zz) == eee.para()){
                                                aux = 1;
                                            }
                                            if(aux == 1){
                                                break;
                                            }
                                            if(zz == (marcacao.size() - 1) ){
                                                marcacao.add(eee.para());
                                                fila.add(eee.para());
                                                caminho[cont5][0] = eee.de();
                                                caminho[cont5][1] = eee.para();
                                                caminho[cont5][2] = ( eee.capacidade() - eee.fluxo());
                                                cont5++;
                                            }
                                        } 
                                        aux =0;
                                    }
                                }
                            }else{
                                cont12++;
                            }
                            if(fila.isEmpty()){
                            }else{
                                //System.out.println(marcacao);
                                tamanhoFila = fila.size();
                                if(fila.get(tamanhoFila -1) == valorMax){
                                    fila.clear();
                                    sei =1;
                                    break;
                                }
                            }
                        }
                        if(sei == 1){
                            break;
                        }    
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if(sei == 1){
                        break;
                    } 
                    if(saida == 1){
                        break;
                    }
                }
                if(saida == 1){
                    break;
                }    
            }
            valorMarcacao = marcacao.size() ;
            System.out.println(valorMarcacao);
            if(marcacao.get(valorMarcacao -1 ) == valorMax){
                auxiliar = 0;
                tamanhomarcacao = marcacao.size();
                fluxoMenor = -100;
                delta =0;
                cont10 =0;
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /// verificacao do caminho que irar passar o fluxo, no caso sauvando o menor valor do fluxo para ser adicionar na prox vez
                for(cont5 = (marcacao.size() -1) ; cont5 > valorMin; cont5--){
                    for (int cont9 = 0; cont9 < (V); cont9++) {
                        for (ArestaFluxo ee : adj[cont9] ) {
                            if(cont5 == tamanhomarcacao-1){
                                if(caminho[cont5][0] == ee.de() && caminho[cont5][1] == ee.para()){
                                    if(fluxoMenor == -100){
                                        fluxoMenor = ( ee.capacidade() - ee.fluxo()); 
                                    }else{
                                        delta = ( ee.capacidade() - ee.fluxo());
                                        if(fluxoMenor > delta){
                                            fluxoMenor = ( ee.capacidade() - ee.fluxo());
                                        }
                                    }
                                    auxiliar =caminho[cont5][0];
                                }
                            }else{
                                if(caminho[cont5][0] == ee.de() && auxiliar == ee.para()){
                                    if(fluxoMenor == -100){
                                        fluxoMenor = ( ee.capacidade() - ee.fluxo()); 
                                    }else{
                                        delta = ( ee.capacidade() - ee.fluxo());
                                        if(fluxoMenor > delta){
                                            fluxoMenor = ( ee.capacidade() - ee.fluxo());
                                        }
                                    }
                                    auxiliar =caminho[cont5][0];   
                                }
                            }  
                        } 
                    }
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /// Adicionando o valor do menor fluxo
                auxiliar =0;
                for(cont5 = (marcacao.size() -1) ; cont5 >= valorMin; cont5--){
                    for (int cont9 = 0; cont9 < (V); cont9++) {
                        for (ArestaFluxo ee : adj[cont9] ) {
                            if(cont5 == tamanhomarcacao-1){
                                if(caminho[cont5][0] == ee.de() && caminho[cont5][1] == ee.para()){
                                    if(cont10 == 0){
                                        ee.addFluxoResidualPara(ee.para(),fluxoMenor);
                                        auxiliar =caminho[cont5][0];
                                    }
                                    cont10 =1;   
                                }
                            }else{
                                if(caminho[cont5][0] == ee.de() && auxiliar == ee.para()){
                                    ee.addFluxoResidualPara(ee.para(),fluxoMenor);
                                    auxiliar =caminho[cont5][0]; 
                                }
                            }
                        } 
                    }
                }
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                marcacao.clear();
                cont5 =0;
                v=0;
                valorMarcacao=0;
                qtdMarcacao =0;
                sei =0;
            }      
        }
        System.out.println("Fluxo Maximo");
        testee();
        System.out.println("Como foi lido do TXT");
        System.out.println("");
    }
    public int valorMax(int valor){
        int aux =0;
        String valorMax;
        for (int v = 0; v < V; v++) {
            for (ArestaFluxo e : adj[v]) {
                if(e.de() == v){
                   valorMax = String.valueOf(e.para());
                   aux = Integer.valueOf(valorMax);
                   if(valor < aux ){
                       valor = aux;
                   }
                }
            }
        }
        return valor;
    }
    public int valorMin (int valor){
        int aux =0;
        String valorMin;
        for (int v = 0; v < V; v++) {
            for (ArestaFluxo e : adj[v]) {
                if(e.de() == v){
                    valorMin = String.valueOf(e.de());
                    aux = Integer.valueOf(valorMin);
                    if(valor > aux){
                        valor = aux;
                    }
                }
            }
        }
        return valor;
    }
    public void testee() {
        System.out.println("===================");
        for (int v = 0; v < V; v++) {System.out.println("");
            for (ArestaFluxo e : adj[v]) {
                if(e.de() == v){
                    System.out.println(e);
                }
            }
        }
        System.out.println("===================");
    }  
}