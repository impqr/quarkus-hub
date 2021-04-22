package per.pqr.ac;

import org.junit.Test;

public class AcTest {

    String src = "毛泽东是中国共产党最伟大的领导人";

    @Test
    public void testAc() {
        AcNode root = new AcNode();
        AcUtil.insert(root, "毛泽东");
        AcUtil.insert(root, "共产党");
        AcUtil.query(root, 2, src);
    }
}
