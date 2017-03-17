package nl.knapper_development.math.algorithms;/**
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

import jdk.nashorn.internal.ir.annotations.Ignore;
import nl.knapper_development.math.LiveAlgorithm;

import java.util.ArrayList;

@Deprecated
public class MergeSort extends LiveAlgorithm {

    private int stepCounter = 0;
    private int mergecounter = 1;
    private ArrayList<ArrayList<Integer>> dataLists;
    private ArrayList<Integer> working;

    @Ignore
    public MergeSort(ArrayList<Integer> dataSet) {
        super(dataSet, 1000);
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {
        if (stepCounter < (dataSet.size()) % 2) {
            if (working.size() < mergecounter) {

            } else {

            }
        } else {

        }

        return mergeAll();
    }

    private ArrayList<Integer> mergeAll() {
        ArrayList<Integer> merged = new ArrayList<>();

        System.out.println("DATALISTS: " + dataLists);
        for (ArrayList<Integer> list : dataLists) {
            merged.addAll(list);
        }
        System.out.println("TOTALSET: " + merged);
        return merged;
    }
}
