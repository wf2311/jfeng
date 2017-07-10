package com.wf2311.jfeng.lang;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wangfeng
 * @time 2017/07/07 14:28.
 */
@Data
public class SysMenuDto extends TreeNode<SysMenuDto,Integer> {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 上级id
     */
    private Integer parentId;


    private String parentTitle;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 访问地址
     */
    private String href;

    /**
     * 菜单显示图标
     */
    private String icon;

    /**
     * 菜单是否默认展开
     */
    private Boolean spread;

    /**
     * 下级菜单
     */
    private List<SysMenuDto> children=new ArrayList<>();

    private Integer sequence;

}
