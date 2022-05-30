package design.pattern.structure.flyweight;

/**
 * 棋子实体类V2
 *
 * 假设同时进行成千上万的对局，此时会存在大量的相似对象（id、text、color 相同，而positionX、positionY 不同），内存开销非常大。
 * 实际上，我们可以将棋子的 id、text、color 属性拆分出来，设计成独立的类，并且作为享元供多个棋盘复用。
 */
public class ChessPieceV2 {
    private ChessPieceUnit chessPieceUnit;
    private int positionX;
    private int positionY;

    public ChessPieceV2(ChessPieceUnit unit, int positionX, int positionY) {
        this.chessPieceUnit = unit;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
