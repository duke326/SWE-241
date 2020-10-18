package exercise3;

public class MainMethod {

        public static void main(String[] args) {
            int[][] graph = new int[][]{
                    {0,1,1,0,0,1,1},
                    {1,0,0,1,1,0,1},
                    {1,0,0,1,0,1,0},
                    {0,1,1,0,1,0,0},
                    {0,1,0,1,0,0,1},
                    {1,0,1,0,0,0,0},
                    {1,1,0,0,1,0,0}
            };
            AMatrixToAList Alist=new AMatrixToAList();
            Alist.convert(graph);

            AListToIMatrix Imatrix=new AListToIMatrix();
            Imatrix.convert(Alist.map);

            ImatrixToAList temp=new ImatrixToAList();
            temp.convert(Imatrix.incidenceMatrix);
        }

}
