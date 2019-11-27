package com.example.watcherdemo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * watcher监听多次
 */
public class Main2 implements Watcher{
    static ZooKeeper zooKeeper;

    static {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 4000, new Main2());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String path="/watcher";
        if(zooKeeper.exists(path,false)==null){
            zooKeeper.create("/watcher","hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        zooKeeper.exists(path,true);
        System.in.read();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("eventType:"+watchedEvent.getType());
        if (watchedEvent.getType()== Event.EventType.NodeDataChanged){
            try {
                zooKeeper.exists(watchedEvent.getPath(),true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
