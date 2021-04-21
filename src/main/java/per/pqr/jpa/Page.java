package per.pqr.jpa;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

/**
 * Page
 *
 * @author pangqirong
 * @date 2021/4/13
 */
@Data
@Schema(description = "分页器")
public class Page<T> {

    @Schema(description = "总页数")
    private Integer totalPages;
    @Schema(description = "当前页码")
    private Integer number;
    @Schema(description = "每页容量")
    private Integer size;
    @Schema(description = "列表内容")
    private List content;
    @Schema(description = "是否首页")
    private Boolean isFirst;
    @Schema(description = "是否末页")
    private Boolean isLast;
    @Schema(description = "是否有下一页")
    private Boolean hasNext;
    @Schema(description = "总条数")
    private Long total;

    public Page fromJpaPage(org.springframework.data.domain.Page p) {
        this.totalPages = p.getTotalPages();
        this.number     = p.getNumber()    ;
        this.size       = p.getSize()      ;
        this.content    = p.getContent()   ;
        this.isFirst    = p.isFirst()      ;
        this.isLast     = p.isLast()       ;
        this.hasNext    = p.hasNext()      ;
        this.total      = p.getTotalElements();

        return this;
    }
}
