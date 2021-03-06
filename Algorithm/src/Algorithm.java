import Array3d.Array3d;
import java.util.Arrays;
import Sampling.Sampling;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.DoubleStream;
/*
 * @author minhpham
 */
public class Algorithm {
    
    public static void main(String[] args) {
        // Example of how to use Algorithm(...)
        int[][] Availability = {
            {1,1,2},
            {2,0,1},
            {1,0,1}
        };
        int[][] Skill = {
            {1,1,1},
            {0,1,1},
            {1,0,1}
        };       
        int[] PMax = {5,5,5};
        int[][] Needs = {
            {2,2,2},
            {2,2,2},
            {2,2,2}
        };      
        String[] EName = {"Husky", "Corgi", "Cat"};
        
        String[][][] schedule = Algorithm(Availability, Skill, PMax, Needs, EName, 0.1);

        // output
        for (int skill = 0; skill<=2; skill++){
            System.out.println("skill " + skill + ":");
            for (int shift = 0; shift<=2; shift ++){
                System.out.print("    -Shift " + shift + ":");
                System.out.println(Arrays.toString(schedule[skill][shift]));                           
            }                       
        }
        
    }

    /**
     *
     * @param Avail     Employee Availability, values 0 1 or 2
     * @param Skill     Employee Skills, values 0 or 1
     * @param PMax      Employee Hour Limits
     * @param Needs     Maximum staff needs
     * @param EName     Employee Names 
     * @param alpha     inverse weight of non preferred shift, let user pick, set 10 as default
     * @return          A 3D of String, [i][j][...] is the list of names of employee working on skill i on shift j
     */
    public static String[][][] Algorithm(
        int[][] Avail,      
        int[][] Skill,      
        int[]   PMax,       
        int[][] Needs,      
        String[] EName,     
        double alpha         
    ){        
        Sampling tool = new Sampling();
        int p = Avail.length;
        int t = Avail[0].length;
        int c = Skill[0].length;
        
        // setting weight of non-preferred shifts and create 3D availability
        double[][][] A_data = new double[p][t][c];
        double[][] Avail_double = new double[p][t];
        for (int i = 0; i < p; i++){
            for (int j = 0; j < t; j++){
                if (Avail[i][j] == 2){Avail_double[i][j] = 1/alpha;} else {Avail_double[i][j] = Avail[i][j];}
                for (int k = 0; k  < c; k++){
                    A_data[i][j][k] = Avail_double[i][j] * Skill[i][k];
                }
            }
        }
        Array3d A = new Array3d(A_data);
        
        // create loop order A[][j][k] ascending
        int[][] LoopOrder = A.LoopOrder();
        
        // Iterate
        double Sigma_best = 0;  
        Array3d X_best = new Array3d(new double[p][t][c]); //bestX
        for (int sim = 1; sim <= 100; sim++){   //100 simulations of schedule
            Array3d X = new Array3d(new double[p][t][c]);
            double sumX = -1;
            // loop till no change in X
            while (X.Sum_3d() > sumX){  
                sumX = X.Sum_3d();
                // loop through (j,k) Order
                for (int jk = 0; jk < LoopOrder.length; jk ++){
                    int j = LoopOrder[jk][0]; int k = LoopOrder[jk][1];
                    System.out.println(j + " " + k);
                    if (X.Sum_1d(1)[j][k] == Needs[j][k]){continue;}
                    double[] omega = new double[p];
                    for (int i = 0; i < p; i++){    //loop through employee to calculate preference score
                        if (j == 0){
                        omega[i] = A.value[i][j][k] 
                                / (1 + A.Sum_1d(3)[i][j])
                                * (PMax[i] - X.Sum_2d(2, 3)[i])
                                * (1 - X.Sum_1d(3)[i][j])
                                * Math.pow(1 + X.Sum_1d(3)[i][j + 1], 10)
                                ;                        
                        } else if (j == t - 1){
                        omega[i] = A.value[i][j][k] 
                                / (1 + A.Sum_1d(3)[i][j])
                                * (PMax[i] - X.Sum_2d(2, 3)[i])
                                * (1 - X.Sum_1d(3)[i][j])
                                * Math.pow(1 + X.Sum_1d(3)[i][j - 1], 10)
                                ;                        
                        } else{
                        omega[i] = A.value[i][j][k] 
                                / (1 + A.Sum_1d(3)[i][j])
                                * (PMax[i] - X.Sum_2d(2, 3)[i])
                                * (1 - X.Sum_1d(3)[i][j])
                                * Math.pow(1 + X.Sum_1d(3)[i][j + 1], 10)
                                * Math.pow(1 + X.Sum_1d(3)[i][j - 1], 10)
                                ;                        
                        }
                    }
                    if (DoubleStream.of(omega).sum() == 0){continue;}
                    // sample 1 Employee
                    int emp = tool.Sample(tool.NaturalArray(p), tool.Normalize(omega), 1)[0];
                    // assign to Matrix X
                    X.Set(emp - 1, j, k, 1);                        
                    
                }
            } // done simulation for X
            // Calculate Optimization Score
            double Sigma = X.Sum_3d() + A.Product(X).Sum_3d();
            
            // update X if better than X_best
            if (Sigma > Sigma_best){
                X_best = X;
                Sigma_best = Sigma;
            }
        }
        
        return X_best.translate_schedule(EName);
    }
            
}
