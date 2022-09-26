package depth_search;

public class DepthSearch {

    private int [][] adjacencyMatrix;
    private int fontVertice;
    private EnumColor [] color;
    private int [] visitInitiated;
    private int [] visitFinished;
    private Integer [] antecedent;
    private int time;

    public DepthSearch(int[][] adjacencyMatrix, Integer vertice) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.fontVertice = vertice;
        startExtesionSearchProperties();
    }

    private void startExtesionSearchProperties() {
        this.color = new EnumColor [adjacencyMatrix.length];
        this.visitInitiated = new int [adjacencyMatrix.length];
        this.visitFinished = new int [adjacencyMatrix.length];
        this.antecedent = new Integer[adjacencyMatrix.length];

        for (int i = 0; i < color.length; i++) {
            this.color[i] = EnumColor.WHITE;
            this.visitInitiated[i] = -1;
            this.visitFinished[i] = -1;
            this.antecedent[i] = -1;
        }
        this.time = 1;
        this.visit(this.adjacencyMatrix, this.fontVertice);
    }

    public void showResults(Integer v) throws Exception {
        if(v == this.fontVertice){
            System.out.print(this.fontVertice);
        } else {
            if(this.antecedent[v] == -1) {
                System.out.println("There's no way!");
            } else {
                this.showResults(antecedent[v]);
                System.out.print(" -> "+v);
            }
        }
    }

    private void visit(int[][] adjacencyMatrix, Integer verticeU) {
        this.color[verticeU] = EnumColor.GRAY;
        visitInitiated[verticeU] = this.time++;
        for (int v = 0; v < adjacencyMatrix.length; v++) {
            if(adjacencyMatrix[v][verticeU] == 1 || adjacencyMatrix[verticeU][v] == 1) {
                if(this.color[v] == EnumColor.WHITE){
                    this.antecedent[v] = verticeU;
                    this.visit(adjacencyMatrix, v);
                }
            }
        }
        this.color[verticeU] = EnumColor.BLACK;
        visitFinished[verticeU] = this.time++;
    }
    
}
