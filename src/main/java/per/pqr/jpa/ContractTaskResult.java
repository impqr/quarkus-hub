package per.pqr.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * ContractTaskResult
 *
 * @author pangqirong
 */
@Setter
@Getter
@Entity
@Schema(title = "合约任务")
public class ContractTaskResult extends PanacheEntity {

    @Schema(title = "关联任务")
    private Long taskID;
    @Schema(title = "执行结果")
    private String remark;
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
        return "ContractTaskResult{" +
                "id=" + id +
                ", taskID=" + taskID +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
