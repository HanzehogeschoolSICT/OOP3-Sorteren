package nl.knapper_development.math;/*
    Copyright (C) 3/13/17  Hanze Hogeschool ITV2D

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

public abstract class Algorithm extends Thread {

    private ArrayList<Integer> dataSet;
    private long interval;
    private Observer observer;

    private int comparisons = 0;
    private int numberOfStepsTaken = 0;
    private boolean paused = false;

    public Algorithm(long interval) {
        this.interval = interval;
        this.dataSet = new ArrayList<>();
    }

    public Algorithm(ArrayList<Integer> dataSet, long interval) {
        this.dataSet = dataSet;
        this.interval = interval;
    }

    public Algorithm(ArrayList<Integer> dataSet, long interval, Observer observer) {
        this.dataSet = dataSet;
        this.interval = interval;
        this.observer = observer;
    }

    protected abstract ArrayList<Integer> loop(ArrayList<Integer> dataSet);

    public void swap(ArrayList<Integer> dataSet, int pos1, int pos2){
        int swap = dataSet.get(pos1);
        dataSet.set(pos1, dataSet.get(pos2));
        dataSet.set(pos2, swap);
    }

    public boolean isSorted() {
        boolean sorted = true;
        for (int count = 1; count < this.dataSet.size(); count++) {
            if (dataSet.get(count-1).compareTo(dataSet.get(count)) > 0) sorted = false;
        }
        return sorted;
    }

    public void addComparison(){
        comparisons++;
    }

    private void doStep() {
        onLoop();
        numberOfStepsTaken++;
        ArrayList<Integer> loopResult = loop(this.dataSet);
        onLoopDone(loopResult);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        super.start();
        while (!isSorted()) {
            if (!paused) {
                doStep();
            }
        }

        onFinished(this);
    }

    public synchronized void pause() {
        this.paused = true;
    }

    public synchronized void unpause() {
        this.paused = false;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getNumberOfStepsTaken() {
        return numberOfStepsTaken;
    }

    private void onLoop() {
        if (this.observer != null) this.observer.onLoop();
    }

    private void onLoopDone(ArrayList<Integer> currentDataset) {
        if (this.observer != null) this.observer.onLoopDone(currentDataset);
    }

    private void onFinished(Algorithm algorithm) {
        if (this.observer != null) this.observer.onFinished(algorithm);
    }

    //<editor-fold desc="Observer">
    public interface Observer {

        void onLoop();

        void onLoopDone(ArrayList<Integer> currentDataset);

        void onFinished(Algorithm thisAlgorithm);

    }

    //</editor-fold>

}
