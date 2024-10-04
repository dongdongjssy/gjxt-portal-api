package gjxt.portal.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Data
@ToString
@AllArgsConstructor
public class GjxtCertificateSearchCriteria {
    private String searchIdNumber;
    private String searchRealName;
    private String searchCertNumber;

    public boolean checkCriteria() {
        if (StringUtils.isAllBlank(this.searchCertNumber, this.searchIdNumber, this.searchRealName)) {
            return false;
        }

        return true;
    }
}
