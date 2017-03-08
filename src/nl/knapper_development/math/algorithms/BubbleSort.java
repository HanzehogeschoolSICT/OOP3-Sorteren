package nl.knapper_development.math.algorithms;

import nl.knapper_development.math.Algorithm;

import java.util.ArrayList;

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
public class BubbleSort extends Algorithm {

    private int loop1 = 0;
    private int loop2 = 0;
    private int dataSetSize = 0;

    public BubbleSort(ArrayList<Integer> dataSet) {
        super(dataSet);
        dataSetSize = dataSet.size();
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {

        if (loop1 < (dataSetSize-1)){
            if (loop2 < (dataSetSize-loop1-1)){
                if (dataSet.get(loop2) > dataSet.get(loop2+1)) {
                    swap(dataSet, loop2, loop2+1);
                }
                loop2++;
            }
            else {
                loop2 = 0;
                loop1++;
            }
        }
        return dataSet;
    }

    private void swap(ArrayList<Integer> dataSet, int pos1, int pos2){
        int swap = dataSet.get(pos1);
        dataSet.set(pos1, dataSet.get(pos2));
        dataSet.set(pos2, swap);
    }
}
