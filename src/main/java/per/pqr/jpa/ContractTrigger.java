package per.pqr.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * ContractTrigger
 *
 * @author pangqirong
 * @date 2021/4/13
 */
@Setter
@Getter
@Entity
@Schema(title = "触发条件记录")
public class ContractTrigger extends PanacheEntity {
    @Schema(title = "对应的触发器定义", required = true)
    @NotBlank(message = "必须指定触发器的定义ID")
    private Long triggerID;

    @Schema(title = "参数值", required = true)
    @NotBlank(message = "必须设定参数值")
    private String triggerValue;

    @Schema(title = "关联的合约任务", required = true)
    @NotBlank(message = "必须关联合约任务ID")
    private Long taskID;

    @Column(updatable = false)
    @Schema(title = "创建时间", required = true, hidden = true)
    private Long createTime;
    @Schema(title = "更新时间", hidden = true)
    private Long updateTime;
    @Schema(title = "状态", defaultValue = "1", description = "除1外，其他值都表示异常")
    private Integer status;

    @Override
    public void persist() {
        long now = System.currentTimeMillis();
        this.updateTime = now;
        this.createTime = now;
        this.status = 1;
        super.persist();
    }

    @Override
    public String toString() {
        return "ContractTrigger{" +
                "id=" + id +
                ", triggerID=" + triggerID +
                ", triggerValue='" + triggerValue + '\'' +
                ", taskID=" + taskID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
