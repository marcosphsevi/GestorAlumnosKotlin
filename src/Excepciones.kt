import kotlin.system.exitProcess

object Excepciones {
    fun depuracionInput (): String {
        val input: String?
        input = readlnOrNull()?.trim()
        if (input.equals(null)){
            exitProcess(0)
        }else{
            return input!!
        }
    }

    fun depuracionInt (input: String?): Boolean{
        try {
            input!!.toInt()
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }

    fun depuracionDouble (input: String?): Boolean{
        try {
            input!!.toDouble()
            return true
        } catch (e: NumberFormatException){
            return false
        }
    }

    fun error(mensaje: String) {
        println("\u001B[31mERROR: $mensaje\u001B[0m")
    }
}