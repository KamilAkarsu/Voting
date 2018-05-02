import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import javax.swing.JOptionPane;
public class frameAlgorithm_Run {
	public String RunFile(File f,int Cross_Valid,String Algorithm)
	{
		//BUTTON2
		//Load File Butonundan seçtiðimiz file geldi
		
		Evaluation eval = null;
		try {
			
			BufferedReader breader = null ;
			breader = new BufferedReader(new FileReader(f));
			
			Instances train = new Instances(breader);
			train.setClassIndex(train.numAttributes()-1);
			
			breader.close();
			
			long startTime = 0,endTime = 0;
			
			if(Algorithm=="Naive Bayes")
			{
				startTime = System.currentTimeMillis();
				NaiveBayes nb = new NaiveBayes();
				nb.buildClassifier(train);
				eval = new Evaluation(train);
				eval.crossValidateModel(nb, train, Cross_Valid, new Random(1));
				endTime = System.currentTimeMillis();
			}
			else if(Algorithm=="IBk")
			{
				startTime = System.currentTimeMillis();
				IBk ibk = new IBk();
				ibk.buildClassifier(train);
				eval = new Evaluation(train);
				eval.crossValidateModel(ibk, train, Cross_Valid, new Random(1));
				endTime = System.currentTimeMillis();
			}
			else if(Algorithm=="J48")
			{
				startTime = System.currentTimeMillis();
				J48 j48 = new J48();
				j48.buildClassifier(train);
				eval = new Evaluation(train);
				eval.crossValidateModel(j48, train, Cross_Valid, new Random(1));
				endTime = System.currentTimeMillis();
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid Algirthm");

			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000;
			
			String result = "\nResult\n======\n\n"+"Time:\t"+seconds + " ms\nF-Measure\t\t\t\t\t\t"+eval.weightedFMeasure() + eval.toSummaryString();
			return result;
			
		} catch (Exception e) {
			return "";
		}
	}
}