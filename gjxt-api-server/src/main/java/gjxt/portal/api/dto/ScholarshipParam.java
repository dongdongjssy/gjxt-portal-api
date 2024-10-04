package gjxt.portal.api.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author hxy
 * @date 2023/5/15
 */

@Data
public class ScholarshipParam {

    private String name;

    private String province;

    private String schoolName;

    private Page page;

    private String stuYear;

    private Integer certEducationLevel;

}
