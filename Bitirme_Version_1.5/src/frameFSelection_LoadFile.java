
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

public class frameFSelection_LoadFile {
	public File getFile()
	{
		//BUTTON_1
		try {
			JFileChooser file_chooser = new JFileChooser();
			file_chooser.setCurrentDirectory(new java.io.File("C:\\"));
			file_chooser.setDialogTitle("Choose Any Arff File");
			file_chooser.setAcceptAllFileFilterUsed(false);
			FileFilter filter = new FileNameExtensionFilter("Arff File (.arff)","arff");
			file_chooser.addChoosableFileFilter(filter);
		
			
			
			if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				File file = file_chooser.getSelectedFile();
				//file chooser da seçilen dosyayý yeni bir dosya olarak atadýk
				return file;
			}
			else 
				return null;
		} catch (Exception e) {
			return null;
		}
	}
}
