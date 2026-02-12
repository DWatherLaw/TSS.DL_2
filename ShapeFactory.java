import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;


public class ShapeFactory {

    // Поля класу
    public Shape shape;
    public BasicStroke stroke = new BasicStroke(3.0F);
    public Paint paint;
    public int width = 25;
    public int height = 25;

    /**
     * Конструктор для зворотної сумісності.
     */
    public ShapeFactory(int shape_type) {
        switch(shape_type / 10) {
            case 1:
                this.shape = createStar(6, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 2.0D);
                break;
            case 3:
                this.shape = createStar(5, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 4.0D);
                break;
            case 5:
                this.shape = new java.awt.geom.Rectangle2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height);
                break;
            case 7:
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0D) / 2.0D * (double)this.height;
                path.moveTo((double)(-this.width / 2), -tmp_height);
                path.lineTo(0.0D, -tmp_height);
                path.lineTo((double)(this.width / 2), tmp_height);
                path.closePath();
                this.shape = path;
                break;
            case 9:
                this.shape = new java.awt.geom.Arc2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height, 30.0D, 300.0D, 2);
                break;
            default:
                throw new Error("type is nusupported");
        }
        switch(shape_type % 10) {
            case 1:
                this.stroke = new BasicStroke(3.0F);
                break;
            case 3:
                break;
            case 4:
                this.stroke = new BasicStroke(7.0F);
                break;
            case 7:
                this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
                break;
            case 8:
                this.paint = Color.red;
                break;
            default:
                throw new Error("type is nusupported");
        }
    }

    /**
     * Новий, покращений конструктор, що використовує Enums.
     * @param form Форма фігури.
     * @param style Стиль фігури.
     */
    public ShapeFactory(ShapeForm form, ShapeStyle style) {
        // Логіка для вибору форми
        switch(form) {
            case HEXAGON:
                this.shape = createStar(6, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 2.0D);
                break;
            case STAR_3:
                this.shape = createStar(3, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 2.0D);
                break;
            case STAR_5:
                this.shape = createStar(5, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 4.0D);
                break;
            case RECTANGLE:
                this.shape = new java.awt.geom.Rectangle2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height);
                break;
            case TRAPEZOID:
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0D) / 2.0D * (double)this.height;
                path.moveTo((double)(-this.width / 2), -tmp_height);
                path.lineTo(0.0D, -tmp_height);
                path.lineTo((double)(this.width / 2), tmp_height);
                path.closePath();
                this.shape = path;
                break;
            case ARC:
                this.shape = new java.awt.geom.Arc2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height, 30.0D, 300.0D, 2);
                break;
        }
        // Логіка для вибору стилю
        switch(style) {
            case THICK_3PX:
                this.stroke = new BasicStroke(3.0F);
                break;
            case THICK_7PX:
                 this.stroke = new BasicStroke(7.0F);
                break;
            case WHITE_GRAY_GRADIENT:
                this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
                break;
            case RED_COLOR:
                this.paint = Color.red;
                break;
            case DEFAULT_THICKNESS:
                break;
        }
    }

    /**
     * Допоміжний метод, що створює багатокутник.
     */
    private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
          double angle = 3.141592653589793D / (double)arms;
          GeneralPath path = new GeneralPath();
          for(int i = 0; i < 2 * arms; ++i) {
             double r = (i & 1) == 0 ? rOuter : rInner;
             java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
             if (i == 0) {
                path.moveTo(p.getX(), p.getY());
             } else {
                path.lineTo(p.getX(), p.getY());
             }
          }
          path.closePath();
          return path;
    }
}