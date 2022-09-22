package com.up3d.link.controller;


import com.up3d.link.service.Up3dProductFunctionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 云甲产品功能表 前端控制器
 * </p>
 *
 * @author 董小眼
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/up3d/up3dProductFunction")
public class Up3dProductFunctionController {
    @Resource
    private Up3dProductFunctionService up3dProductFunctionService;

    @ApiOperation("Excel批量导入数据字典")
    @PostMapping("/import")
    public String batchImport(
            @ApiParam(value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {

        InputStream inputStream = file.getInputStream();
        up3dProductFunctionService.importData(inputStream);
        return "批量导入成功";
    }


}

