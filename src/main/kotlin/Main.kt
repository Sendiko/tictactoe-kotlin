import java.util.*

fun main(args: Array<String>) {
    val board: MutableList<MutableList<Char>> = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )

    val scanner = Scanner(System.`in`)
    var currentPlayer = 1
    var gameWon = false

    fun isValidMove(board: List<List<Char>>, row: Int, column: Int): Boolean{
        return row in 0..2 && column in 0..2 && board[row][column] == ' '
    }

    fun checkForWin(board: List<List<Char>>): Boolean{
        for(i in 0..2){
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                return true
            }
        }

        for (j in 0..2) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return true
            }
        }

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true
        }

        return false
    }

    fun isBoardFull(board: List<List<Char>>): Boolean {
        for (i in 0..2){
            for (j in 0..2){
                if (board[i][j] == ' '){
                    return false
                }
            }
        }
        return true
    }

    fun displayBoard(board: List<List<Char>>){
        for(i in 0..2){
            for(j in 0..2){
                print(board[i][j])
                if(j < 2){
                    print(" | ")
                }
            }
            println()
            if (i < 2){
                println("---------")
            }
        }
    }

    while (!gameWon){
        displayBoard(board)
        print("Player $currentPlayer, enter row and column (e.g., 1 2): ")

        val row = scanner.nextInt() - 1
        val column = scanner.nextInt() - 1

        if (isValidMove(board, row, column)){
            board[row][column] = if (currentPlayer == 1) 'X' else 'O'
            currentPlayer = 3 - currentPlayer
            if (checkForWin(board)){
                gameWon = true
                displayBoard(board)
                println("Player " + (3 - currentPlayer) + " wins!")
            } else if (isBoardFull(board)){
                gameWon = true
                println("It's a draw!")
            }
        } else {
            print("Invalid move, please try again")
        }

    }
    scanner.close()
}