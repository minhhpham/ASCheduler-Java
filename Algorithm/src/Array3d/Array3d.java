package Array3d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.stream.IntStream;

/*
 *
 * @author minhpham
 */
public class Array3d {

    public double[][][] value;
    public int dim1, dim2, dim3;
  
    public Array3d(double[][][] x){
        value = x;
        this.dim1 = value.length;
        this.dim2 = value[0].length;
        this.dim3 = value[0][0].length;
    }
    public void Set(int i, int j, int k, double val){
        value[i][j][k] = val;
    }
    public double Get(int i, int j, int k){
        return value[i][j][k];
    }
    
    public double[][] Sum_1d(int d){ //summing across 1 dimension, returning array of 2 other dimensions
        double temp;
        if (d == 1){
            double[][] out = new double[dim2][dim3];
            for (int j = 0; j < dim2; j++){
                for (int k = 0; k < dim3; k++){
                    temp = 0;
                    for (int i = 0; i < dim1; i++){
                        temp = temp + value[i][j][k];
                    }
                    out[j][k] = temp;
                }
            }
            return out;
        }
        if (d == 2){
            double[][] out = new double[dim1][dim3];
            for (int i = 0; i < dim1; i++){
                for (int k = 0; k < dim3; k++){
                    temp = 0;
                    for (int j = 0; j < dim2; j++){
                        temp = temp + value[i][j][k];
                    }
                    out[i][k] = temp;
                }
            }
            return out;
        }       
        if (d == 3){
            double[][] out = new double[dim1][dim2];
            for (int i = 0; i < dim1; i++){
                for (int j = 0; j < dim2; j++){
                    temp = 0;
                    for (int k = 0; k < dim3; k++){
                        temp = temp + value[i][j][k];
                    }
                    out[i][j] = temp;
                }
            }
            return out;
        }        
        return null;
    }
    
    public double[] Sum_2d(int d1, int d2){ //summing across 2 dimension, returning array of the other dimensions
        double[][] sum1;
        if (d1 == 1 & d2 == 2){
            double[] out = new double[dim3];
            sum1 = this.Sum_1d(d1);
            for (int k = 0; k < dim3; k++){
                for (int j = 0; j < dim2; j++){
                    out[k] = out[k] + sum1[j][k];
                }
            }
            return out;
        }
        if (d1 == 1 & d2 == 3){
            double[] out = new double[dim2];
            sum1 = this.Sum_1d(d1);
            for (int j = 0; j < dim2; j++){
                for (int k = 0; k < dim3; k++){
                    out[j] = out[j] + sum1[j][k];
                }
            }
            return out;
        }        
        if (d1 == 2 & d2 == 3){
            double[] out = new double[dim1];
            sum1 = this.Sum_1d(d1);
            for (int i = 0; i < dim1; i++){
                for (int k = 0; k < dim3; k++){
                    out[i] = out[i] + sum1[i][k];
                }
            }
            return out;
        }      
        return null;
    }

    public double Sum_3d(){
        double out = 0;
        for (int i = 0; i < dim1; i++){
            for (int j = 0; j < dim2; j++){
                for (int k = 0; k < dim3; k++){
                    out = out + value[i][j][k];
                }
            }
        }
        return out;
    }
    
    public int[][] LoopOrder(){
        double[][] Sum1 = Sum_1d(1);
        int[][] Order = new int[dim2*dim3][2];
        
        double[] Sum1_arr = new double[dim2*dim3];
        int[] j_arr = new int[dim2*dim3]; 
        int[] k_arr = new int[dim2*dim3];
        
        int i = 0;
        // create array of sum1 (sum across dim1), j, and k
        for (int j = 0 ; j < dim2; j++){
            for (int k = 0; k < dim3; k++){
                Sum1_arr[i] = Sum1[j][k];
                j_arr[i] = j; k_arr[i] = k;
                i = i + 1;
            }
        }
        Integer[] i_index = SortedIndex(Sum1_arr);        
        for (i = 0; i < Sum1_arr.length; i++){
            Order[i][0] = j_arr[i_index[i]];
            Order[i][1] = k_arr[i_index[i]];
        }       
        return Order;
    }
   
    private Integer[] SortedIndex(double[] array){
        Integer[] indices = new Integer[array.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, new Comparator<Integer>() {

        public int compare(Integer i1, Integer i2) {
                int out = 0;
                if (array[i1] < array[i2]){out = -1;}
                if (array[i1] == array[i2]){out = 0;}
                if (array[i1] > array[i2]){out = 1;}  
                return out;
            }
        });
        return indices;
    }
    
    public Array3d Product(Array3d X){
        double[][][] OutData = new double[dim1][dim2][dim3];
        for (int i = 0; i < dim1; i++){
            for (int j = 0; j < dim2; j++){
                for (int k = 0; k < dim3; k++){
                    OutData[i][j][k] = this.value[i][j][k] * X.value[i][j][k];
                }
            }
        }
        return new Array3d(OutData);
    }
    
    public String[][][] translate_schedule( // Translating from binary matrix to schedule
            String[] EName
    ){
        ArrayList<String>[][] schedule = (ArrayList<String>[][]) new ArrayList[dim3][dim2];

        if (EName.length != dim1){
            throw new RuntimeException("Lengths of Employee Names and 3DMatrix do not match");
        }
        
        for (int k = 0; k < dim3; k ++){
            for (int j = 0; j < dim2; j++){
                schedule[k][j] = new ArrayList<String>();
                for (int i = 0; i < dim1; i++){
                    if (value[i][j][k] == 1){ 
                        schedule[k][j].add(EName[i]) ;
                    }
                }
            }
        }
        
        String[][][] out = new String[dim3][dim2][];
        for (int k = 0; k < dim3; k++){
            for (int j = 0; j < dim2; j ++){
                ArrayList<String> people = schedule[k][j];
                out[k][j] = people.toArray(new String[people.size()]);
            }
        }
        return out;
    }
}
