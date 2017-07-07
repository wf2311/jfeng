package com.wf2311.jfeng.lang;

import java.util.List;

/**
 * @author wf2311
 */
interface Tree<T, S> {
    S getId();

    S getParentId();

    List<T> getChildren();

    void setChildren(List<T> list);
}
