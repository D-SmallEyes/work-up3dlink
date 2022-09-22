package com.up3d.link.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.up3d.link.pojo.entity.Up3dProductFunction;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 云甲产品功能表 服务类
 * </p>
 *
 * @author 董小眼
 * @since 2022-07-27
 */
public interface Up3dProductFunctionService extends IService<Up3dProductFunction> {

    void importData(InputStream inputStream) throws IOException;

    //Up3dProductFunction getByParentIdAndKey(Integer parentId,String up3dKey);
}
