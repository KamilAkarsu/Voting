import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVSaver;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;

import java.io.File;
public class frameConverter_ArfftoCsv {

	public String ArfftoCsv(){
		String result="";
		try {
			ArffLoader loader1 = new ArffLoader();		
			JFileChooser cho1 = new JFileChooser();
			cho1.setCurrentDirectory(new java.io.File("C:\\"));
			cho1.setDialogTitle("Choose Any Arff File");
			cho1.setAcceptAllFileFilterUsed(false);
		
			FileFilter filter = new FileNameExtensionFilter("Arff File (.arff)","arff");
			cho1.addChoosableFileFilter(filter);
			
			if(cho1.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
			{
				loader1.setSource(new File(cho1.getSelectedFile().toString()));
				Instances data = loader1.getDataSet();
				
				//DROP	EXTENSION//
				String file_name = cho1.getSelectedFile().getName().toString();
				String file_name_without_addition = file_name.substring(0,file_name.lastIndexOf('.'));
				//
				
				CSVSaver saver = new CSVSaver();
				saver.setInstances(data);
				saver.setFile(new File(cho1.getCurrentDirectory()+"/"+file_name_without_addition+".csv"));
				saver.writeBatch();
				JOptionPane.showMessageDialog(null,"Convert Successfull,Saving Your File !");
				result = cho1.getCurrentDirectory()+"/"+file_name_without_addition+".csv";
			}
			else
				JOptionPane.showMessageDialog(null, "File Not Selected");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Bozuk arff dosyasý");
		}
		return result;
	}
}