package gjxt.portal.api.common.constant;

import lombok.Getter;

/**
 * @author hxy
 * @date 2023/4/17
 */
@Getter
public enum UserEnum {

    ADMIN(1),

    MANAGER(12),

    SALESMAN(11);

    
    private final Integer id;


    UserEnum(int id) {
        this.id=id;
    }
}
