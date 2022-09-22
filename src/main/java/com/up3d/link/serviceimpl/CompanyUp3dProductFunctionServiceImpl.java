package com.up3d.link.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.up3d.link.common.Enum.CompanyUp3dProductFunctionEnum;
import com.up3d.link.common.Enum.SysProductEnum;
import com.up3d.link.common.result.Result;
import com.up3d.link.mapper.CompanyUp3dProductFunctionMapper;
import com.up3d.link.pojo.entity.CompanyUp3dProductFunction;
import com.up3d.link.service.CompanyUp3dProductFunctionService;
import com.up3d.link.service.SysProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-07-28  14:18
 */
@Service
public class CompanyUp3dProductFunctionServiceImpl extends ServiceImpl<CompanyUp3dProductFunctionMapper, CompanyUp3dProductFunction> implements CompanyUp3dProductFunctionService {

    @Resource
    private CompanyUp3dProductFunctionMapper companyUp3dProductFunctionMapper;

    /**
     * 获取公司商品权限级别的所有信息
     * @return
     */
    @Override
    public List<CompanyUp3dProductFunction> getCompanyUp3dProductFunctionAndChild() {

        Map<String, Object> map = new TreeMap<>();

        QueryWrapper<CompanyUp3dProductFunction> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        List<CompanyUp3dProductFunction> up3dList = companyUp3dProductFunctionMapper.selectList(wrapper);

        for (CompanyUp3dProductFunction up3d : up3dList) {
            up3d.setCompanyUp3dPFChildList(getChildrenList(up3d.getId()));

        }
        return up3dList;
    }


    @Autowired
    private SysProductService sysProductService;

    /**
     * 查询所有子类型数据
     * @param id
     * @return
     */
    private List<CompanyUp3dProductFunction> getChildrenList(Integer id) {
        // 通过id查询对应的子节点
        QueryWrapper<CompanyUp3dProductFunction> childWrapper = new QueryWrapper<>();
        childWrapper.eq("parent_id", id);
        List<CompanyUp3dProductFunction> childrenList = companyUp3dProductFunctionMapper.selectList(childWrapper);
        if (!ObjectUtils.isEmpty(childrenList)) {
            for (CompanyUp3dProductFunction up3d : childrenList) {
                up3d.setCompanyUp3dPFChildList(getChildrenList(up3d.getId()));
            }
        }
        return childrenList;
    }

    /**
     * 数据思路：
     *   Map<String, Object> map0 = new HashMap<>();
     *
     *         Map<String, Object> map = new HashMap<>();
     *         map.put("startTime",1);
     *         map.put("endTime",2);
     *         Map<String, Object> map1 = new HashMap<>();
     *         map1.put("4axiesHalf",map);
     *         map1.put("4axiesWhole",map);
     *
     *         Map<String, Object> map2 = new HashMap<>();
     *         map2.put("4axies",map1);
     *
     *         Map<String, Object> map3 = new HashMap<>();
     *         map3.put("axies",map2);
     *
     *
     *         Map<String, Object> map4 = new HashMap<>();
     *         map4.put("startTime",3);
     *         map4.put("endTime",4);
     *
     *         Map<String, Object> map5 = new HashMap<>();
     *         map5.put("start",map4);
     *
     *
     *         map3.put("base",map5);
     *
     *         map0.put("module",map3);
     * @return
     */
    @Override
    public List<Map<String, Object>> getResult() {
        List<CompanyUp3dProductFunction> companyUp3dPFList =
                companyUp3dProductFunctionMapper.selectList(
                        new LambdaQueryWrapper<CompanyUp3dProductFunction>()
                                .eq(CompanyUp3dProductFunction::getCompanyId, 1)
                                .eq(CompanyUp3dProductFunction::getUp3dProductId,1)
                                .eq(CompanyUp3dProductFunction::getStatus,1)
                                .eq(CompanyUp3dProductFunction::getParentId,0));
        return companyUp3dPFList.stream().map(companyUp3dPF -> {
            Map<String, Object> map = new HashMap<>();
            map.put(companyUp3dPF.getKey(), leavesData(companyUp3dPF));
            return map;
        }).collect(Collectors.toList());
    }

    private List<Object> getLeaves(CompanyUp3dProductFunction companyUp3dPF){
        // 通过id查询对应的子节点
        QueryWrapper<CompanyUp3dProductFunction> childWrapper = new QueryWrapper<>();
        childWrapper.eq("parent_id", companyUp3dPF.getId());
        List<CompanyUp3dProductFunction> childrenList = companyUp3dProductFunctionMapper.selectList(childWrapper);
        if (!ObjectUtils.isEmpty(childrenList)) {
            ArrayList<Object> keyList = new ArrayList<>();
            for (CompanyUp3dProductFunction up3d : childrenList) {
                Map<String, Object> map = new HashMap<>();
                map.put(up3d.getKey(), getLeaves(up3d));
                keyList.add(map);
            }
            return keyList;
        } else {
            // 添加时间数据
            ArrayList<Object> timeList = new ArrayList<>();
            Map<String, Object> leavesMap = new HashMap<>();
            leavesMap.put("startTime",companyUp3dPF.getStartTime());
            leavesMap.put("endTime",companyUp3dPF.getEndTime());
            timeList.add(leavesMap);
            return timeList;
        }
    }


    public Map<String, Object> leavesData(CompanyUp3dProductFunction companyUp3dPF){

        Map<String, Object> leaveMap = new HashMap<>();

        List<CompanyUp3dProductFunction> companyUp3dPFList =
                companyUp3dProductFunctionMapper.selectList(
                        new LambdaQueryWrapper<CompanyUp3dProductFunction>()
                                .eq(CompanyUp3dProductFunction::getCompanyId, 1)
                                .eq(CompanyUp3dProductFunction::getUp3dProductId,1)
                                .eq(CompanyUp3dProductFunction::getStatus,1)
                                .eq(CompanyUp3dProductFunction::getParentId,companyUp3dPF.getId()));
        if (!ObjectUtils.isEmpty(companyUp3dPFList)){
            for (CompanyUp3dProductFunction companyUp3dPFChildren : companyUp3dPFList) {

                leaveMap.put(companyUp3dPFChildren.getKey(),leavesData(companyUp3dPFChildren));
            }
            return leaveMap;
        }else {
            //达到叶子节点 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("startTime",companyUp3dPF.getStartTime());
            map.put("endTime",companyUp3dPF.getEndTime());
            return map;
        }
    }
    /**
     * 级联数据展示
     * @return
     */
    public Map<String, Map<String, Object>> getResult1(){


        return null;
    }

    public Map<String, Object> getLeaves(Integer parentId){
        Map<String, Object> map= new HashMap<>();
        List<CompanyUp3dProductFunction> companyUp3dPFAndChild = getCompanyUp3dProductFunctionAndChild();
        for (CompanyUp3dProductFunction companyUp3dProductFunction : companyUp3dPFAndChild) {
            if (!ObjectUtils.isEmpty(companyUp3dProductFunction.getCompanyUp3dPFChildList())){
                getLeaves(companyUp3dProductFunction.getId());
            }else{
                Map<String, Object> leaveMap= new HashMap<>();
                leaveMap.put("startTime", companyUp3dProductFunction.getStartTime());
                leaveMap.put("endTime", companyUp3dProductFunction.getEndTime());
                map.put(companyUp3dProductFunction.getKey(),leaveMap);
            }
        }
        return map;
    }


    /**
     * 无级别数据展示
     * @return
     */
    public Map<String, Object> getChildResult(Integer up3dProductId, Integer companyId) {


        //参数校验
        if (up3dProductId == null && companyId == null){
            throw new RuntimeException("参数异常");
        }
        List<CompanyUp3dProductFunction> companyUp3dProductFunctions = null;
        //无级别 展示
        companyUp3dProductFunctions =
                companyUp3dProductFunctionMapper.selectList(
                        new LambdaQueryWrapper<CompanyUp3dProductFunction>()
                                .eq(CompanyUp3dProductFunction::getCompanyId, companyId)
                                .eq(CompanyUp3dProductFunction::getUp3dProductId, up3dProductId)
                                .eq(CompanyUp3dProductFunction::getStatus, CompanyUp3dProductFunctionEnum.NORMAL.getStatus()));

        //非空判断
        if (ObjectUtils.isEmpty(companyUp3dProductFunctions)){
            return null;
        }
        Map<String,Object> rootMap = new HashMap<>();
        Map<String,Object> childrenMap = new HashMap<>();
        for (CompanyUp3dProductFunction companyUp3dProductFunction : companyUp3dProductFunctions) {
            Map<String, Object> childMap  = new HashMap<>();
            childMap.put("startTime",companyUp3dProductFunction.getStartTime());
            childMap.put("endTime",companyUp3dProductFunction.getEndTime());
            //获取数量
            Integer count = companyUp3dProductFunction.getCount();
            if (count != null && count != 0 ){
                childMap.put("count",count);
            }
            childrenMap.put(companyUp3dProductFunction.getKey(),childMap);
        }
        rootMap.put("module",childrenMap);
        return rootMap;
    }

    /**
     * 获取系统服务的时间
     *
     * @param up3dProductId
     * @return
     */
    @Override
    public Result getCompanyUp3dProductFunctionDate(Integer up3dProductId) {
        //token中获取公司名
        Integer companyId = 1;

        Map<String, Object> result = new HashMap<>();
        result.put("user","用户id");
        //系统时间戳 单位秒
        Long l = System.currentTimeMillis();
        result.put("time",l.intValue()/1000);
        if (up3dProductId != null){
            Map<String, Object> companyUp3dPF = getChildResult(up3dProductId,companyId);
            result.put(SysProductEnum.getMsgByCode(up3dProductId).toLowerCase(Locale.ROOT),companyUp3dPF);
        }else {
            SysProductEnum[] values = SysProductEnum.values();
            for (SysProductEnum value : values) {
                Map<String, Object> companyUp3dPF = getChildResult(value.getCode(),companyId);
                if (companyUp3dPF == null){
                    continue;
                }
                result.put(SysProductEnum.getMsgByCode(value.getCode()).toLowerCase(Locale.ROOT),companyUp3dPF);
            }
        }
        return Result.success(result);
    }



}
