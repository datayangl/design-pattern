package design.pattern.structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * ChessPieceUnit 工厂类
 *
 * 利用工厂类来缓存 ChessPieceUnit 信息（也就是 id、text、color）。
 */
public class ChessPieceUnitFactory {
    private static final Map<Integer, ChessPieceUnit> pieces = new HashMap<>();

    static {
        pieces.put(1, new ChessPieceUnit(1, "車", Color.BLACK));
        pieces.put(2, new ChessPieceUnit(2,"馬", Color.BLACK));
        //...省略摆放其他棋子的代码...
    }

    public static ChessPieceUnit getChessPiece(int chessPieceId) {
        return pieces.get(chessPieceId);
    }
}
