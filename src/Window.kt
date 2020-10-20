import Main.calcularDeterminante
import Main.calcularMatrizEscalonada
import swingRAD.*
import swingRAD.mainBar.MainBar
import javax.swing.*

class Window: JFrame() {
    private val pOrdenMatriz = JPanel()
    private val pEscalonado = JPanel()
    private val pRango = JPanel()
    private val pDeterminante = JPanel()
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
        val mainBar = MainBar(1300, this, backgroundColor = cyan, fontColor = black, borderColor = black)
        mainBar.setTitle("Determinante, escalonamiento y rango de una matriz")
        add(mainBar)

        pOrdenMatriz.setProperties(35, 525, 230, 160, backgroundColor = mustard, border = darkOcherBorder)
        add(pOrdenMatriz)

        pEscalonado.setProperties(285, 525, 230, 160, backgroundColor = mustard, border = darkOcherBorder)
        add(pEscalonado)

        pRango.setProperties(535, 525, 250, 160, backgroundColor = mustard, border = darkOcherBorder)
        add(pRango)

        pDeterminante.setProperties(805, 525, 450, 160, backgroundColor = mustard, border = darkOcherBorder)
        add(pDeterminante)

        lRango.setProperties(32, 32, 180, 32, fontColor = black)
        pRango.add(lRango)

        lEscalonado.setProperties(42, 14, 166, 90, false, background = null, border = null, foreground = black)
        pEscalonado.add(lEscalonado)

        lDeterminante.setProperties(32, 14, 400, 90, false, background = null, border = null, foreground = black)
        pDeterminante.add(lDeterminante)

        pMatrizIn.setProperties(35, 55, 600, 450, backgroundColor = mustard, border = darkOcherBorder)
        add(pMatrizIn)

        pMatrizOut.setProperties(655, 55, 600, 450, backgroundColor = mustard, border = darkOcherBorder)
        add(pMatrizOut)

        addJTextFields()
        addJLabels()
        addJButtons()

        setProperties(1300, 720, background = darkBlueGray)
    }

    private fun addJLabels() {
        val lOrden = JLabel()
        lOrden.setProperties(32, 12, 160, 32, "Orden de la matriz", fontTextBig, fontColor = black)
        pOrdenMatriz.add(lOrden)

        val lFila = JLabel()
        lFila.setProperties(32, 44, 140, 32, "Filas =", fontColor = black)
        pOrdenMatriz.add(lFila)

        val lColumna = JLabel()
        lColumna.setProperties(32, 76, 140, 32, "Columnas =", fontColor = black)
        pOrdenMatriz.add(lColumna)
    }

    private fun addJButtons() {
        bDibujarMatriz.setProperties(50, 116, 120, 28, "Dibujar matriz", background = ocher, foreground = black,
            border = darkOcherBorder, backgroundEntered = darkOcher)
        bDibujarMatriz.addActionListener {
            pMatrizIn.removeAll()
            numbers.clear()
            val width = 560 / tColumnas.text.toInt()
            val height = 410 / tFilas.text.toInt()
            for (i in 0 until tFilas.text.toInt()) {
                for (j in 0 until tColumnas.text.toInt()) {
                    val textField = JTextField()
                    textField.horizontalAlignment = SwingConstants.CENTER
                    textField.setProperties(25 + j * width, 25 + i * height, width - 10, height - 10, backgroundColor = ocher,
                        border = darkOcherBorder, foreground = black)
                    numbers.add(textField)
                    pMatrizIn.add(numbers.last())
                }
            }
            pMatrizIn.repaint()
        }
        pOrdenMatriz.add(bDibujarMatriz)

        bCalcularRango.setProperties(67, 116, 120, 28, "Calcular rango", background = ocher, foreground = black,
            border = darkOcherBorder, backgroundEntered = darkOcher)
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
        pRango.add(bCalcularRango)

        bEscalonar.setProperties(47, 116, 120, 28, "Escalonar", background = ocher, foreground = black,
            border = darkOcherBorder, backgroundEntered = darkOcher)
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
            lEscalonado.text = "OE por contador: ${calcularMatrizEscalonada(matrizIn)}\n" +
                    "OE por fórmula: ${28*n*m + 4*n + 2}\n" +
                    "Complejidad O(MN)\n" +
                    "28NM + 4N + 2"

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
                        "%.4f".format(matrizIn[i][j]), backgroundColor = ocher, border = darkOcherBorder, foreground = black
                    )
                    pMatrizOut.add(textField)
                }
            }
        }
        pEscalonado.add(bEscalonar)

        bDeterminante.setProperties(160, 116, 120, 28, "Determinante", background = ocher, foreground = black,
            border = darkOcherBorder, backgroundEntered = darkOcher)
        bDeterminante.addActionListener {
            //pasa los valores ingresados a una matriz
            var c = 0
            if(tFilas.text.toInt() != tColumnas.text.toInt()){
                JOptionPane.showMessageDialog(null, "La matriz debe ser cuadrada", "ERROR", JOptionPane.ERROR_MESSAGE)
                lDeterminante.text = ""
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
                val texto = "OE en determinante por contador: ${calcularDeterminante(matrizIn)}\n" +
                        "OE en determinante por fórmula: ${(22*n*n*n + 21*n*n + 17*n - 42)/ 6}\n" +
                        "Determinante: ${"%.3f".format(Main.determinante)}\n" +
                        "Complejidad O(N³), fórmula: (22N³ + 21N² + 17N - 42)/6"
                if(Main.determinante != 0.0) {
                    lDeterminante.text = texto
                }else {
                    lDeterminante.text = ""
                }
            }
        }
        pDeterminante.add(bDeterminante)
    }

    private fun addJTextFields() {
        tFilas.setProperties(120, 44, 70, 27, backgroundColor = ocher, border = darkOcherBorder, foreground = black)
        pOrdenMatriz.add(tFilas)

        tColumnas.setProperties(120, 76, 70, 27, backgroundColor = ocher, border = darkOcherBorder, foreground = black)
        pOrdenMatriz.add(tColumnas)
    }

}