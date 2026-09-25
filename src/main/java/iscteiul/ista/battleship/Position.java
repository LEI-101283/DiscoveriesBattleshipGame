/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Represents a single position (cell) on the Battleship game board.
     *
     * A position is identified by its row and column indices. It also
     * keeps track of two independent states: whether it is occupied by
     * a ship and whether it has been hit by a shot.
     *
     * @see IPosition
     */
    public Position(int row, int column) {

        /** The row index of this position. */
        this.row = row;

        /** The column index of this position. */
        this.column = column;

        /** Whether this position is occupied by a ship. */
        this.isOccupied = false;

        /** Whether this position has been hit by a shot. */
        this.isHit = false;
    }

    /**
     * Returns the row index of this position.
     *
     * @return the row index
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of this position.
     *
     * @return the column index
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Returns a hash code for this position, based on its row,
     * column and current state.
     *
     * @return the hash code of this position
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compares this position with another object for equality.
     * <p>
     * Two positions are considered equal if they have the same row
     * and column, regardless of their occupied or hit state.
     * </p>
     *
     * @param otherPosition the object to compare with
     * @return {@code true} if the other object is a position with the
     *         same row and column, {@code false} otherwise
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Checks whether this position is adjacent to another position.
     * <p>
     * Two positions are adjacent if the absolute difference between
     * their rows is at most 1 and the absolute difference between
     * their columns is at most 1 (i.e. they are neighbours, including
     * diagonals).
     * </p>
     *
     * @param other the other position to compare with
     * @return {@code true} if the positions are adjacent,
     *         {@code false} otherwise
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marks this position as occupied by a ship.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Marks this position as hit by a shot.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Checks whether this position is occupied by a ship.
     *
     * @return {@code true} if the position is occupied,
     *         {@code false} otherwise
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Checks whether this position has been hit by a shot.
     *
     * @return {@code true} if the position has been hit,
     *         {@code false} otherwise
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a textual representation of this position, including
     * its row and column.
     *
     * @return a string representing the position
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
