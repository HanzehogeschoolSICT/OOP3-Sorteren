package nl.knapper_development.math;

/**
 * Copyright (C) 3/17/17 By joris
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
public class Swap {

    private int firstOriginalIndex;
    private int firstNewIndex;
    private int secondOriginalIndex;
    private int secondNewIndex;

    public Swap(int firstOriginalIndex, int firstNewIndex, int secondOriginalIndex, int secondNewIndex) {
        this.firstOriginalIndex = firstOriginalIndex;
        this.firstNewIndex = firstNewIndex;
        this.secondOriginalIndex = secondOriginalIndex;
        this.secondNewIndex = secondNewIndex;
    }

    @Override
    public String toString() {
        return String.format("SWAP, 1st: %d -> %d, 2nd: %d -> %d", firstOriginalIndex, firstNewIndex, secondOriginalIndex, secondNewIndex);
    }

    public int getFirstOriginalIndex() {
        return firstOriginalIndex;
    }

    public int getFirstNewIndex() {
        return firstNewIndex;
    }

    public int getSecondOriginalIndex() {
        return secondOriginalIndex;
    }

    public int getSecondNewIndex() {
        return secondNewIndex;
    }
}
