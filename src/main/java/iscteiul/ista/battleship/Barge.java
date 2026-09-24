package iscteiul.ista.battleship;

/**
 * Represents a Barge ship in the Portuguese Discoveries themed
 * Battleship game.
 *
 * <p>A Barge occupies a single position on the game board.</p>
 */
public class Barge extends Ship {

    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a new Barge ship.
     *
     * <p>The Barge is positioned at the given initial position
     * on the game board.</p>
     *
     * @param bearing the direction associated with the Barge
     * @param pos the initial position of the Barge on the board
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the number of positions occupied by the Barge.
     *
     * @return the size of the Barge, which is 1
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}

