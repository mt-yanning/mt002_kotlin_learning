// Task 17.5: Image class (subclass of Bitmap, implements Drawable)
// Note that Bitmap is provided by a separate library module

import com.example.imglib.Bitmap
import java.awt.Graphics2D
import java.awt.image.RescaleOp
// 问题一：不支持多继承，Image 已经继承 Bitmap
// 问题二（假设支持多继承）：用接口表达“能被画出来”能力，而不是直接继承 shape，因为 image 毕竟不是一种 shape
class Image(val x: Int, val y: Int, filename: String) : Bitmap(filename), Drawable {
    override fun draw(context: Graphics2D) {
        val nullOp = RescaleOp(1.0f, 0.0f, null)
        context.drawImage(pixelData, nullOp, x, y)
    }
}
