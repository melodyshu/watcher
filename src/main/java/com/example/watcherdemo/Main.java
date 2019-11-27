package com.example.watcherdemo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * watcher只监听一次
 */
public class Main implements Watcher{
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181", 4000, new Main());
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
    }
}
