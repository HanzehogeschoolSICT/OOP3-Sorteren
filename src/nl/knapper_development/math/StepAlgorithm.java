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

import java.util.ArrayList;

@Deprecated
public abstract class StepAlgorithm {

    private ArrayList<Integer> dataSet;
    private ArrayList<ArrayList<Integer>> history;
    private Condition condition;
    private Observer observer;
    private int comparisons = 0;
    private int numberOfStepsTaken = 0;
    public StepAlgorithm(ArrayList<Integer> dataSet) {
        this.dataSet = dataSet;
        this.history = new ArrayList<>();
    }

    protected abstract ArrayList<Integer> loop(ArrayList<Integer> dataSet);

    public void run() {
        while (!isSorted()) {
            onLoop();
            numberOfStepsTaken++;
            ArrayList<Integer> loopResult = loop(this.dataSet);
            onLoopDone(loopResult);
            update(loopResult);
        }

        onFinished();
    }

    public void swap(ArrayList<Integer> dataSet, int pos1, int pos2){
        int swap = dataSet.get(pos1);
        dataSet.set(pos1, dataSet.get(pos2));
        dataSet.set(pos2, swap);
    }

    public void runWithCondition(Condition condition) {
        this.condition = condition;
        while (!condition.criteria()) {
            onLoop();
            ArrayList<Integer> loopResult = loop(this.dataSet);
            onLoopDone(loopResult);
            update(loopResult);
        }

        onFinished();
    }

    public boolean isSorted() {
        boolean sorted = true;
        for (int count = 1; count < this.dataSet.size(); count++) {
            if (dataSet.get(count-1).compareTo(dataSet.get(count)) > 0) sorted = false;
        }

        return sorted;
    }

    private void update(ArrayList<Integer> dataSet) {
        updateHistory(dataSet);
        updateDataset(dataSet);
    }

    private void updateDataset(ArrayList<Integer> dataSet) {
        this.dataSet = dataSet;
    }

    private void updateHistory(ArrayList<Integer> entry) {
        this.history.add(entry);
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void unsetObserver() {
        this.observer = null;
    }

    private void onLoop() {
        if (this.observer != null) this.observer.onLoop();
    }

    private void onLoopDone(ArrayList<Integer> currentDataset) {
        if (this.observer != null) this.observer.onLoopDone(currentDataset);
    }

    private void onFinished() {
        if (this.observer != null) this.observer.onFinished();
    }

    public void addComparison(){
        comparisons++;
    }

    //<editor-fold desc="Getters and Setters">
    public ArrayList<Integer> getDataSet() {
        return dataSet;
    }

    //</editor-fold>

    public ArrayList<ArrayList<Integer>> getHistory() {
        return history;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getNumberOfStepsTaken() {
        return numberOfStepsTaken;
    }

    //<editor-fold desc="Observer">
    public interface Observer {

        void onLoop();

        void onLoopDone(ArrayList<Integer> currentDataset);

        void onFinished();

    }

    //</editor-fold>

}
