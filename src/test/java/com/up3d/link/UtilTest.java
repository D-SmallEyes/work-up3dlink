package com.up3d.link;

import com.up3d.link.common.util.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-22  16:33
 * @Description: 工具测试
 */
public class UtilTest {

    @Test
    public void test0(){
        int year = DateUtils.getYear(new Date());
        System.out.println(year%100);
    }
}
