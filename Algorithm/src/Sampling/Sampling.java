/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sampling;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

/**
 *
 * @author minhpham
 */
public class Sampling {
    
    public int[] Sample(int[] items, double[] probs, int n){ // sampling with replacement
        if (items.length != probs.length){
            throw new RuntimeException("Lengths of items and probabilities not matched");
        }
        double[] Cum_dist = new double[probs.length];
        Cum_dist[0] = probs[0];
        for (int i = 1; i < items.length; i++){            
            Cum_dist[i] = Cum_dist[i - 1] + probs[i]; 
        }
        
        int [] out = new int[n];
        Random rand = new Random();        
        for (int j = 0 ; j < n; j ++){
            float u = rand.nextFloat();
            for (int i = 0; i < items.length; i++){
                if (u <= Cum_dist[i]){
                    out[j] = items[i]; break;
                }
            }
        }
        return out;
    }
    
    public int[] NaturalArray(int n){
        int[] out = new int[n];
        for (int i = 0; i < n; i++){
            out[i] = i + 1;
        }
        return out;
    }
    
    public double[] Normalize(double[] probs){
        double sum = DoubleStream.of(probs).sum();
        double[] out = new double[probs.length];
        for (int i = 0; i < probs.length; i++){
            out[i] = probs[i]/sum;
        }
        return out;
    }
}
