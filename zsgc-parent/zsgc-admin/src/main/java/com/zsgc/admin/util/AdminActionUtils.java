package com.zsgc.admin.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zsgc.core.model.AdminAction;
import com.zsgc.core.model.AdminActionInfo;

import java.util.List;
import java.util.Map;

public final class AdminActionUtils {

    public static AdminActionInfo buildTree(List<AdminAction> src) {
        AdminActionInfo root = new AdminActionInfo();
        // init root node
        root.setId(0);
        root.setName("ALL");
        root.setLevel(-1);
        root.setPaixu(0);
        root.setUrl("/");
        root.setType(0);

        return process(root, convertToInfoMap(src), root.getId());
    }

    public static AdminActionInfo process(AdminActionInfo root, Map<Integer, List<AdminActionInfo>> map, Integer pid) {
        List<AdminActionInfo> ifs = map.get(pid);
        if (ifs != null && !ifs.isEmpty()) {
            for (AdminActionInfo pi : ifs) {
                AdminActionInfo tempa = process(pi, map, pi.getId());
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
     * @param aas AdminAction list
     * @return map datasource
     */
    public static Map<Integer, List<AdminActionInfo>> convertToInfoMap(List<AdminAction> aas) {
        Map<Integer, List<AdminActionInfo>> map = Maps.newHashMap();

        for (AdminAction a : aas) {
            AdminActionInfo ai = new AdminActionInfo(a);
            List<AdminActionInfo> temp = map.get(ai.getPid());
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
