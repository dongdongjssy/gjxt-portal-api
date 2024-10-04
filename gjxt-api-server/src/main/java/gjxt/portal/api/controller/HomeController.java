package gjxt.portal.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.dto.ScholarshipParam;
import gjxt.portal.api.entity.*;
import gjxt.portal.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hxy
 * @date 2023/5/15
 */
@RestController
@RequestMapping("api/home")
public class HomeController extends BaseController{

    @Autowired
    private ClientUserService clientUserService;

    @Autowired
    private GjxtHighScholarshipService gjxtHighScholarshipService;

    @Autowired
    private GjxtSecondaryVocationalScholarshipService secondaryVocationalScholarshipService;

    @Autowired
    private GjxtPostgraduateScholarshipService postgraduateScholarshipService;

    @Autowired
    private GjxtClientUserCertService gjxtClientUserCertService;

    /**
     * 我的证书
     * @return
     */
    @PostMapping("myCertHome")
    public R myCert(@RequestBody Map<String,Object> map){
        Long userIdFromHeader = getUserIdFromHeader();
        ClientUser clientUserById = clientUserService.getById(userIdFromHeader);
        map.put("identityId",clientUserById.getIdNumber());
        IPage<GjxtClientUserCert> iPage=gjxtClientUserCertService.queryPage(getPage(map), map);
        Map res=new HashMap();
//        List list=new ArrayList();
//        iPage.getRecords().forEach(a->{
//            GjxtCertificate cert=new GjxtCertificate();
//            if(a.getCertEducationLevel()==0){
//                GjxtSecondaryVocationalScholarship byId = secondaryVocationalScholarshipService.getById(a.getCertId());
//                cert.setCertNumber(byId.getCertNumber());
//                cert.setCertIssueYear(byId.getStuYear());
//            }
//            if(a.getCertEducationLevel()==2){
//                GjxtPostgraduateScholarship byId = postgraduateScholarshipService.getById(a.getCertId());
//                cert.setCertNumber(byId.getCertNumber());
//                cert.setCertIssueYear(byId.getStuYear());
//            }
//
//            if(a.getCertEducationLevel()==1){
//                GjxtHighScholarship byId = gjxtHighScholarshipService.getById(a.getCertId());
//                cert.setCertNumber(byId.getCertNumber());
//                cert.setCertIssueYear(byId.getStuYear());
//            }
//            cert.setCertEducationLevel(a.getCertEducationLevel());
//            list.add(cert);
//
//        });
        res.put("user",clientUserById);
        res.put("cert",iPage);
        return R.ok(res);
    }

    /**
     * 获奖名单
     * @return
     */
    @PostMapping("scholarshipList")
    public R scholarshipList(@RequestBody ScholarshipParam scholarshipParam){
        if(scholarshipParam.getCertEducationLevel()==0){
            IPage<GjxtSecondaryVocationalScholarship> byId = secondaryVocationalScholarshipService.queryPage(scholarshipParam.getPage(), JSONObject.parseObject(JSONObject.toJSONString(scholarshipParam),Map.class));
            return R.ok(byId);
        }
        if(scholarshipParam.getCertEducationLevel()==1){
            IPage<GjxtHighScholarship> byId = gjxtHighScholarshipService.queryPage(scholarshipParam.getPage(), JSONObject.parseObject(JSONObject.toJSONString(scholarshipParam),Map.class));;
            return R.ok(byId);
        }
        if(scholarshipParam.getCertEducationLevel()==2){
            IPage<GjxtPostgraduateScholarship> byId = postgraduateScholarshipService.queryPage(scholarshipParam.getPage(), JSONObject.parseObject(JSONObject.toJSONString(scholarshipParam),Map.class));;
            return R.ok(byId);
        }

        return R.error("查询条件有误");
    }

}


