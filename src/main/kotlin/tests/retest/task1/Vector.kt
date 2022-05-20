package tests.retest.task1

class Vector<T : ArithmeticAvailable<T>>(val coordinates: List<T>) {
    private val size = coordinates.size
    operator fun plus(vector: Vector<T>): Vector<T> {
        require(this.size == vector.size) { "Vectors should be same size" }
        return Vector(coordinates.indices.map { coordinates[it] + vector.coordinates[it] }.toList())
    }

    operator fun minus(vector: Vector<T>): Vector<T> {
        require(this.size == vector.size) { "Vectors should be same size" }
        return Vector(coordinates.indices.map { coordinates[it] - vector.coordinates[it] }.toList())
    }

    operator fun times(vector: Vector<T>): T {
        require(this.size == vector.size) { "Vectors should be same size" }
        require(this.size > 0) { "Size should be positive for scalar multiplications" }
        var sum = coordinates[0] * vector.coordinates[0]
        (1 until size.toInt()).forEach { sum += coordinates[it] * vector.coordinates[it] }
        return sum
    }

    fun isNull(): Boolean {
        coordinates.forEach { if (!it.isNull()) return false }
        return true
    }

    override fun toString(): String = coordinates.joinToString(" ")
}
