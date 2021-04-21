package per.pqr.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * ContractTask
 *
 * @author pangqirong
 */
@Setter
@Getter
@Entity
@Schema(title = "合约任务")
public class ContractTask extends PanacheEntity {
    @Schema(title = "合约ID")
    @NotBlank(message = "合约ID不能为空")
    private String contractID;

    @Schema(title = "分片名称")
    @NotBlank(message = "分片名称不能为空")
    private String shard;

    @Schema(title = "执行次数", description = "最多为999次")
    @Min(value = 1, message = "执行次数需大于0")
    @Max(value = 1000, message = "执行次数需小于1000")
    private Integer cycle;

    @Schema(title = "已执行次数")
    private Integer execute;

    @Schema(title = "执行间隔", description = "填写内容为秒数")
    @Min(value = 1, message = "执行间隔需大于0秒")
    @Max(value = 86400, message = "执行间隔需小于86400秒（一天）")
    private Integer cron;

    @Schema(title = "开始时间（延时启动）")
    private Long startTime;

    @Schema(title = "停止条件")
    private Long stopTriggerID;

    @Schema(title = "执行条件")
    private Long startTriggerID;

    @Column(updatable = false)
    @Schema(title = "创建时间", required = true, hidden = true)
    private Long createTime;
    @Schema(title = "更新时间", hidden = true)
    private Long updateTime;
    @Schema(title = "状态", defaultValue = "0", description = "0-未执⾏，1-执⾏中，2-已结束")
    private Integer status;

    @Override
    public void persist() {
        long now = System.currentTimeMillis();
        this.updateTime = now;
        this.createTime = now;
        this.status = 0;
        super.persist();
    }

    @Override
    public String toString() {
        return "ContractTask{" +
                "id=" + id +
                ", contractID='" + contractID + '\'' +
                ", shard='" + shard + '\'' +
                ", cycle=" + cycle +
                ", execute=" + execute +
                ", cron=" + cron +
                ", startTime=" + startTime +
                ", stopTriggerID=" + stopTriggerID +
                ", startTriggerID=" + startTriggerID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
