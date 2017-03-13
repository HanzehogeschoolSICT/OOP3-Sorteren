package nl.knapper_development.math;

/*
    Copyright (C) 3/8/17  Hanze Hogeschool ITV2D

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import nl.knapper_development.math.algorithms.BubbleSort;
import nl.knapper_development.math.algorithms.InsertionSort;
import nl.knapper_development.math.algorithms.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        ArrayList<Integer> dataSet1 = new ArrayList<>(Arrays.asList(23, 452, 12, 22, 2, 1, 86, 45));
        ArrayList<Integer> dataSet2 = new ArrayList<>(Arrays.asList(23, 452, 12, 22, 2, 1, 86, 45));
        ArrayList<Integer> dataSet3 = new ArrayList<>(Arrays.asList(23, 452, 12, 22, 2, 1, 86, 45));

        BubbleSort bs = new BubbleSort(dataSet1);
        bs.setObserver(new StepAlgorithm.Observer() {
            @Override
            public void onLoop() {
            }

            @Override
            public void onLoopDone(ArrayList<Integer> currentDataset) {
                System.out.println(currentDataset);
            }

            @Override
            public void onFinished() {
                System.out.println("BS DONE, " + bs.getComparisons() + " comparisons, " + bs.getNumberOfStepsTaken() + " steps.\n----------");
            }
        });


        QuickSort qs = new QuickSort(dataSet2);
        qs.setObserver(new StepAlgorithm.Observer() {
            @Override
            public void onLoop() {

            }

            @Override
            public void onLoopDone(ArrayList<Integer> currentDataset) {
                System.out.println(currentDataset);
            }

            @Override
            public void onFinished() {
                System.out.println("QS DONE, " + qs.getComparisons() + " comparisons, " + qs.getNumberOfStepsTaken() + " steps.\n----------");
            }
        });

        InsertionSort is = new InsertionSort(dataSet3);
        is.setObserver(new StepAlgorithm.Observer() {
            @Override
            public void onLoop() {

            }

            @Override
            public void onLoopDone(ArrayList<Integer> currentDataset) {
                System.out.println(currentDataset);
            }

            @Override
            public void onFinished() {
                System.out.println("IS DONE, " + is.getComparisons() + " comparisons, " + is.getNumberOfStepsTaken() + " steps.\n----------");
            }
        });


        bs.run();
        qs.run();
        is.run();
    }

}
