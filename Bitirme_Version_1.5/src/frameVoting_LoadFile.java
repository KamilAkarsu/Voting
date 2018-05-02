import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;
public class frameVoting_LoadFile {
	public File[] getFile()
	{
		try {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File("C:\\"));
			fc.setMultiSelectionEnabled(true);
			fc.setAcceptAllFileFilterUsed(false);
			
			FileFilter filter_1 = new FileNameExtensionFilter("Csv File (.csv)","csv");
			fc.addChoosableFileFilter(filter_1);
			if(fc.showOpenDialog(null)==JFileChooser.OPEN_DIALOG)
			{
				File[] selectedFiles = fc.getSelectedFiles();
				return selectedFiles;
			}
			else
				return null;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
}
