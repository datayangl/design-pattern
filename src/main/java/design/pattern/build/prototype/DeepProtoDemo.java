package design.pattern.build.prototype;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 需求1：假设数据库中存储了大约 10 万条“搜索关键词”信息，{@link SearchWord} 每条信息包含关键词、关键词被搜索的次数、信息最近被更新的时间等。
 * 系统 A 在启动的时候会加载这份数据到内存中，用于处理某些其他的业务需求。我们给关键词建立一个散列表索引方便查找。
 * 系统 B，专门用来分析搜索日志，定期（比如间隔 10 分钟）批量地更新数据库中的数据，并且标记为新的数据版本。
 *
 * 需求2：任何时刻，系统 A 中的所有数据都必须是同一个版本的，要么都是版本 a，要么都是版本 b。除此之外，在更新内存数据的时候，
 * 系统 A 不能处于不可用状态，也就是不能停机更新数据。
 *
 * 解决方案1：系统 A 中，记录当前数据的版本 Va 对应的更新时间 Ta，从数据库中捞出更新时间大于 Ta 的所有搜索关键词，也就是找出 Va 版本与最新版本数据的“差集”，
 * 然后针对差集中的每个关键词进行处理。如果它已经在散列表中存在了，我们就更新相应的搜索次数、更新时间等信息；如果它在散列表中不存在，我们就将它插入到散列表中。
 * 为了避免停机更新数据，先生成一个数据快照，然后应用"差集"，最后用快照和数据切换。
 *
 */
public class DeepProtoDemo {
    private HashMap<String, SearchWord> currentKeywords=new HashMap<>();

    private long lastUpdateTime = -1;

    public void refresh() {
        HashMap<String, SearchWord> newKeywords = (HashMap<String, SearchWord>) currentKeywords.clone();
        // 从数据库中取出所有的数据，放入到newKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords();
        long maxNewUpdatedTime = lastUpdateTime;

        for (SearchWord searchWord : toBeUpdatedSearchWords) {
            // 查找最新更新时间
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }
            if(newKeywords.containsKey(searchWord.getKeyWord())) {
                SearchWord oldSearchWord = newKeywords.get(searchWord.getKeyWord());
                oldSearchWord.setLastUpdateTime(searchWord.getLastUpdateTime());
                oldSearchWord.setSearchNum(searchWord.getSearchNum());
            } else {
                newKeywords.put(searchWord.getKeyWord(), searchWord);
            }
            // 更新最近更新时间
            lastUpdateTime = maxNewUpdatedTime;
        }
        currentKeywords = newKeywords;
    }
    private List getSearchWords() {
        // TODO: 从数据库中取出所有的数据
        return null;
    }
}

class SearchWord {
    private String keyWord;
    private long lastUpdateTime;
    private long searchNum;

    public SearchWord(String keyWord, long lastUpdateTime, long searchNum) {
        this.keyWord = keyWord;
        this.lastUpdateTime = lastUpdateTime;
        this.searchNum = searchNum;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public long getSearchNum() {
        return searchNum;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setSearchNum(long searchNum) {
        this.searchNum = searchNum;
    }
}