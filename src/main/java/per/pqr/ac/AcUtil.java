package per.pqr.ac;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * AC自动机
 */
public class AcUtil {

    /**
     * 构建字典树
     *
     * @param root 树根节点
     * @param s    匹配的字符串
     */
    public static void insert(AcNode root, String s) {
        AcNode temp = root;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (!temp.children.containsKey(aChar)) {
                temp.children.put(aChar, new AcNode());
            }
            temp = temp.children.get(aChar);
        }
        temp.wordLengthList.add(s.length());
    }

    /**
     * 构建Fail指针
     *
     * @param root 指定节点
     * @param n    预留字段
     * @param s    预留字段
     */
    public static void buildFailPath(AcNode root, int n, String[] s) {
        Queue<AcNode> queue = new LinkedList<>();
        Map<Character, AcNode> childs = root.children;
        for (Map.Entry<Character, AcNode> next : childs.entrySet()) {
            queue.offer(next.getValue());
            next.getValue().failNode = root;
        }

        while (!queue.isEmpty()) {
            AcNode x = queue.poll();
            childs = x.children;
            for (Map.Entry<Character, AcNode> next : childs.entrySet()) {
                AcNode y = next.getValue();
                AcNode fafail = x.failNode;
                while (fafail != null && (!fafail.children.containsKey(next.getKey()))) {
                    fafail = fafail.failNode;
                }
                if (fafail == null) {
                    y.failNode = root;
                } else {
                    y.failNode = fafail.children.get(next.getKey());
                }
                if (y.failNode.wordLengthList != null) {
                    y.wordLengthList.addAll(y.failNode.wordLengthList);
                }
                queue.offer(y);
            }
        }
    }

    /**
     * 匹配查找
     *
     * @param root 根节点
     * @param n    预留参数
     * @param s    源数据
     */
    public static void query(AcNode root, int n, String s) {
        AcNode temp = root;
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            while (temp.children.get(c[i]) == null && temp.failNode != null) {
                temp = temp.failNode;
            }
            if (temp.children.get(c[i]) != null) {
                temp = temp.children.get(c[i]);
            } else {
                temp = root;
                continue;
            }
            if (temp.wordLengthList.size() != 0) {
                handleMatchWords(temp, s, i);
            }
        }
    }

    /**
     * 匹配对象的处理方式
     *
     * @param node       记录节点
     * @param word       源数据
     * @param currentPos 当前指针
     */
    public static void handleMatchWords(AcNode node, String word, int currentPos) {
        for (Integer wordLen : node.wordLengthList) {
            int startIndex = currentPos - wordLen + 1;
            String matchWord = word.substring(startIndex, currentPos + 1);
            System.out.println("匹配到的敏感词为:" + matchWord + ",其在搜索串中下标为:" + startIndex + "," + currentPos);
        }
    }
}
