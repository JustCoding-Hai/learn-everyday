import cn.hutool.dfa.WordTree;
import org.junit.Test;

import java.util.List;

/**
 * 有限状态自动机-敏感词搜索测试
 *
 * @author huangjinhai
 * @date 2021-11-29
 */
public class DfaTest {

    @Test
    public void testDfa() {
        WordTree tree = new WordTree();
        tree.addWord("中国");
        tree.addWord("移动");
        tree.addWord("杭州");
        tree.addWord("移动通信");
        tree.addWord("杭州市");
        tree.addWord("中国人");

        String text = "中国移动在中国，都是中国人，移动杭研在杭州，杭州市";

        List<String> match = tree.matchAll(text, -1, false, false);
        System.out.println(match);
    }

}
