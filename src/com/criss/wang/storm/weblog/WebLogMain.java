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
		// ��������
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("weblogspout", new WebLogSpout(), 1); // ����1��ʾ�м����̶߳����spout����
		builder.setBolt("weblogbolt", new WebLogBolt(), 1).shuffleGrouping("weblogspout");
		
		// ����������Ϣ����,����worker�_������
		Config config = new Config();
		config.setNumWorkers(2);
		
		// �ύ����
		if(args.length > 0) {// ��Ⱥ�ύ
			try {
				StormSubmitter.submitTopology(args[0], config, builder.createTopology());
			} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
				e.printStackTrace();
			}
		}else {// �����ύ
			LocalCluster localCluster = new LocalCluster();
			localCluster.submitTopology("weblogtopology", config, builder.createTopology());
		}
		
	}
}
