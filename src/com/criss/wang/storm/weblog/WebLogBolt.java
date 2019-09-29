package com.criss.wang.storm.weblog;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

public class WebLogBolt implements IRichBolt{
	private int line_num = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1295728828847022846L;

	@Override
	public void cleanup() {
		// 清除资源
	}

	@Override
	public void execute(Tuple input) {
		// 执行
		// 获取数据,"log"参数特指是从标识为“log”的spout获取数据源
		input.getStringByField("log");
		
		String line = input.getString(0); // 索引是0的数据
		while(line == null || "".equals(line)) {
			return;
		}
		// 数据切割
		String[] split = line.split("\t");
		String session_id = split[1];
		// 统计发送行数
		line_num++;
		// 打印
		System.out.println(Thread.currentThread().getId() + ",session_id:" + session_id + ",lineNum;"+line_num);
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// 准备
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// 声明
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
