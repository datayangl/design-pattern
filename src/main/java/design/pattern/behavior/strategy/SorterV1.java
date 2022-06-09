package design.pattern.behavior.strategy;


import design.pattern.behavior.strategy.impl.ConcurrentExternalSort;
import design.pattern.behavior.strategy.impl.ExternalSort;
import design.pattern.behavior.strategy.impl.MapReduceSort;
import design.pattern.behavior.strategy.impl.QuickSort;

import java.io.File;

/**
 * sort 基础版本
 */
public class SorterV1 {
    private static final long GB = 1000 * 1000 * 1000;

    public void sortFile(String filePath) {
        // 省略校验逻辑
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg;
        if (fileSize < 6 * GB) { // [0, 6GB)
            sortAlg = new QuickSort();
        } else if (fileSize < 10 * GB) { // [6GB, 10GB)
            sortAlg = new ExternalSort();
        } else if (fileSize < 100 * GB) { // [10GB, 100GB)
            sortAlg = new ConcurrentExternalSort();
        } else { // [100GB, ~)
            sortAlg = new MapReduceSort();
        }
        sortAlg.sort(filePath);
    }
}
