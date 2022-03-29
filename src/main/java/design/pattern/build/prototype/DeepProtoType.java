package design.pattern.build.prototype;

import java.io.*;

/**
 * 深拷贝的2种实现方式
 */
public class DeepProtoType implements Serializable, Cloneable {
    public String name;
    public DeepCloneableTarget deepCloneableTarget;

    public DeepProtoType() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return deepClone1();
    }

    /**
     * 深拷贝方式1：递归拷贝对象、对象的引用对象以及引用对象的引用对象，直到要拷贝的对象只包含基本数据类型数据，没有引用对象为止
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Object deepClone1() throws CloneNotSupportedException {
        Object deep = null;
        // 基本数据类型（属性）和String，使用默认的clone
        deep = super.clone();
        // 引用类型的属性，单独处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();

        return deep;
    }


    /**
     * 深拷贝方式2：序列化
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Object deepClone2() throws CloneNotSupportedException {
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //当前这个对象以对象流的方式输出
            oos.writeObject(this);

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            DeepProtoType copyObj = (DeepProtoType) ois.readObject();
            return  copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
