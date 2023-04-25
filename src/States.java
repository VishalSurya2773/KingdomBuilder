enum GameStates{
    startGame,
    objectiveCards,
    turnStart,
    chooseSettlement,
    gameOver
} 

public class States{
    public static void main(String args[]){
        static GameStates gameStates = GameStates.startGame;
    }
}