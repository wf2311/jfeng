package com.wf2311.jfeng.lang;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wangfeng
 * @time 2017/07/07 14:28.
 */
@Data
public class SysMenuDto extends TreeNode<SysMenuDto, Integer> {
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
    private List<SysMenuDto> children = new ArrayList<>();

    private Integer sequence;

    public String toHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<ol class='dd-list'>");
        sb.append(this._toHtml());
        sb.append("</ol>");
        return sb.toString();
    }

    private String _toHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("<li class='dd-item' data-id='%d'>", this.getId()));
        sb.append(String.format("<div class='dd-handle'>%s</div>", this.getTitle()));
        if (!isLeaf()) {
            sb.append("<ol class='dd-list'>");
            this.children.forEach(node -> sb.append(node._toHtml()));
            sb.append("</ol>");
        }
        sb.append("</li>");
        return sb.toString();
    }

}
