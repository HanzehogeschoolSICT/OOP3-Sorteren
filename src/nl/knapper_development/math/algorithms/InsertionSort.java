package nl.knapper_development.math.algorithms;

import nl.knapper_development.math.LiveAlgorithm;

import java.util.ArrayList;

/**
 * Copyright (C) 3/8/17 By joris
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

public class InsertionSort extends LiveAlgorithm {

    private int singleSortCounter = 1;
    private int masterSortCounter = 1;
    private int insertionCounter = 0;
    private int dataSetSize;

    public InsertionSort(ArrayList<Integer> dataSet, long interval) {
        super(dataSet, interval);
        dataSetSize = dataSet.size();
    }

    public InsertionSort(ArrayList<Integer> dataSet, long interval, Observer observer) {
        super(dataSet, interval, observer);
        dataSetSize = dataSet.size();
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {

        if (masterSortCounter < dataSetSize) {
            addComparison();
            if ((insertionCounter >= 0) && (dataSet.get(singleSortCounter) < dataSet.get(insertionCounter))) {
                swap(dataSet, singleSortCounter, insertionCounter);
                insertionCounter--;
                singleSortCounter--;
            }
            else {
                masterSortCounter++;
                singleSortCounter = masterSortCounter;
                insertionCounter = (masterSortCounter - 1);
            }
        }
        return dataSet;
    }
}
