import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;


//import voting_feature_selection.feature;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class frameVotin_RunVoting {
	public String RunVoting(File[] files,int voting_count) throws IOException
	{
		File file = new File(files[0].toString());
		int lineNumber=0;
		String result = null;
		
		if(file.exists())
		{
			FileReader fr = new FileReader(file);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			while(lnr.readLine()!=null)
			{
				lineNumber++;
			}
			lnr.close();
		}
		

		CSVLoader csv_loader = new CSVLoader();
		csv_loader.setSource(new File (files[0].toString()));
		Instances data = csv_loader.getDataSet();
		int attSize  = data.numAttributes()-1;
		String class_attr_name = data.attribute(attSize).name().toString();
		
			
		String old_file_path = files[0].getParent();
			
			
		ArrayList<frameVoting_feature> features = new ArrayList<frameVoting_feature>();//Feature Listesi
		for (int i = 0; i < files.length; i++) {
			String[][] rawData = new String[lineNumber][];
			BufferedReader br = new BufferedReader(new FileReader(files[i]));
				
			String line="";
			for (int j = 0; j < lineNumber; j++) {
				line = br.readLine();
				rawData[j] = line.split(",");
			}
			br.close();
			
			for (int x = 0; x < rawData[0].length; x++) {
				frameVoting_feature f = new frameVoting_feature();
				f.name = rawData[0][x];
				frameVoting_feature getFeature = getFeature(f,features);
				
				if(getFeature != null)
					getFeature.count++;
				else
				{
					f.count=1;
					for (int k = 1; k < rawData.length; k++) { //feature ait verileri feature'un data dizisine aktar.
						f.data.add(rawData[k][x]);
					}
					features.add(f); // feature'u listeye ekle.
				}
			}
		}	
			
			

		setClassAttr(class_attr_name,features); // burada 'bugs' isimli feature'u features listesinde bulup listenin en sonuna gödermekte.
			
			
		String namesLine = ""; 
			for (frameVoting_feature f : features) { 
				if(f != null && f.count >= voting_count) 
				{
							namesLine += f.name + ","; 
				}
			}	

			if(namesLine!="")
			{
				namesLine = namesLine.substring(0, namesLine.length()-1); 
				result = old_file_path+"\\Voting_"+voting_count+"in4_AttrCount_"+attSize+".csv";
			    BufferedWriter bw = new BufferedWriter(new FileWriter(result));
				bw.write(namesLine+"\n");
				for (int i = 0; i < lineNumber-1; i++) {
					String dataLine = "";
						for (frameVoting_feature f : features) {
							if(f != null && f.count >= voting_count)
							{
								dataLine += f.data.get(i) + ",";
							}
						}
				dataLine = dataLine.substring(0, dataLine.length()-1);
					
				if(i==0)
				bw.write(dataLine);
				else
				bw.write("\n"+dataLine);
				}
				bw.close();
			}
			else
			{
				System.out.println("voting için uygun deðil");
			}
			return result;
	}
	
	public static frameVoting_feature getFeature(frameVoting_feature f , ArrayList<frameVoting_feature> features)
	{
		frameVoting_feature result = null;
		for (frameVoting_feature feature : features) {
			if(feature.name.equals(f.name))
			{
				result = feature;
				break;
			}
		}
		return result;
	}
	public static void setClassAttr(String className , ArrayList<frameVoting_feature> features)
	{
		frameVoting_feature cFeature = null;
		for (frameVoting_feature feature : features) {
			if(feature.name.equals(className))
			{
				cFeature = feature;
			}
		}
		features.remove(cFeature);
		features.add(cFeature);
	}
}
