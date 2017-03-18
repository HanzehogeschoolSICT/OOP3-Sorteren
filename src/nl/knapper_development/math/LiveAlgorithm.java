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

public abstract class LiveAlgorithm implements Runnable {

    private ArrayList<Integer> dataSet;
    private long interval;
    private Observer observer;

    private int comparisons = 0;
    private int numberOfStepsTaken = 0;
    private boolean paused = false;

    public LiveAlgorithm(long interval) {
        this.interval = interval;
        this.dataSet = new ArrayList<>();
    }

    public LiveAlgorithm(ArrayList<Integer> dataSet, long interval) {
        this.dataSet = dataSet;
        this.interval = interval;
    }

    public LiveAlgorithm(ArrayList<Integer> dataSet, long interval, Observer observer) {
        this.dataSet = dataSet;
        this.interval = interval;
        this.observer = observer;
    }

    protected abstract ArrayList<ArrayList<Integer>> loop(ArrayList<Integer> dataSet);

    public ArrayList<Integer> swap(ArrayList<Integer> dataSet, ArrayList<Integer> swappables) {
        onPreSwap(dataSet, swappables);

        int swap = dataSet.get(swappables.get(0));
        dataSet.set(swappables.get(0), dataSet.get(swappables.get(1)));
        dataSet.set(swappables.get(1), swap);

        return new ArrayList<>(swappables);
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
        ArrayList<ArrayList<Integer>> loopResult = loop(this.dataSet);
        onLoopDone(loopResult.get(0), loopResult.get(1));
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!isSorted()) {
            synchronized (this) {
                if (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            doStep();
        }

        onFinished(this);
    }

    public synchronized void pause() {
        this.paused = true;
    }

    public synchronized void unpause() {
        this.paused = false;
        this.notifyAll();
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getNumberOfStepsTaken() {
        return numberOfStepsTaken;
    }

    public void setDataSet(ArrayList<Integer> dataSet) {
        this.dataSet = dataSet;
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

    private void onPreSwap(ArrayList<Integer> dataSet, ArrayList<Integer> swappables) {
        if (this.observer != null) this.observer.onPreSwap(dataSet, swappables);
    }

    private void onLoopDone(ArrayList<Integer> currentDataset, ArrayList<Integer> changedIndexes) {
        if (this.observer != null) this.observer.onLoopDone(currentDataset, changedIndexes);
    }

    private void onFinished(LiveAlgorithm liveAlgorithm) {
        if (this.observer != null) this.observer.onFinished(liveAlgorithm);
    }

    //<editor-fold desc="Observer">
    public interface Observer {

        void onLoop();

        void onPreSwap(ArrayList<Integer> dataSet, ArrayList<Integer> toBeSwapped);

        void onLoopDone(ArrayList<Integer> currentDataset, ArrayList<Integer> changedIndexes);

        void onFinished(LiveAlgorithm thisLiveAlgorithm);

    }

    //</editor-fold>

}
