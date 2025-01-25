package SnakeAndLadder;

import SnakeAndLadder.entity.*;

import java.util.*;

public class SnakeAndLadderExecution {
    public static void executeGame(List<Player> listOfPlayers, List<BoardObject> listOfBoardObjects, Dice dice) {
        Queue<Player> playerQueue = new LinkedList<>(listOfPlayers);
        boolean isGameOver = false;

        while (!isGameOver) {
            Player currentPlayer = playerQueue.poll();
            // Roll dice.
            int diceRoll = dice.rollDice();

            /* To move the player:
             *   - First get the gamePiece:
             *   - Then add the diceRoll number.
             *   - Update the player's location.
             *   - Check if the player is at a snake or ladder.*/

            GamePiece playerGamePiece = currentPlayer.getGamePiece();
            int currentGamePieceLocation = playerGamePiece.getCurrentPosition();
            playerGamePiece.updatePosition(currentGamePieceLocation + diceRoll);

            // Condition Snake and Ladder:
            updatePosition(listOfBoardObjects, playerGamePiece);

            int latestPosition = currentPlayer.getGamePiece().getCurrentPosition();
            if (currentPlayer.getGamePiece().getCurrentPosition() == 100) {
                String msg = String.format("%s rolled a %s and moved from %s to %s ",
                        currentPlayer.getPlayerName(), diceRoll, currentGamePieceLocation, latestPosition);
                System.out.println(msg);
                System.out.println(currentPlayer.getPlayerName() + " wins the game");
                isGameOver = true;
                continue;
            }

            String msg = String.format("%s rolled a %s and moved from %s to %s ",
                    currentPlayer.getPlayerName(), diceRoll, currentGamePieceLocation, latestPosition);
            System.out.println(msg);

            //Spacing out the output:
            System.out.println("--- NEXT TURN ---");
            System.out.println();

            //Add player back in queue:
            playerQueue.add(currentPlayer);
        }
    }

    public static void updatePosition(List<BoardObject> boardObjects, GamePiece gamePiece) {
        boardObjects.stream().filter(boardObject -> boardObject.isPlayerOnObject(gamePiece.getCurrentPosition()))
                .findFirst().
                ifPresent(boardObject -> {
                    String msg = String.format("Landed on a %s. %s starts at %s and ends at %s", boardObject.getType(), boardObject.getType(),
                            boardObject.getStartPoint(), boardObject.getEndPoint());
                    System.out.println(msg);

                    gamePiece.updatePosition(boardObject.getEndPoint());
                });
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Objects on board:
        List<Player> listOfPlayers = new ArrayList<>();
        List<BoardObject> listOfBoardObjects = new ArrayList<>();

        System.out.println("Enter snake count here: ");
        int numberOfSnakes = sc.nextInt();
        // Adding snakes here:
        for(int i = 0;  i < numberOfSnakes; i++) {
            int startPos = sc.nextInt();
            int endPos = sc.nextInt();
            Snake newSnake = new Snake(startPos,endPos);

            // Only valid Snakes make it to the board:
            if(newSnake.isValidPosition()) listOfBoardObjects.add(newSnake);
            else{
                System.out.println("Invalid Snake entered. Game over!");
                break;
            }
        }

        System.out.println("Enter ladders here: ");
        int numberOfLadders = sc.nextInt();
        // Adding Ladders here:
        for(int i = 0;  i < numberOfLadders; i++) {
            int startPos = sc.nextInt();
            int endPos = sc.nextInt();
            Ladder newLadder = new Ladder(startPos,endPos);

            // Only valid Snakes make it to the board:
            if(newLadder.isValidPosition()) listOfBoardObjects.add(newLadder);
            else{
                System.out.println("Invalid Ladder entered. Game over!");
                break;
            }
        }

        System.out.println("Enter player count: ");
        int numberOfPlayers = sc.nextInt();
        //Adding players here:
        for(int i = 0;  i < numberOfPlayers; i++) {
            String userName = sc.next();
            listOfPlayers.add(new Player(userName, new GamePiece()));
        }

        //Dice:
        Dice dice = new Dice();
        //Execute
        executeGame(listOfPlayers, listOfBoardObjects, dice);
    }
}
