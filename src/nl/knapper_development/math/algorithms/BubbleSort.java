package nl.knapper_development.math.algorithms;

import nl.knapper_development.math.StepAlgorithm;

import java.util.ArrayList;

public class BubbleSort extends StepAlgorithm {

    private int sortCounter = 0;
    private int swapCounter = 0;
    private int dataSetSize = 0;

    public BubbleSort(ArrayList<Integer> dataSet) {
        super(dataSet);
        dataSetSize = dataSet.size();
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {

        if (sortCounter < (dataSetSize-1)){
            if (swapCounter < (dataSetSize- sortCounter -1)){
                if (dataSet.get(swapCounter) > dataSet.get(swapCounter +1)) {
                    addComparison();
                    swap(dataSet, swapCounter, swapCounter +1);
                }
                swapCounter++;
            }
            else {
                swapCounter = 0;
                sortCounter++;
            }
        }

        return dataSet;
    }
}
