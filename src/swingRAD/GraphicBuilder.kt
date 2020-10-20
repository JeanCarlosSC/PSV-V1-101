package swingRAD

import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.*
import javax.swing.border.Border

fun JPanel.setProperties(x: Int, y: Int, width: Int, height: Int, backgroundColor: Color? = semiDarkGrayBlue, border: Border? = semiDarkGray2Border,
                         layoutManager: LayoutManager? = null) {
    this.setBounds(x, y, width, height)
    this.background = backgroundColor
    this.border = border
    this.layout = layoutManager
}

/**
 * icon label
 */
fun JLabel.setProperties(x: Int, y: Int, icon: Icon, cursor: Cursor? = null) {
    this.setSize(icon.iconWidth, icon.iconHeight)
    this.setLocation(x, y)
    this.icon = icon
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    this.cursor = cursor
}

/**
 * text label
 */
fun JLabel.setProperties(x: Int, y: Int, width: Int, height: Int, str: String? = "", font: Font? = fontText, fontColor: Color? = darkWhite,
                         hAlignment: String? = "LEFT") {
    this.setBounds(x, y, width, height)
    this.foreground = fontColor
    this.font = font
    this.text = str
    this.horizontalAlignment = when(hAlignment) {
        "CENTER" -> SwingConstants.CENTER
        "RIGHT" -> SwingConstants.RIGHT
        else -> SwingConstants.LEFT
    }
}

fun JFrame.setProperties(width: Int = 1280, height: Int = 720, background: Color? = megaDarkGray, undecorated: Boolean = true,
         border: Border? = blackBorderTransparent, relativeLocation: Component? = null, visible: Boolean = true,
         layoutManager: LayoutManager? = null) {
    this.setSize(width, height)
    this.setLocationRelativeTo(relativeLocation)
    this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    this.contentPane.background = background
    this.isUndecorated = undecorated
    this.rootPane.border = border
    this.layout = layoutManager
    this.isVisible = visible
}

/**
 * Icon button
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun JButton.setProperties(x: Int, y: Int, icon: Icon?, cursor: Cursor? = handCursor) {
    this.setLocation(x, y)
    this.isContentAreaFilled = false
    this.border = null
    this.cursor = cursor
    this.isFocusable = false
    if (icon != null) {
        this.setSize(icon.iconWidth, icon.iconHeight)
        this.icon = icon
    }
}

/**
 * text button
 */
fun JButton.setProperties(x: Int, y: Int, width: Int, height: Int, text: String?, cursor: Cursor? = handCursor, font: Font? = fontTitleMini,
                          background: Color? = darkGray, foreground: Color? = darkWhite, border: Border? = semiDarkGray2Border,
                          alignment: String? = "CENTER", isSolid: Boolean = true, backgroundEntered: Color = semiDarkGray) {
    setProperties(x, y, width, height, cursor, background, isSolid)
    this.text = text
    this.font = font
    this.foreground = foreground
    this.border = border
    this.horizontalAlignment = when (alignment) {
        "LEFT" -> SwingConstants.LEFT
        "RIGHT" -> SwingConstants.RIGHT
        else -> SwingConstants.CENTER
    }

    this.addMouseListener(object : MouseListener {
        override fun mouseClicked(e: MouseEvent) {
        }

        override fun mousePressed(e: MouseEvent) {
        }

        override fun mouseReleased(e: MouseEvent) {
        }

        override fun mouseEntered(e: MouseEvent) {
            this@setProperties.background = backgroundEntered
        }

        override fun mouseExited(e: MouseEvent) {
            this@setProperties.background = background
        }
    })
}

/**
 * text button filled with icon
 */
fun JButton.setProperties( x: Int,y: Int, width: Int, height: Int, cursor: Cursor?, background: Color?, isSolid: Boolean, icon: Icon? = null) {
    this.setProperties(x, y, icon, cursor)
    this.setSize(width, height)
    this.background = background
    this.isContentAreaFilled = isSolid
}

fun JTextArea.setProperties(x: Int, y: Int, width: Int, height: Int, editable: Boolean = true, lineWrap: Boolean = true, text: String? = "",
                            foreground: Color? = darkWhite, background: Color? = darkGray, font: Font? = fontText, border: Border? = semiDarkGray2Border) {
    this.setBounds(x, y, width, height)
    this.text = text
    this.isEditable = editable
    this.foreground = foreground
    this.font = font
    this.background = background
    this.caretColor = foreground
    this.border = border
    this.wrapStyleWord = lineWrap
    this.lineWrap = lineWrap
}

fun JTextField.setProperties(x: Int, y: Int, width: Int, height: Int, editable: Boolean = true, text: String? = "", foreground: Color? = darkWhite,
                            backgroundColor: Color? = darkGray, font: Font? = fontText, border: Border? = semiDarkGray2Border) {
    this.setBounds(x, y, width, height)
    this.text = text
    this.isEditable = editable
    this.foreground = foreground
    this.font = font
    this.background = backgroundColor
    this.caretColor = foreground
    this.border = border
}