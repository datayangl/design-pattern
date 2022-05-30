package design.pattern.structure.flyweight;

/**
 * 棋子实体类V1
 *
 * 包含棋子的所有原始属性。在同时进行大量棋局的情况下，这些原始属性占用的内存开销非常大。
 */
public class ChessPieceV1 {
    private int id;
    private String text;
    private Color color;
    private int positionX;
    private int positionY;

    public ChessPieceV1(int id, String text, Color color, int positionX, int positionY) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}