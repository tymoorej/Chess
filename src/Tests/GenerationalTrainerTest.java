package Tests;

import GenerationalAlgorithm.GenerationalTrainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerationalTrainerTest {

    @Test
    void getRandomCombination() {
        GenerationalTrainer generationalTrainer = new GenerationalTrainer();
        double[] values = {0.5, 0.2, -0.8, -0.75, -1};

        double valuesSum = 0;
        for (int i = 0; i < values.length; i++){
            valuesSum += values[i];
        }

        double expectedAverage = valuesSum / (double) values.length;

        double sum = 0;
        int n = 100000;

        double min = 2;
        double max = -2;
        for (int i = 0; i < n; i++){
            double value = generationalTrainer.getRandomCombination(values);
            assertTrue(value >= -1 && value <= 1);
            sum += value;
            if (value < min){
                min = value;
            }
            if (value > max){
                max = value;
            }
        }

        double average = sum / n;

        System.out.println("Expected Average = " + expectedAverage);
        System.out.println("Actual Average = " + average);
        System.out.println("Min = " + min);
        System.out.println("Max = " + max);
    }

}