/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a generic ship in the Battleship game.
 *
 * This abstract class serves as the base for all ship types
 * (Galleon, Frigate, Carrack, Caravel and Barge), defining the
 * common behaviour shared by all of them: position, bearing,
 * size, occupied coordinates and state (floating or sunk).
 */

public abstract class Ship implements IShip {

    /** Name that identifies a Galleon-type ship. */
    private static final String GALEAO = "galeao";

    /** Name that identifies a Frigate-type ship. */
    private static final String FRAGATA = "fragata";

    /** Name that identifies a Carrack-type ship. */
    private static final String NAU = "nau";

    /** Name that identifies a Caravel-type ship. */
    private static final String CARAVELA = "caravela";

    /** Name that identifies a Barge-type ship. */
    private static final String BARCA = "barca";

    /**
     * Builds and returns a concrete ship instance according to the
     * given ship kind.
     *
     * @param shipKind the type of ship to create (e.g. "galeao", "fragata")
     * @param bearing  the ship's bearing (horizontal or vertical)
     * @param pos      the ship's starting position (top-left corner)
     * @return an instance of the corresponding ship, or {@code null} if
     *         the ship kind is not recognised
     */


    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** The ship's category (type). */
    private String category;

    /** The ship's bearing on the board. */
    private Compass bearing;

    /** The ship's starting position. */
    private IPosition pos;

    /** The list of all positions occupied by the ship. */
    protected List<IPosition> positions;


    /**
     * Creates a new ship with the given category, bearing and starting
     * position.
     *
     * @param category the ship's category
     * @param bearing  the ship's bearing
     * @param pos      the ship's starting position
     */

    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Devolve a categoria do navio.
     *
     * @return a categoria do navio
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Devolve a lista de posições ocupadas pelo navio.
     *
     * @return a lista de posições do navio
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Returns the ship's category.
     *
     * @return the ship's category
     */

    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Returns the ship's bearing.
     *
     * @return the ship's bearing
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Indicates whether the ship is still floating, i.e. whether it
     * still has at least one position that has not been hit.
     *
     * @return {@code true} if the ship is still floating,
     *         {@code false} otherwise
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Returns the topmost row occupied by the ship.
     *
     * @return the index of the ship's top row
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Returns the bottommost row occupied by the ship.
     *
     * @return the index of the ship's bottom row
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Returns the leftmost column occupied by the ship.
     *
     * @return the index of the ship's left column
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Returns the rightmost column occupied by the ship.
     *
     * @return the index of the ship's right column
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Checks whether the ship occupies a given position.
     *
     * @param pos the position to check
     * @return {@code true} if the ship occupies the given position,
     *         {@code false} otherwise
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Checks whether the ship is too close to another ship (i.e.
     * whether any of its positions is adjacent to any position of
     * the other ship).
     *
     * @param other the other ship to compare with
     * @return {@code true} if the ships are too close,
     *         {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Checks whether the ship is too close to a given position.
     *
     * @param pos the position to check
     * @return {@code true} if any of the ship's positions is adjacent
     *         to the given position, {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Shoots at a given position. If the ship occupies that position,
     * it is marked as hit.
     *
     * @param pos the position where the shot was fired
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Returns a textual representation of the ship, including its
     * category, bearing and position.
     *
     * @return a string representing the ship
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
