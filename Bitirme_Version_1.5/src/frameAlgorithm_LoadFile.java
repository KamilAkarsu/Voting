import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
public class frameAlgorithm_LoadFile {
	public File getFile()
	{
		try {
			JFileChooser file_chooser = new JFileChooser();
			file_chooser.setCurrentDirectory(new java.io.File("C:\\"));
			file_chooser.setDialogTitle("Choose Any Arff File");
			
			FileFilter filter = new FileNameExtensionFilter("Arff File (.arff)","arff");
			file_chooser.addChoosableFileFilter(filter);
			
		
			file_chooser.setAcceptAllFileFilterUsed(false);
			
			if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				File file = file_chooser.getSelectedFile();
				
				return file;
			}
			else 
				return null;
		} catch (Exception e) {
			return null;
		}
		
	}
}
