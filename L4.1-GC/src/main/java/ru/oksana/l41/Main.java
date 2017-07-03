package ru.oksana.l41;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//VM options -Xmx512m -Xms512m
public class Main {

    public static void main(String[] args) throws InterruptedException {

        installGCMonitoring();

        MemoryLeaker ml = new MemoryLeaker();

        while (true) {
            ml.doWork();
        }
    }

    private static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

        Map<String, GCInfoAggregator> gcInfoAggregators = initGcInfoAggregators(gcbeans);

        for (GarbageCollectorMXBean gcBean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            System.out.println(gcBean.getName());

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    gcInfoAggregators
                            .get(info.getGcName())
                            .collect(info.getGcInfo());
                    System.out.println(
                            gcInfoAggregators.get(info.getGcName())
                    );
                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }

    private static Map<String, GCInfoAggregator> initGcInfoAggregators(List<GarbageCollectorMXBean> gcbeans) {
        Map<String, GCInfoAggregator> gcInfoAggregators = new HashMap<>();
        long currentTime = System.currentTimeMillis();
        for (GarbageCollectorMXBean gcBean : gcbeans) {
            gcInfoAggregators.put(gcBean.getName(), new GCInfoAggregator(gcBean.getName(), currentTime));
        }
        return gcInfoAggregators;
    }

}
