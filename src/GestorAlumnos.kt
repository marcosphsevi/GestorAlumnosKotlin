class GestorAlumnos {
    private var alumnos: MutableList<Alumno> = mutableListOf<Alumno>()

    private fun imprimirMenu () {
        println("""
            ===== GESTOR DE ALUMNOS =====
                0. Salir
                1. Registrar alumno
                2. Listar alumnos
                3. Calcular media del grupo
                4. Mostrar aprobados
                5. Mostrar suspensos
                6. Buscar alumno
                7. Eliminar alumno
            Seleccione una opción:
        """.trimIndent())
    }

    fun menu () {
        var input: String?
        while (true){
            imprimirMenu()
            input = Excepciones.depuracionInput()
            when (input){
                "0" -> {
                    println("===== SALIR =====")
                    break
                }
                "1" -> {
                    println("===== REGISTRAR ALUMNO =====")
                    registrarAlumno()
                }
                "2" -> {
                    println("===== LISTAR ALUMNOS =====")
                    listarAlumnos()
                }
                "3" -> {
                    println("===== CALCULAR MEDIA DEL GRUPO =====")
                    calcularMedia()
                }
                "4" -> {
                    println("===== MOSTRAR APROBADOS =====")
                    mostrarAprobados()
                }
                "5" -> {
                    println("===== MOSTRAR SUSPENSOS =====")
                    mostrarSuspensos()
                }
                "6" -> {
                    println("===== BUSCAR ALUMNO =====")
                    buscarAlumno()

                }
                "7" -> {
                    println("===== ELIMINAR ALUMNO =====")
                    eliminarAlumno()

                }
                else -> {
                    if (!Excepciones.depuracionInt(input)){
                        Excepciones.error("Tienes que introducir un número entero.")
                    } else {
                        Excepciones.error("Opción no válida.")
                    }
                }
            }
        }
    }

    private fun registrarAlumno (){
        var input: String?
        val nombre: String
        val apellido: String
        val nota: Double
        while (true) {
            println("Introduce el nombre del alumno, deja el campo vacío para salir: ")
            input = Excepciones.depuracionInput()
            if (input!!.equals("")){
                return
            } else {
                nombre = input
                break
            }
        }
        while (true) {
            println("Introduce los apellidos del alumno, deja el campo vacío para salir: ")
            input = Excepciones.depuracionInput()
            if (input!!.equals("")){
                return
            } else {
                apellido = input
                break
            }
        }
        while (true) {
            println("Introduce la nota media del alumno, deja el campo vacío para salir: ")
            input = Excepciones.depuracionInput()
            if (input!!.equals("")){
                return
            } else if (!Excepciones.depuracionDouble(input)) {
                Excepciones.error("Tienes que introducir un número décimal")
            } else if (input!!.toDouble() !in 0.0..10.0){
                Excepciones.error("Tienes que introducir un número entre 0 y 10.")
            } else {
                nota = input!!.toDouble()
                break
            }
        }
        alumnos.add(Alumno(nombre, apellido, nota))
        println("Alumno registrado correctamente.")
    }

    private fun listarAlumnos (){
        var i: Int = 0
        if (!alumnos.isEmpty()) {
            for (alumno in alumnos) {
                i++
                println("$i. ${alumno.nombre} ${alumno.apellidos} - Nota: ${"%.2f".format(alumno.nota)}")
            }
        } else {
            Excepciones.error("Lista de alumnos vacía, añade alumnos para poder mostrarlos.")
        }
    }

    private fun calcularMedia (){
        var media: Double = 0.0
        if (!alumnos.isEmpty()){
            for (alumno in alumnos){
                media += alumno.nota
            }
            media /= alumnos.size
            println("La media del grupo es: ${"%.2f".format(media)}")
        } else {
            Excepciones.error("Lista de alumnos vacía, añade alumnos para poder calcular la media.")
        }
    }

    private fun mostrarAprobados (){
        if (!alumnos.isEmpty()){
            var i: Int = 0
            for (alumno in alumnos){
                if (alumno.nota >= 5){
                    i++
                    println("$i. ${alumno.nombre} ${alumno.apellidos} - Nota: ${"%.2f".format(alumno.nota)}")
                }
            }
            if (i == 0){
                println("No hay ningún alumno aprovado.")
            }
        } else {
            Excepciones.error("Lista de alumnos vacía, añade alumnos para poder mostrarlos.")
        }
    }

    private fun mostrarSuspensos (){
        if (!alumnos.isEmpty()){
            var i: Int = 0
            for (alumno in alumnos){
                if (alumno.nota <= 5){
                    i++
                    println("$i. ${alumno.nombre} ${alumno.apellidos} - Nota: ${"%.2f".format(alumno.nota)}")
                }
            }
            if (i == 0){
                println("No hay ningún alumno suspenso.")
            }
        } else {
            Excepciones.error("Lista de alumnos vacía, añade alumnos para poder mostrarlos.")
        }
    }

    private fun buscarAlumno (){
        var input: String?
        while (true) {
            println("""
                    0. Salir
                    1. Por nombre
                    2. Por apellido
                Seleccione una opción:
            """.trimIndent())
            input = Excepciones.depuracionInput()
            when (input) {
                "0" -> {
                    return
                }
                "1" -> {
                    println("===== POR NOMBRE =====")
                    println("Introduce el texto a buscar: ")
                    input = Excepciones.depuracionInput()
                    if (!input.equals("")) {
                        var i: Int = 0
                        for (alumno in alumnos) {
                            if (alumno.nombre.contains(input, ignoreCase = true)) {
                                i++
                                println("$i. ${alumno.nombre} ${alumno.apellidos} - Nota: ${"%.2f".format(alumno.nota)}")
                            }
                        }
                    } else {
                        Excepciones.error("No has introducido ningún texto a buscar.")
                    }
                }
                "2" -> {
                    println("===== POR APELLIDO =====")
                    println("Introduce el texto a buscar: ")
                    input = Excepciones.depuracionInput()
                    if (!input.equals("")) {
                        var i: Int = 0
                        for (alumno in alumnos) {
                            if (alumno.apellidos.contains(input, ignoreCase = true)) {
                                i++
                                println("$i. ${alumno.nombre} ${alumno.apellidos} - Nota: ${"%.2f".format(alumno.nota)}")
                            }
                        }
                    } else {
                        Excepciones.error("No has introducido ningún texto a buscar.")
                    }
                }
                else -> {
                    if (!Excepciones.depuracionInt(input)){
                        Excepciones.error("Tienes que introducir un número entero.")
                    } else {
                        Excepciones.error("Opción no válida.")
                    }
                }
            }
        }
    }

    private fun eliminarAlumno (){
        var input: String?
        while (true){
            println("0. Salir")
            listarAlumnos()
            println("Elige una el índice a eliminar:")
            input = Excepciones.depuracionInput()
            if (!Excepciones.depuracionInt(input)){
                Excepciones.error("Tienes que introducir un número entero.")
            } else if (input.toInt() !in 0 .. alumnos.size){
                Excepciones.error("El número introducido está fuera de rango")
            } else if (input.toInt() == 0){
                return
            } else {
                alumnos.removeAt(input.toInt()-1)
                println("Alummno eliminado correctamente")
            }
        }
    }
}