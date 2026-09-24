package iscteiul.ista.battleship;

/**
 * Represents a Caravel ship in the Portuguese Discoveries themed
 * Battleship game.
 *
 * <p>A Caravel occupies two consecutive positions on the game board.
 * Its orientation is determined by the specified compass bearing.</p>
 */
public class Caravel extends Ship {

    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Creates a new Caravel ship.
     *
     * <p>The Caravel is positioned starting at the given position and
     * occupies two consecutive positions horizontally or vertically,
     * depending on its bearing.</p>
     *
     * @param bearing the direction in which the Caravel is oriented
     * @param pos the initial position of the Caravel on the board
     * @throws NullPointerException if the specified bearing is null
     * @throws IllegalArgumentException if the specified bearing is invalid
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Returns the number of positions occupied by the Caravel.
     *
     * @return the size of the Caravel, which is 2
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
