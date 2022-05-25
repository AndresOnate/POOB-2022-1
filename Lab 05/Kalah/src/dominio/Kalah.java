package dominio;

/**
* Implementación de la capa de dominio del juego Kalah
 * Andrés Camilo Oñate Quimbayo, Nicolas Ariza Barbosa
 * ECI POOB 2022-1
*/
public class Kalah {
    //Caracteristicas del Juego
    private int numPlayer = 2;

    //Representación de los almacenes, casas y semillas
    private int[][] board;
    private int houses;
    private int seeds;
    private int storePlayer1;
    private int storePlayer2;
    private int totalSeeds;

    /**
     * Constructor del tablero de Kalah.
     */
    public Kalah(int numHouses, int numSeeds){
        this.houses = numHouses;
        this.seeds = numSeeds;
        initialize();

    }
    /**
     * initialize house states
     */
    public void initialize(){
        this.board = new int[this.numPlayer][this.houses];
        this.totalSeeds = this.houses* this.seeds;
        this.storePlayer1 = 0;
        this.storePlayer2 = 0;
        for (int i = 0; i < this.numPlayer; i++) {
            for (int j = 0; j < this.houses; j++) {
                this.board[i][j] = this.seeds;
            }
        }
    }

    /**
     * Returns the number of houses in the game.
     *
     * @return houses
     */
    public int getHouses() {
        return this.houses;
    }

    /**
     * Returns the default seeds home in the game.
     *
     * @return seeds
     */
    public int getSeeds() {
        return this.seeds;
    }

    /**
     * Returns the board of the game.
     *
     * @return board
     */
    public int[][] board() {
        return this.board;
    }

    /**
     * Return the seeds of a certain player's house
     *
     * @return seeds
     */
    public int getSeedsPlayer(int house, int player) {
        return this.board[player][house];
    }

    /**
     * Adds a seed to a certain house.
     */
    public void addSeed(int house, int player) {
        this.board[player][house]++;
    }

    /**
     * Adds a num of seeds to a certain house.
     */
    public void addSeeds(int house, int player, int numSeeds) {
        this.board[player][house] += numSeeds;
    }

    /**
     * Get the seeds from the opposite house
     *
     * @return seedsInHouse
     */
    public int seedsInOpositeHouse(int house, int player) {
        int seedsInHouse = 0;
        if (player == 0) {
            seedsInHouse = this.board[1][house];
        } else {
            seedsInHouse = this.board[0][house];
        }
        return seedsInHouse;
    }

    /**
     * Add seeds to player 1's store
     */
    public void setStorePlayer1(int seeds) {
        this.storePlayer1 += seeds;
    }

    /**
     * Add seeds to player 2's store
     */
    public void setStorePlayer2(int seeds) {
        this.storePlayer2 += seeds;
    }


    /**
     * Indicates if the game is over (There are no seeds in the houses)
     * @return Game is Over.
     */
    public boolean isOver() {
        boolean isEmpty1 = true;
        boolean isEmpty2 = true;
        for (int j = 0; j < this.houses; j++) {
            if (this.board[0][j] != 0) {
                isEmpty2 = false;
            }
        }
        for (int j = 0; j < this.houses; j++) {
            if (this.board[1][j] != 0) {
                isEmpty1 = false;
            }
        }
        return isEmpty2 || isEmpty1;
    }

    /**
     * Remove all the seeds of a house
     */
    public void emptyHouse(int house, int player){
        this.board[player][house] = 0;
    }

    /**
     * Get the seeds from the store player 1
     * @return seedsInStore
     */
    public int getStorePlayer1(){
        return  this.storePlayer1;
    }

    /**
     * Get the seeds from the store player 2
     * @return seedsInStore
     */
    public int getStorePlayer2(){
        return this.storePlayer2;
    }

    /**
     * Determine if a house is empty
     * @return isEmptyHouse
     */
    public boolean isEmptyHouse(int house, int player){
        return this.board[player][house] == 0;
    }

    /**
     * Set the num of Houses
     */
    public void setNumHouses(int newHouses){
        this.houses =  newHouses;
        initialize();
    }
    /**
     * Set the num of Seeds
     */
    public void setNumSeeds(int newSeeds){
        this.seeds =  newSeeds;
        initialize();
    }
}
