package iscteiul.ista.battleship;

/**
 * Represents a Carrack ship in the Portuguese Discoveries themed
 * Battleship game.
 *
 * <p>A Carrack occupies three consecutive positions on the board.
 * Its position and orientation are determined by the starting position
 * and the specified compass bearing.</p>
 */
public class Carrack extends Ship {

    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a new Carrack ship.
     *
     * <p>The ship occupies three consecutive positions either vertically
     * or horizontally, depending on its bearing.</p>
     *
     * @param bearing the direction in which the ship is oriented
     * @param pos the starting position of the ship
     * @throws IllegalArgumentException if the specified bearing is invalid
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the number of positions occupied by the Carrack.
     *
     * @return the size of the Carrack, which is 3
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}
