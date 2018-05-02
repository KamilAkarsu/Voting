import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;

import java.io.File;
public class frameConverter_CsvtoArff {

	public String CsvtoArff(){
		String result = "";
		try {
			CSVLoader loader2 = new CSVLoader();		
			JFileChooser cho2 = new JFileChooser();
			cho2.setCurrentDirectory(new java.io.File("C:\\"));
			cho2.setDialogTitle("Choose Any Csv File");
			cho2.setAcceptAllFileFilterUsed(false);
			
			FileFilter filter = new FileNameExtensionFilter("Csv File (.csv)","csv");
			cho2.addChoosableFileFilter(filter);
			
			if(cho2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				loader2.setSource(new File (cho2.getSelectedFile().toString()));
				Instances data = loader2.getDataSet();
				
				
				//DROP EXTENSION//
				String file_name = cho2.getSelectedFile().getName().toString();
				String file_name_without_addition = file_name.substring(0,file_name.lastIndexOf('.'));
				// . dan sonraki karakterleri sildik
				
				ArffSaver saver2 = new ArffSaver();
				saver2.setInstances(data);
				saver2.setFile(new File(cho2.getCurrentDirectory()+"/"+file_name_without_addition+".arff"));
				saver2.writeBatch();
				JOptionPane.showMessageDialog(null,"Convert Successfull,Saving Your File !");
				result = cho2.getCurrentDirectory()+"/"+file_name_without_addition+".csv";
			}
			else
				JOptionPane.showMessageDialog(null, "File Not Selected");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Bozuk csv dosyasý");
		}
		return result;
	}
}