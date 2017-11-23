import java.util.logging.Logger;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class QueueManager {

	Logger logger = Logger.getLogger("WMQ_TEST");
//	public static void main(String[] args) {
//		QueueManager queueManager = new QueueManager();
//		queueManager.getQueueDepth();
//
//	}
	public String getQueueDepth() 
	{	
		logger.info("Inside METHOD :---------------------------------");
		int depth = 0;
		MQQueueManager qMgr; // define a queue manager object
		String mqHost = "host";
		String mqPort = "port";
		String mqChannel = "channel";
		String mqQMgr = "queueManager";
		String mqQueue = "queueName";
		try {
		    // Set up MQSeries environment
		   MQEnvironment.hostname = mqHost;
		   MQEnvironment.port = Integer.valueOf(mqPort).intValue();
		   MQEnvironment.channel = mqChannel;
		   MQEnvironment.userID="username";
		   MQEnvironment.password="passwor";
		   MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);
		   logger.info("________________________________________________________");
		   qMgr = new MQQueueManager(mqQMgr);
		   logger.info("________________________________________________________");

		   int openOptions = MQC.MQOO_INQUIRE;
		   MQQueue destQueue = qMgr.accessQueue(mqQueue, openOptions);
		   depth = destQueue.getCurrentDepth();
		   logger.info("Queue Deplth:" + mqQueue + " : " +depth);
		   destQueue.close();
		   qMgr.disconnect();
		   return "Queue Deplth:" + mqQueue + " : " +depth;
		} catch (Exception err) {
		   err.printStackTrace();
		}
		return "ERROR------------------------";

	}
}
