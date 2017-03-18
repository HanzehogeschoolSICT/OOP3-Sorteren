package nl.knapper_development.math.algorithms;

import nl.knapper_development.math.LiveAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (C) 3/8/17 By Joris
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class QuickSort extends LiveAlgorithm {

    private ArrayList<ArrayList<Integer>> dataLists;
    private boolean firstStepDone = false;
    private int differentListLocation = 0;
    private Random random = new Random();

    public QuickSort(ArrayList<Integer> dataSet, long interval) {
        super(dataSet, interval);
        dataLists = new ArrayList<>();
    }

    public QuickSort(ArrayList<Integer> dataSet, long interval, Observer observer) {
        super(dataSet, interval, observer);
        dataLists = new ArrayList<>();
    }

    @Override
    protected ArrayList<ArrayList<Integer>> loop(ArrayList<Integer> dataSet) {
        int pivotIndex = -1;

        if (dataLists.size() != 0) {
            if (differentListLocation < dataLists.size()) {
                ArrayList<Integer> list = dataLists.get(differentListLocation);
                pivotIndex = seperator(list);
                dataLists.remove(list);
            }
        } else {
            if (!firstStepDone) {
                pivotIndex = seperator(dataSet);
                firstStepDone = true;
            }
            differentListLocation = 0;
        }

        ArrayList<Integer> newDataSet = mergeAll();
        super.setDataSet(newDataSet);

        return new ArrayList<>(Arrays.asList(newDataSet, new ArrayList<>(Arrays.asList(pivotIndex))));
    }

    private int seperator(ArrayList<Integer> dataset) {
        ArrayList<Integer> lowerSide = new ArrayList<>();
        ArrayList<Integer> higherSide = new ArrayList<>();
        int higestPos = (dataset.size() - 1);
        int numberOfPivots = 0;

        if (higestPos > 0) {
            int pivotIndex = random.nextInt(higestPos);
            int pivot = dataset.get(pivotIndex);
            numberOfPivots++;

            for (int i = 0; i <= higestPos; i++) {
                int number = dataset.get(i);
                addComparison();
                if (number > pivot) {
                    higherSide.add(number);
                } else if (number < pivot) {
                    lowerSide.add(number);
                } else if (number == pivot) {
                    numberOfPivots++;
                }
            }

            for (int i = 1; i < numberOfPivots; i++) {
                lowerSide.add(pivot);
            }

            dataLists.add(lowerSide);
            if (higherSide.size() > 0) {
                dataLists.add(higherSide);
            }
        } else {
            lowerSide.add(dataset.get(0));
            dataLists.add(lowerSide);

        }
        return (lowerSide.size() - 1);
    }

    private ArrayList<Integer> mergeAll() {
        ArrayList<Integer> merged = new ArrayList<>();

        for (ArrayList<Integer> list : dataLists) {
            merged.addAll(list);
        }
        System.out.println("MERGED: " + merged);
        return merged;
    }

}
