package per.pqr.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * ContractResource
 *
 * @author pangqirong
 */
@Path("/api/contract")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContractResource {

    // TODO add Hibernate validator

    // part 1 : contract task triggers

    @GET
    @Path("/trigger-types")
    @Operation(summary = "获取触发器列表")
    public List<ContractTriggerDefine> listContractTriggerDefine() {
        return ContractTriggerDefine.list("parentID", (Parameters) null);
    }

    @GET
    @Path("/trigger-args-{triggerID}")
    @Operation(summary = "获取触发器所需参数列表")
    public List<ContractTriggerDefine> listContractTriggerArg(@PathParam("triggerID") long parentID) {
        return ContractTriggerDefine.list("parentID", parentID);
    }

    /* TODO test only; delete while production */
    @POST
    @Path("/trigger-type-add")
    @Operation(summary = "获取触发器所需参数列表", description = "仅用于测试，方便前端调试，生产环境该接口要删除")
    public void addContractTriggerDefine(ContractTriggerDefine define) {
        define.persistAndFlush();
    }

    // part 2 : contract tasks

    @POST
    @Path("/task-add")
    @Operation(summary = "添加合约任务")
    @Transactional
    public ContractTask addContractTask(@Valid ContractTask task) {
        task.persist();
        return task;
    }

    @POST
    @Path("/task-list-{size}-{page}")
    @Operation(summary = "合约任务分页", description = "每页容量0~100；搜索允许条件为：合约ID、执行状态、分片名称。")
    public Page<ContractTask> contractTaskPage(@PathParam("size") int size,
                                               @PathParam("page") int page,
                                               ContractTask search) {
        String sql = "createTime <> null";
        if (search.getContractID() != null) {
            sql += " and contractID = " + search.getContractID();
        }
        if (search.getStatus() != null) {
            sql += " and status = " + search.getStatus();
        }
        if (search.getShard() != null) {
            sql += " and shard = " + search.getShard();
        }
        PanacheQuery<PanacheEntityBase> all = ContractTask.find(sql + " order by createTime DESC");
        PanacheQuery<PanacheEntityBase> range = all.range(page, size);

        Page<ContractTask> result = new Page<>();
        result.setContent(range.list());
        result.setNumber(page);
        result.setSize(size);
        result.setTotal(all.count());

        return result;
    }

    @POST
    @Path("/task-result-{taskID}-{size}-{page}")
    @Operation(summary = "合约任务执行结果分页", description = "每页容量0~100；搜索允许条件为：时间段（毫秒时间戳）")
    public Page<ContractTaskResult> contractTaskResultPage(@PathParam("taskID") Long taskID,
                                                           @PathParam("size") int size,
                                                           @PathParam("page") int page,
                                                           Duration duration) {
        String sql = "taskID = " + taskID;
        if (duration != null && duration.getStart() != null && duration.getEnd() != null) {
            sql += " and createTime between " + duration.getStart() + " and " + duration.getEnd();
        }
        PanacheQuery<PanacheEntityBase> all = ContractTaskResult.find(sql + " order by createTime DESC");
        PanacheQuery<PanacheEntityBase> range = all.range(page, size);

        Page<ContractTaskResult> result = new Page<>();
        result.setContent(range.list());
        result.setNumber(page);
        result.setSize(size);
        result.setTotal(all.count());

        return result;
    }
}
