package org.perfectglue;

import java.util.List;

import org.perfectglue.config.CamundaTask;
import org.perfectglue.util.DataHandler;

public class Main {

	public static void main(String[] args) {
		List<CamundaTask> list = DataHandler.initCamundaTaskList("");
		for(CamundaTask task : list) {
			System.out.println("\n\nTask: \n" + task.toString());
		}
	}
}
