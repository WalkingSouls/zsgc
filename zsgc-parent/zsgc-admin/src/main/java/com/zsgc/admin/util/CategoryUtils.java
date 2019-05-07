package com.zsgc.admin.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zsgc.core.model.Category;
import com.zsgc.core.model.CategoryInfo;

import java.util.List;
import java.util.Map;

public final class CategoryUtils {

    public static CategoryInfo buildTree(List<Category> src, CategoryInfo root) {
        if (root == null) {
            root = new CategoryInfo();
            root.setId(0);
            root.setName("ALL");
        }

        return process(root, convertToInfoMap(src), root.getId());
    }

    /**
     * build permission tree
     * @param root root node
     * @param map datasource to processs
     * @param pid element parent id
     * @return the tree node which contain some children
     */
    public static CategoryInfo process(CategoryInfo root, Map<Integer, List<CategoryInfo>> map, Integer pid) {
        List<CategoryInfo> ifs = map.get(pid);
        if (ifs != null && !ifs.isEmpty()) {
            for (CategoryInfo pi : ifs) {
                CategoryInfo tempa = process(pi, map, pi.getId());
                if (tempa != null) {
                    root.addChild(tempa);
                }
            }
        }
        return root;
    }

    /**
     * for build a tree
     * @param cs Category list
     * @return map datasource
     */
    public static Map<Integer, List<CategoryInfo>> convertToInfoMap(List<Category> cs) {
        Map<Integer, List<CategoryInfo>> map = Maps.newHashMap();

        for (Category c : cs) {
            CategoryInfo ci = new CategoryInfo(c);
            List<CategoryInfo> temp = map.get(ci.getPid());
            // map has no such mapping
            if (temp == null) {
                temp = Lists.newArrayList();
                temp.add(ci);
                map.put(ci.getPid(), temp);
                continue;
            }

            // map has such mapping
            temp.add(ci);
        }
        return map;
    }
}
