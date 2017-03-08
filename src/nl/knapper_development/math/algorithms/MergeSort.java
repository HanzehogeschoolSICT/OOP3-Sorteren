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
public class MergeSort extends Algorithm {

    private int lowest;
    private int highest;
    private int pivot;



    public MergeSort(ArrayList<Integer> dataSet) {
        super(dataSet);
        this.lowest = 0;
        this.highest = (dataSet.size() -1 );
        this.pivot = dataSet.get(lowest+((highest-lowest)/2));
    }

    @Override
    protected ArrayList<Integer> loop(ArrayList<Integer> dataSet) {





        return null;
    }

}
