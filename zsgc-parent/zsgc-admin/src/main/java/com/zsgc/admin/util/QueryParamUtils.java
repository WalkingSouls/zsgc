package com.zsgc.admin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.calanger.common.util.UrlUtils;

public final class QueryParamUtils {
    public static String serialize(Map<String, String> params) {
        return serialize(params, false);
    }

    public static String serialize(Map<String, String> params, boolean needEncode) {
        StringBuilder sb = new StringBuilder();
        boolean firstElement = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (firstElement) {
                firstElement = false;
            } else {
                sb.append("&");
            }
            if (needEncode) {
                sb.append(UrlUtils.encode(entry.getKey())).append("=").append(UrlUtils.encode(entry.getValue()));
            } else {
                sb.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        return sb.toString();
    }

    public static String sortAndSerialize(Map<String, String> params) {
        return sortAndSerialize(params, false);
    }

    public static String sortAndSerialize(Map<String, String> params, boolean needEncode) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        boolean firstElement = true;
        for (String key : keys) {
            if (firstElement) {
                firstElement = false;
            } else {
                sb.append("&");
            }
            if (needEncode) {
                sb.append(UrlUtils.encode(key)).append("=").append(UrlUtils.encode(params.get(key)));
            } else {
                sb.append(key).append("=").append(params.get(key));
            }
        }

        return sb.toString();
    }

    private QueryParamUtils() {
    }
}
