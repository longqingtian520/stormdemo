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
		// �����Դ
	}

	@Override
	public void execute(Tuple input) {
		// ִ��
		// ��ȡ����,"log"������ָ�Ǵӱ�ʶΪ��log����spout��ȡ����Դ
		input.getStringByField("log");
		
		String line = input.getString(0); // ������0������
		while(line == null || "".equals(line)) {
			return;
		}
		// �����и�
		String[] split = line.split("\t");
		String session_id = split[1];
		// ͳ�Ʒ�������
		line_num++;
		// ��ӡ
		System.out.println(Thread.currentThread().getId() + ",session_id:" + session_id + ",lineNum;"+line_num);
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// ׼��
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// ����
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
