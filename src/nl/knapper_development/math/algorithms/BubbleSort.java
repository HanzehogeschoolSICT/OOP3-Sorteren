package nl.knapper_development.math.algorithms;

import nl.knapper_development.math.LiveAlgorithm;

import java.util.ArrayList;

public class BubbleSort extends LiveAlgorithm {

    private int sortCounter = 0;
    private int swapCounter = 0;
    private int dataSetSize = 0;

    public BubbleSort(ArrayList<Integer> dataSet, long interval) {
        super(dataSet, interval);
        dataSetSize = dataSet.size();
    }

    public BubbleSort(ArrayList<Integer> dataSet, long interval, Observer observer) {
        super(dataSet, interval, observer);
        this.dataSetSize = dataSet.size();
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {

        //TODO sometimes outputs of two steps are identical...

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
