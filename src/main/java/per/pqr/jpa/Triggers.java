package per.pqr.jpa;

/**
 * Triggers
 *
 * @author pangqirong
 */
public enum Triggers {
    NONE("无条件"),
    HAS_TOKEN("满足某记账单位个数"),
    HAS_NFT("拥有指定非同质化记账单位"),
    STATUS_EQUAL("状态值为指定值"),
    ES_STATISTICS("ES统计的值达到预期"),
    HTTP_SUCCESS("访问URL成功"),
    HTTP_HEADER("HTTP请求响应头包含指定值"),
    HTTP_BODY("HTTP请求响应体包含指定内容");

    private final String description;

    Triggers(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
