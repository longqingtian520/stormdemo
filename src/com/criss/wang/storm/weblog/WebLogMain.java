package com.criss.wang.storm.weblog;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class WebLogMain {

	public static void main(String[] args) {
		// 创建拓扑
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("weblogspout", new WebLogSpout(), 1); // 數字1表示有几个线程读这个spout数据
		builder.setBolt("weblogbolt", new WebLogBolt(), 1).shuffleGrouping("weblogspout");
		
		// 创建配置信息对象,配置worker開啟數量
		Config config = new Config();
		config.setNumWorkers(2);
		
		// 提交程序
		if(args.length > 0) {// 集群提交
			try {
				StormSubmitter.submitTopology(args[0], config, builder.createTopology());
			} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
				e.printStackTrace();
			}
		}else {// 本地提交
			LocalCluster localCluster = new LocalCluster();
			localCluster.submitTopology("weblogtopology", config, builder.createTopology());
		}
		
	}
}
