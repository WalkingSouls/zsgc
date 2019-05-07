package com.zsgc.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.base.Strings;
import com.zsgc.core.dao.SequenceDAO;
import com.zsgc.core.model.SequenceRange;

public final class Sequences {
    private static final Lock lock = new ReentrantLock();

    private static ConcurrentMap<String, SequenceRange> sequenceRangeMap = new ConcurrentHashMap<String, SequenceRange>();

    private static SequenceDAO sequenceDAO;

    public static void setSequenceDAO(SequenceDAO sequenceDAO) {
        Sequences.sequenceDAO = sequenceDAO;
    }

    public static long nextValue(String name) {
        if (!sequenceRangeMap.containsKey(name)) {
            lock.lock();
            try {
                sequenceRangeMap.putIfAbsent(name, sequenceDAO.nextRange(name));
            } finally {
                lock.unlock();
            }
        }

        long value = sequenceRangeMap.get(name).getAndIncrement();
        if (value == -1) {
            lock.lock();
            try {
                for (;;) {
                    if (sequenceRangeMap.get(name).isOverflow()) {
                        sequenceRangeMap.replace(name, sequenceDAO.nextRange(name));
                    }
                    value = sequenceRangeMap.get(name).getAndIncrement();
                    if (value == -1) {
                        continue;
                    }
                    break;
                }
            } finally {
                lock.unlock();
            }
        }

        return value;
    }

    public static String getNo(String name) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(new Date()));
        sb.append(Strings.padStart(String.valueOf(nextValue(name) % 100000000), 8, '0'));
        return sb.toString();
    }

    private Sequences() {
    }
}
