package com.up3d.link.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.up3d.link.mapper.Up3dProductFunctionMapper;
import com.up3d.link.pojo.entity.Up3dProductFunction;
import com.up3d.link.service.Up3dProductFunctionService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 云甲产品功能表 服务实现类
 * </p>
 *
 * @author 董小眼
 * @since 2022-07-27
 */
@Service
public class Up3dProductFunctionServiceImpl extends ServiceImpl<Up3dProductFunctionMapper, Up3dProductFunction> implements Up3dProductFunctionService {


    @Override
    public void importData(InputStream inputStream) throws IOException {
        //1.创建workbook工作簿
        HSSFWorkbook sheets = new HSSFWorkbook(inputStream);
        //2.获取sheet 从0开始
        Sheet sheet = sheets.getSheetAt(0);
        //循环所有行
        //循环所有行
        for (Row row : sheet) {
            List<Up3dProductFunction> list = new ArrayList<>();
//            System.out.println(row);
            //遍历所有单元格
            for (int i = 3; i < row.getLastCellNum(); i += 2) {
                String key = row.getCell(i).getStringCellValue();
                Up3dProductFunction gmt = new Up3dProductFunction();
                gmt.setUp3dProductId(1);
                gmt.setKey(key);
                gmt.setName(row.getCell(i + 1).getStringCellValue());
                gmt.setGmtCreate(1);
                gmt.setIsDelete(true);
                Integer parentId = 0;
                // 设置parentId
                // 代表此行数据第一个key。设置第一层的父节点0
                if (i == 3) {
                    gmt.setParentId(0);
                } else {
                    //只要不是第一层的，就把父节点设置为它前一个key的id
                    //去数据库查询前一个key的id
                    parentId = baseMapper.selectList(
                            new LambdaQueryWrapper<Up3dProductFunction>()
                                    .eq(Up3dProductFunction::getKey, row.getCell(i - 2).getStringCellValue())).get(0).getId();
                    gmt.setParentId(parentId);
                }

                //数据去重 条件 key && parentId
                List<Up3dProductFunction> up3dProductFunctions = baseMapper.selectList(
                        new LambdaQueryWrapper<Up3dProductFunction>()
                                .eq(Up3dProductFunction::getKey, key).eq(Up3dProductFunction::getParentId, parentId));
                if (up3dProductFunctions.size() == 0) {
                    //插入数据
                    baseMapper.insert(gmt);
                }
            }
        }
    }

//    @Override
//    public Up3dProductFunction getByParentIdAndKey(Integer parentId, String up3dKey) {
//        return baseMapper.selectList(new QueryWrapper<Up3dProductFunction>()
//                .eq("parent_id",parentId)
//                .eq("key",up3dKey)).get(0);
//    }
}
