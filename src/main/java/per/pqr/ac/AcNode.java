package per.pqr.ac;

import java.util.*;

public class AcNode {
    Map<Character, AcNode> children = new HashMap<>();
    AcNode failNode;
    Set<Integer> wordLengthList = new HashSet<>();
}
