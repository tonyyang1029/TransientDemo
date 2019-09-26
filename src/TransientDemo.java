/**
 * https://www.cnblogs.com/chenpi/p/6185773.html
 */

import org.w3c.dom.css.Rect;

import java.io.*;

public class TransientDemo {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Rectangle rectangle = new Rectangle(3, 4);
        System.out.println("#1 - Create Rectangle instance:\n" + rectangle);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rectangle"));
        oos.writeObject(rectangle);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rectangle"));
        Rectangle rectangle1 = (Rectangle)ois.readObject();
        System.out.println("#2 - Retrieve Rectangle object:\n" + rectangle1);
        rectangle1.setArea();
        System.out.println("#3 - Set Area:\n" + rectangle1);
        ois.close();
    }
}

class Rectangle implements Serializable {
    private static final long serialVersionUID = 1710022455003682613L;
    private Integer width;
    private Integer height;
    private transient Integer area;

    public Rectangle(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
    }

    public void setArea() {
        this.area = this.width * this.height;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(40);
        sb.append("width : ");
        sb.append(this.width);
        sb.append("\nheight : ");
        sb.append(this.height);
        sb.append("\narea : ");
        sb.append(this.area);

        return sb.toString();
    }
}
