package per.pqr.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * ContractTriggerDefine
 *
 * @author pangqirong
 * @date 2021/4/13
 */
@Setter
@Getter
@Entity
@Schema(description = "合约触发条件定义")
public class ContractTriggerDefine extends PanacheEntity {
    @Schema(title = "参数名字，或条件名称，依据是否为⽗类来决定", required = true)
    private String label;
    @Schema(title = "与枚举类型关联，仅父类有效", required = true)
    private Triggers tag;
    @Schema(title = "作为字段说明时有效，为null表示这是⼀个⽗级")
    private Long parentID;
    @Schema(title = "参数个数：作为⽗类时有⽤")
    private Integer argNum;
    @Schema(title = "参数正则", required = true)
    private String argReg;
    @Schema(title = "正则错误警告", required = true)
    private String argRegAlert;
    @Schema(title = "参数判断规则: 默认值0-表示“等于”，1-表示“包含”", required = true)
    private Integer argJudge;

    @Column(updatable = false)
    @Schema(title = "创建时间", required = true, hidden = true)
    private Long createTime;
    @Schema(title = "更新时间", hidden = true)
    private Long updateTime;
    @Schema(title = "状态", defaultValue = "1", description = "除1外，其他值都表示异常")
    private Integer status;

    /* 确保每次新增数据都自动设置这些值 */
    @Override
    public void persist() {
        long now = System.currentTimeMillis();
        this.updateTime = now;
        this.createTime = now;
        this.status = 1;
        super.persist();
    }

    /* 删除校验 */
    @Override
    public void delete() {
        long count = ContractTrigger.count("triggerID", this.id);
        if (count > 0) {
            throw new RuntimeException("该定义已被引用，无法删除");
        }
        super.delete();
    }

    @Override
    public String toString() {
        return "ContractTriggerDefine{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", tag='" + tag + '\'' +
                ", parentID=" + parentID +
                ", argNum=" + argNum +
                ", argReg='" + argReg + '\'' +
                ", argRegAlert='" + argRegAlert + '\'' +
                ", argJudge=" + argJudge +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
