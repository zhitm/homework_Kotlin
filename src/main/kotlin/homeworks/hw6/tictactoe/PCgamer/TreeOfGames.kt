package homeworks.hw6.tictactoe.PCgamer

class TreeOfGames(private val root: Node) {
    var currentState = root
    fun getBestMove(): Move {
        require(root.children.isNotEmpty()) { "Игра закончена, искать новые ходы нет смысла" }
        val child = root.children.maxByOrNull { it.coeffOfWin }
        child?.let { changeState(it) }
        return child?.move ?: throw Exception("нет детей и не выбрать лучший ход")
    }

    private fun changeState(child: Node) {
        currentState = child
    }
    fun changeStateByMove(move: Move){
        currentState = root.children.find { it.move == move } ?: throw Exception("ход противника не повторить")
    }
    fun printBoard(){
        currentState.printBoard()
    }
}
