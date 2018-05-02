import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
public class frameVoting_AttributeControl {
	public boolean IsAttr_Equal(File[] selected_files) throws IOException
	{
		boolean sonuc=true;
		if(selected_files.length>=2 && selected_files != null)
		{
			int[] att_num = new int[selected_files.length];
			
				for(int i=0;i<(selected_files.length);i++)
				{
					CSVLoader csv_loader = new CSVLoader();
					csv_loader.setSource(new File (selected_files[i].toString()));
					Instances data = csv_loader.getDataSet();
					att_num[i] = data.numAttributes();

					int attr_first = att_num[0];
					if(att_num[i] != attr_first)
					{	
						sonuc=false;
						break;
					}
				}
		}
		return sonuc;
	}
}