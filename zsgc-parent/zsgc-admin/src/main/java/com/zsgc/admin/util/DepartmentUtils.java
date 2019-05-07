package com.zsgc.admin.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zsgc.core.model.Department;
import com.zsgc.core.model.DepartmentInfo;

import java.util.List;
import java.util.Map;

public final class DepartmentUtils {

    public static DepartmentInfo buildTree(List<Department> src) {
        DepartmentInfo root = new DepartmentInfo();
        root.setId(0);
        root.setDepartment("ROOT");
        root.setPid(-1);

        return process(root, convertToInfoMap(src), root.getId());
    }

    /**
     * build permission tree
     * @param root root node
     * @param map datasource to processs
     * @param pid element parent id
     * @return the tree node which contain some children
     */
    public static DepartmentInfo process(DepartmentInfo root, Map<Integer, List<DepartmentInfo>> map, Integer pid) {
        List<DepartmentInfo> ifs = map.get(pid);
        if (ifs != null && !ifs.isEmpty()) {
            for (DepartmentInfo pi : ifs) {
                DepartmentInfo tempa = process(pi, map, pi.getId());
                if (tempa != null) {
                    root.addChild(tempa);
                } else {
                    // do nothing
                }
            }
        }
        return root;
    }

    /**
     * for build a tree
     * @param aas Department list
     * @return map datasource
     */
    public static Map<Integer, List<DepartmentInfo>> convertToInfoMap(List<Department> aas) {
        Map<Integer, List<DepartmentInfo>> map = Maps.newHashMap();

        for (Department a : aas) {
            DepartmentInfo ai = new DepartmentInfo(a);
            List<DepartmentInfo> temp = map.get(ai.getPid());
            // map has no such mapping
            if (temp == null) {
                temp = Lists.newArrayList();
                temp.add(ai);
                map.put(ai.getPid(), temp);
                continue;
            }

            // map has such mapping
            temp.add(ai);
        }
        return map;
    }
}
