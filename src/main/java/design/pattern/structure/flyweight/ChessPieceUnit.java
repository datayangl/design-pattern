package design.pattern.structure.flyweight;

/**
 * 享元类
 *
 * 封装棋子的 id、text、color 属性
 */
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }
}


