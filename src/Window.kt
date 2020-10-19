import Main.calcularDeterminante
import Main.calcularMatrizEscalonada
import swingRAD.fontTextBig
import swingRAD.mainBar.MainBar
import swingRAD.setProperties
import javax.swing.*

class Window: JFrame() {
    private val pOrdenMatriz =JPanel()
    private val pMatrizIn = JPanel()
    private val pMatrizOut = JPanel()

    private val bDibujarMatriz = JButton()
    private val bCalcularRango = JButton()
    private val bEscalonar = JButton()
    private val bDeterminante = JButton()

    private val tFilas = JTextField()
    private val tColumnas = JTextField()
    private val numbers = mutableListOf<JTextField>()

    private val lEscalonado = JTextArea()
    private val lDeterminante = JTextArea()
    private val lRango = JLabel()

    init {
        val mainBar = MainBar(1300, this)
        mainBar.setLogo(ImageIcon("resources/logo.png"))
        mainBar.setTitle("Determinante, escalonamiento y rango de una matriz")
        add(mainBar)

        pOrdenMatriz.setProperties(35, 55, 515, 160)
        add(pOrdenMatriz)

        val pDatos = JPanel()
        pDatos.setProperties(570, 55, 684, 160)
        add(pDatos)

        lRango.setProperties(32, 18, 500, 32)
        pDatos.add(lRango)

        lEscalonado.setProperties(32, 44, 500, 44, false, background = null, border = null)
        pDatos.add(lEscalonado)

        lDeterminante.setProperties(32, 87, 500, 64, false, background = null, border = null)
        pDatos.add(lDeterminante)

        pMatrizIn.setProperties(35, 235, 600, 450)
        add(pMatrizIn)

        pMatrizOut.setProperties(655, 235, 600, 450)
        add(pMatrizOut)

        addJTextFields()
        addJLabels()
        addJButtons()

        setProperties(1300, 720)
    }

    private fun addJLabels() {
        val lOrden = JLabel()
        lOrden.setProperties(32, 32, 160, 32, "Orden de la matriz", fontTextBig)
        pOrdenMatriz.add(lOrden)

        val lFila = JLabel()
        lFila.setProperties(32, 64, 140, 32, "Filas =")
        pOrdenMatriz.add(lFila)

        val lColumna = JLabel()
        lColumna.setProperties(32, 96, 140, 32, "Columnas =")
        pOrdenMatriz.add(lColumna)
    }

    private fun addJButtons() {
        bDibujarMatriz.setProperties(220, 64, 120, 28, "Dibujar matriz")
        bDibujarMatriz.addActionListener {
            pMatrizIn.removeAll()
            numbers.clear()
            val width = 560 / tColumnas.text.toInt()
            val height = 410 / tFilas.text.toInt()
            for (i in 0 until tFilas.text.toInt()) {
                for (j in 0 until tColumnas.text.toInt()) {
                    val textField = JTextField()
                    textField.horizontalAlignment = SwingConstants.CENTER
                    textField.setProperties(25 + j * width, 25 + i * height, width - 10, height - 10)
                    numbers.add(textField)
                    pMatrizIn.add(numbers.last())
                }
            }
            pMatrizIn.repaint()
        }
        pOrdenMatriz.add(bDibujarMatriz)

        bCalcularRango.setProperties(220, 96, 120, 28, "Calcular rango")
        bCalcularRango.addActionListener {
            var rango = 0

            //pasa los valores ingresados a una matriz
            var c = 0
            val matrizIn = Array(tFilas.text.toInt()) { DoubleArray(tColumnas.text.toInt()) }
            for (i in 0 until tFilas.text.toInt()) {
                for (j in 0 until tColumnas.text.toInt()) {
                    matrizIn[i][j] = numbers[c].text.toDouble()
                    c++
                }
            }
            //calcula
            calcularMatrizEscalonada(matrizIn)
            var i=0
            var j=0

            while(true) {
                if(matrizIn[i][j] != 0.0) {
                    rango++
                    if(i<tFilas.text.toInt()-1 && j<tColumnas.text.toInt()-1){
                        i++
                        j++
                    }else break
                }else{
                    if(j<tColumnas.text.toInt()-1)
                        j++
                    else break
                }
            }

            //muestra el resultado
            lRango.text = "El rango de la matriz es: $rango"
        }
        pOrdenMatriz.add(bCalcularRango)

        bEscalonar.setProperties(370, 64, 120, 28, "Escalonar")
        bEscalonar.addActionListener {
            //pasa los valores ingresados a una matriz
            var c = 0
            val matrizIn = Array(tFilas.text.toInt()) { DoubleArray(tColumnas.text.toInt()) }
            for (i in 0 until tFilas.text.toInt()) {
                for (j in 0 until tColumnas.text.toInt()) {
                    matrizIn[i][j] = numbers[c].text.toDouble()
                    c++
                }
            }
            val n = tFilas.text.toInt()
            val m = tColumnas.text.toInt()

            //muestra OE por formula y contador
            lEscalonado.text = "Operaciones elementales por contador en escalonamiento: ${calcularMatrizEscalonada(matrizIn)}\n" +
                    "Operaciones elementales por fórmula en escalonamiento: ${70*n*n - 280*n + 494 + (m - n)*39}"

            //muestra matriz escalonada
            pMatrizOut.removeAll()
            pMatrizOut.repaint()
            val width = 560 / tColumnas.text.toInt()
            val height = 410 / tFilas.text.toInt()
            for (i in 0 until tFilas.text.toInt()) {
                for (j in 0 until tColumnas.text.toInt()) {
                    val textField = JTextField()
                    textField.horizontalAlignment = SwingConstants.CENTER
                    textField.setProperties(
                        25 + j * width, 25 + i * height, width - 10, height - 10, false,
                        "%.4f".format(matrizIn[i][j])
                    )
                    pMatrizOut.add(textField)
                }
            }
        }
        pOrdenMatriz.add(bEscalonar)

        bDeterminante.setProperties(370, 96, 120, 28, "Determinante")
        bDeterminante.addActionListener {
            //pasa los valores ingresados a una matriz
            var c = 0
            if(tFilas.text.toInt() != tColumnas.text.toInt()){
                JOptionPane.showMessageDialog(null, "La matriz debe ser cuadrada", "ERROR", JOptionPane.ERROR_MESSAGE)
            }else {
                val matrizIn = Array(tFilas.text.toInt()) { DoubleArray(tColumnas.text.toInt()) }
                for (i in 0 until tFilas.text.toInt()) {
                    for (j in 0 until tColumnas.text.toInt()) {
                        matrizIn[i][j] = numbers[c].text.toDouble()
                        c++
                    }
                }

                //muestra OE por formula y contador
                val n = matrizIn.size
                val texto = "Operaciones elementales en determinante por contador: ${calcularDeterminante(matrizIn)}\n" +
                        "Operaciones elementales en determinante por fórmula: ${(22*n*n*n + 21*n*n + 17*n - 42)/ 6}\n" +
                        "El determinante es ${"%.3f".format(Main.determinante)}\n" +
                        "Complejidad O(N³)"
                if(Main.determinante != 0.0) {
                    lDeterminante.text = texto
                }else {
                    lDeterminante.text = ""
                }
            }
        }
        pOrdenMatriz.add(bDeterminante)
    }

    private fun addJTextFields() {
        tFilas.setProperties(80, 64, 110, 27)
        pOrdenMatriz.add(tFilas)

        tColumnas.setProperties(120, 96, 70, 27)
        pOrdenMatriz.add(tColumnas)
    }
}