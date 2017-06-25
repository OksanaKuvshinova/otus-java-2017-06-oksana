package ru.oksana.l41;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        installGCMonitoring();

        MemoryLeaker ml = new MemoryLeaker();

        long startTime = System.currentTimeMillis();

        while (true) {
            ml.doWork();
            long checkTime = System.currentTimeMillis();
            System.out.println("Milliseconds passed: " + (checkTime - startTime));
        }
    }

//VM options -Xmx512m -Xms512m
//VM options -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000, suspend=n
//VM options  -javaagent:target/L2.1.jar
//VM options  -XX:+UseSerialGC


    private static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            System.out.println(gcbean.getName());

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    long duration = info.getGcInfo().getDuration();
                    String gctype = info.getGcAction();

                    System.out.println(gctype + ": - "
                            + info.getGcInfo().getId() + ", "
                            + info.getGcName()
                            + " (from " + info.getGcCause() + ") " + duration + " milliseconds");

                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }

}
