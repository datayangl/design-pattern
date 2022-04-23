package design.pattern.build.builder;

import design.pattern.utils.StringUtils;

/**
 * 需求分析：
 * ResourcePoolConfig 有4个配置项 name、maxTotal、maxIdle、minIdle。且有如下要求：
 * 1. name不为空
 * 2. maxTotal、maxIdle、minIdle 都 > 0
 * 3. minIdle <= maxIdle <= maxTotal
 *
 * 原生的构造问题：
 * 1. 配置项过多或者必填属性很多，构造函数冗长
 * 2. 一些校验逻辑、依赖逻辑通过set()无法执行，只能在构造函数中进行。比如 maxIdle < maxTotal
 * 3. 对象暴露set() 可能会导致对象被修改
 *
 *
 * Builder 模型：
 * 1. 可以保证对象的状态
 * 2.
 */
public class ResourcePoolConfig {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    private ResourcePoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public Builder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal < 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.minIdle = minIdle;
            return this;
        }

        /**
         * 生成实例
         *
         * 在这个方法中进行初始化逻辑和校验逻辑，比如必填项校验、依赖关系校验、约束条件校验等
         * @return
         */
        public ResourcePoolConfig build() {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("...");
            }

            if (minIdle > maxTotal || minIdle > maxIdle) {
                throw new IllegalArgumentException("...");
            }

            return new ResourcePoolConfig(this);
        }
    }

}
