import weka.core.Instances;
import weka.attributeSelection.*;
import weka.core.converters.ArffSaver;
import weka.filters.*;

import java.io.File;

public class frameFSelection_InfoGainFeatureSelection {
	public void Info_FS_Run(Instances data,String File_Path,int ranker_number,String DataSet_Name) throws Exception
	{
			weka.filters.supervised.attribute.AttributeSelection filter1 = new weka.filters.supervised.attribute.AttributeSelection();
			InfoGainAttributeEval evaluater=new InfoGainAttributeEval();
			Ranker ranker = new Ranker();
			ranker.setNumToSelect(ranker_number);
			filter1.setInputFormat(data);
			filter1.setEvaluator(evaluater);
			filter1.setSearch(ranker);

			Instances newData_2 = Filter.useFilter(data, filter1);
			
			ArffSaver saver = new ArffSaver();
			saver.setInstances(newData_2);
			saver.setFile(new File(File_Path+"\\"+"InfoGain_FS_"+DataSet_Name+"_to_"+ranker_number+"_attr.arff"));
			saver.writeBatch();
	}
}
