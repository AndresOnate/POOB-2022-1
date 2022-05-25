package dominio;

/**
 * Implementación de la capa de dominio del juego Kalah
 * Andrés Camilo Oñate Quimbayo, Nicolas Ariza Barbosa
 * ECI POOB 2022-1
 */

public class KalahGame {
    private int player1 = 1;
    private int player2 = 0;
    private Kalah kalah ;
    private int currentPlayer;
    private boolean specialCase =  false;
    private boolean isOver = false;

    public KalahGame(int numHouses, int numSeeds) throws KalahGameException {
        if(numHouses < 1) throw new KalahGameException(KalahGameException.RANGE_HOUSES);
        if(numSeeds < 0) throw new KalahGameException(KalahGameException.RANGE_SEEDS);
        kalah =  new Kalah(numHouses,numSeeds);
        this.currentPlayer = 1;
    }

    /**
     * Change the current player
     */
    public void changePlayer(){
        if (!this.specialCase){
            int nextPlayer = currentPlayer + 1;
            if(nextPlayer > 1)
                nextPlayer = 0;
            this.currentPlayer = nextPlayer;
        }else {
            this.specialCase = false;
        }
    }

    /**
     * Get the current player
     * @return currentPlayer
     */
    public int player(){
        return this.currentPlayer;
    }

    /**
     * Get the waiting player
     * @return currentPlayer
     */
    public int  getWaitingPlayer(){
        if(this.currentPlayer == 1){
            return 0;
        }
        return  1;
    }

    /**
     * Method in charge of the characteristic of the game.
     */
    public void play(int house, int  player) throws KalahGameException{
        if(player != this.currentPlayer) throw new KalahGameException(KalahGameException.PLAYER_TURN);
        if(house < 0 || house > kalah.getHouses() - 1) throw new KalahGameException(KalahGameException.RANGE_HOUSES);
        int seedsHouse = kalah.getSeedsPlayer(house,player);
        kalah.emptyHouse(house,player);
        if(!this.isOver) spreadSeeds(house,player, seedsHouse);
        theGameIsOver();
    }

    /**
     * Add seeds to the current player warehouse
     */
    public void addStorePlayer(int player){
        if(player == 1){
            kalah.setStorePlayer1(1);
        }else{
            kalah.setStorePlayer2(1);
        }
    }

    /**
     * Add seeds to the current player warehouse
     */
    public void addStorePlayer(int player, int seedsInOpHouse){
        if(player == 1){
            kalah.setStorePlayer1(seedsInOpHouse);
        }else{
            kalah.setStorePlayer2(seedsInOpHouse);
        }
    }


    public void spreadSeeds(int house, int player, int seedsHouse){
        if(player == 0){
            spreadSeedsPlayer2(house,seedsHouse);
        } else{
            spreadSeedsPlayer1(house,seedsHouse);
        }
        changePlayer();
    }

    /**
     * Spread the seeds of a certain house along the board.
     */
    private void spreadSeedsPlayer2 (int house, int seedsInHouse){
        int index = house-1;
        int seeds = seedsInHouse;
        while (index >= 0 && seeds > 0){
            if (seeds == 1 && kalah.isEmptyHouse(index,0)){
                caseSteal(index, 0);
                index -= 1;
                seeds -= 1;
            } else{
                kalah.addSeed(index,0);
                index -= 1;
                seeds -= 1;
            }
        }
        if (seeds > 0){
            if(this.currentPlayer == 0){
                addStorePlayer(0);
                seeds -= 1;
            }
            if(seeds == 0){
                this.specialCase = true;
            } else{
                spreadSeedsPlayer1(-1, seeds);
            }
        }
    }

    /**
     * Spread the seeds of a certain house along the board.
     */
    private void spreadSeedsPlayer1(int house, int seedsInHouse){
        int houses = kalah.getHouses();
        int seeds = seedsInHouse;
        int index = house + 1;
        while(index < houses && seeds > 0){
            if (seeds == 1 && kalah.isEmptyHouse(index,1)){
                caseSteal(index, 1);
                index += 1;
                seeds -= 1;
            } else{
                kalah.addSeed(index,1);
                index += 1;
                seeds -= 1;
            }
        }
        if(seeds > 0){
            if(this.currentPlayer == 1){
                addStorePlayer(1);
                seeds -= 1;
            }
            if (seeds == 0){
                this.specialCase = true;
            } else {
                spreadSeedsPlayer2(houses, seeds);
            }
        }
    }

    /**
     * Get the seeds from the house player
     * @return seedsInHouse
     */
    public int getSeedsPlayer(int house, int player) {
        int[][] board = kalah.board();
        return board[player][house];
    }

    /**
     * Get the seeds from the store player
     * @return seedsInStore
     */
    public int getSeedsInStore(int player){
        int seedsInStore = 0;
        if(player == 1){
            seedsInStore = kalah.getStorePlayer1();
        }else{
            seedsInStore = kalah.getStorePlayer2();
        }
        return seedsInStore;
    }

    private void caseSteal(int house, int player){
        int oppHouse = kalah.seedsInOpositeHouse(house,player);
        if(player == this.currentPlayer && oppHouse != 0 ){
            int oppPlayer = getWaitingPlayer();
            int seedsInOPlayer = kalah.getSeedsPlayer(house, oppPlayer);
            kalah.emptyHouse(house,oppPlayer);
            addStorePlayer(this.currentPlayer, seedsInOPlayer + 1);
        }else{
            kalah.addSeed(house,player);
        }
    }

    /**
     * Get the state of the game
     * @return seedsInStore
     */
    public boolean isOverGame(){
        return this.isOver;
    }

    private void theGameIsOver(){
        boolean state = kalah.isOver();
        if (state){
            for(int i = 0; i < 2; i++){
                int restSeeds = 0;
                for (int j = 0; j < this.kalah.getHouses(); j ++){
                    restSeeds += getSeedsPlayer(j,i);
                }
                if(i == 0){
                    kalah.setStorePlayer2(restSeeds);
                }else{
                    kalah.setStorePlayer1(restSeeds);
                }
            }
            this.isOver = true;
        }
    }

    /**
     * Get the number of houses od kalah
     * @return Houses
     */
    public int getNumberOfHouses(){
        return this.kalah.getHouses();
    }

    /**
     * Determine if the game is in special state
     * @return specialCase
     */
    public boolean isSpecialCase(){
        return this.specialCase;
    }

    /**
     * Change the number of houses of the Kalah
     */
    public void setNumHoses(int newHouses) throws KalahGameException{
        if(newHouses < 1) throw new KalahGameException(KalahGameException.RANGE_HOUSES);
        this.kalah.setNumHouses(newHouses);
    }
    /**
     * Change the number of seeds of the Kalah
     */
   public void setNumSeeds(int newSeeds) throws KalahGameException{
       if(newSeeds < 0) throw new KalahGameException(KalahGameException.RANGE_SEEDS);
       this.kalah.setNumSeeds(newSeeds);
       this.kalah.initialize();
    }
}
