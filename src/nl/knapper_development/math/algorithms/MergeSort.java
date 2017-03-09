package nl.knapper_development.math.algorithms;
import nl.knapper_development.math.Algorithm;
import java.util.ArrayList;
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
public class MergeSort extends Algorithm {

    private ArrayList<ArrayList<Integer>> dataLists;
    private boolean firstStepDone = false;
    private int differentListLocation = 0;
    private Random random = new Random();

    public MergeSort(ArrayList<Integer> dataSet) {
        super(dataSet);
        dataLists = new ArrayList<>();
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {

        if (dataLists.size() != 0) {
            if (differentListLocation < dataLists.size()) {
                ArrayList<Integer> list = dataLists.get(differentListLocation);
                seperator(list);
                dataLists.remove(list);
            }
        } else {
            if (!firstStepDone) {
                seperator(dataSet);
                firstStepDone = true;
            }
            differentListLocation = 0;
        }

        return mergeAll();
    }

    private void seperator(ArrayList<Integer> dataset) {
        ArrayList<Integer> lowerSide = new ArrayList<>();
        ArrayList<Integer> higherSide = new ArrayList<>();
        int higestPos = (dataset.size() - 1);

        if (higestPos > 0){
            int pivot = dataset.get(random.nextInt(higestPos));

            for (int i = 0; i <= higestPos; i++) {
                int number = dataset.get(i);
                if (number != pivot) {
                    addComparison();
                    if (number > pivot) {
                        higherSide.add(number);
                    } else {
                        lowerSide.add(number);
                    }
                }
            }

            lowerSide.add(pivot);
            System.out.println("LOWERSIDE:" + lowerSide);
            System.out.println("HIGHERSIDE: " + higherSide);
            dataLists.add(lowerSide);
            dataLists.add(higherSide);
        } else {
            lowerSide.add(dataset.get(0));

        }
    }

    private ArrayList<Integer> mergeAll() {
        ArrayList<Integer> merged = new ArrayList<>();

        for (ArrayList<Integer> list : dataLists) {
            merged.addAll(list);
        }

        return merged;
    }

}
