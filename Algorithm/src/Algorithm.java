import java.util.Arrays;
import org.apache.commons.math3.linear.*;

/*
 * @author minhpham
 */
public class Algorithm {
    
    public static void main(String[] args) {
        /* just an example of how to use my method
        to be replaced by your main()
        */
        
        // Receive inputs, I'm making this example of 3 skills, 3 shifts, 3 people:
        int[][] Avail = {
            {0,1,1},
            {0,0,1},
            {0,1,2}
        };
        int[][] Skill = {
            {1,1,1},
            {0,0,1},
            {1,0,0}
        };
        int[] PMax = {12, 23, 29};
        int[][] Needs = {
            {2,3,2},
            {3,2,3},
            {1,2,1}
        };
        String[] EName = {"corgi", "Husky", "German Sheperd"};
        String[] Shifts = {"shift 1", "Shift 2", "Shift 3"};
        // call the schedule method
        String[][][] output = schedule(Avail, Skill, PMax, Needs, EName, Shifts);
        
        // output
        for (int skill = 0; skill<=2; skill++){
            System.out.println("skill " + skill + ":");
            for (int shift = 0; shift<=2; shift ++){
                System.out.print("    -Shift " + shift + ":");
                System.out.println(Arrays.toString(output[skill][shift]));                           
            }                       
        }
        
    }
    
    
    // schedule algorithm
    public static String[][][] schedule(
            int[][] Avail,      // Employee Availability
            int[][] Skill,      // Employee Skills
            int[]   PMax,       // Employee Hour Limits
            int[][] Needs,      // Maximum staff needs
            String[] EName,     // Employee Names    
            String[] Shifts     // Shifts (NOTE: WHAT IS APPROPRIATE TYPE?
    ){
       
        // made-up output:
        String[][][] out = {
            //skill 1
                {   //shift 1
                    {"Corgi", "Husky"},
                    {"Corgi"},
                    {"Husky"}
                },
                {   //shift 2
                    {"Corgi", "Husky"},
                    {"Corgi", "German Sheperd"},
                    {"Husky"}                  
                },
                {   //shift 3
                    {"German Sheperd", "Husky"},
                    {"Corgi"},
                    {"Husky"}                 
                },
            //skill 2
                {   //shift 1
                    {"Corgi", "Husky"},
                    {"Corgi"},
                    {"Husky"}
                },
                {   //shift 2
                    {"Corgi", "Husky"},
                    {"Corgi", "German Sheperd"},
                    {"Husky"}                  
                },
                {   //shift 3
                    {"German Sheperd", "Husky"},
                    {"Corgi"},
                    {"Husky"}                 
                },
            //skill 3
                {   //shift 1
                    {"Corgi", "Husky"},
                    {"Corgi"},
                    {"Husky"}
                },
                {   //shift 2
                    {"Corgi", "Husky"},
                    {"Corgi", "German Sheperd"},
                    {"Husky"}                  
                },
                {   //shift 3
                    {"German Sheperd", "Husky"},
                    {"Corgi"},
                    {"Husky"}                 
                }                                 
        }
                ;
        
        
    return out; 
    }
    
            
}
