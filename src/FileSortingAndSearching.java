import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSortingAndSearching {
	static String[] dictionary = new String[16000];
	static String[] keywords = new String[84];
	public static void main(String[] args) throws IOException {
		//input files
		File $dictionary =  new File("Files\\HW2-dictionary.txt");
		BufferedReader $bf_dictionary;
		$bf_dictionary = new BufferedReader(new FileReader($dictionary));
		
		//input files
		File $keywords =  new File("Files\\HW2-keywords.txt");
		BufferedReader $bf_keywords;
		$bf_keywords = new BufferedReader(new FileReader($keywords));
		
		//output file
		File $outFile = new File("Files\\HW2-out-16103001.txt");
		BufferedWriter $OutputFile = new BufferedWriter(new FileWriter($outFile));  
		
		//reading files dictionary
		String dict;
		int i=0;
		while ((dict = $bf_dictionary.readLine()) != null && i<16000){
			   dictionary[i++]= dict; 
		}
		
		//reading file keywords
		String key;
		i=0;
		while ((key = $bf_keywords.readLine()) != null && i<84){
			   keywords[i++]= key; 
		}
		
		//sorting the arrays
		Sort(dictionary, 16000);
		Sort(keywords, 84);
		
		int c =0;//Number of words non occurring
		
		//Appending to file
		for(i=0;i<84;i++)
		{
			if(!Binary_Search(dictionary, keywords[i]))
			{
				if(!$outFile.exists())
					$outFile.createNewFile();
				
				$OutputFile.append("keyword not found: " + keywords[i] + "\n");
				$OutputFile.newLine();
				c++;
			}
		}
		
		System.out.println("Number of Words not Found: " + c);
		
		//Closing the file
		$bf_keywords.close();
		$bf_dictionary.close();
		$OutputFile.close();
	}	
	
	//search the data in array using binary search
	private static boolean Binary_Search(String[] dictionary, String string) {
		int low =0, high = 15999;
		while(low<=high)
		{
			int mid = low + (high-low)/2;
			if((dictionary[mid].compareTo(string))==0)
				return true;
			else if((dictionary[mid].compareTo(string))>0)
				high = mid-1;
			else
				low = mid+1;
		}	
		return false;
	}
	
	//method to sort the data using bubble sort
	public static void Sort(String[] s, int n)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if((s[i].compareTo(s[j]))>0)
				{
					String temp = s[i];
					s[i] = s[j];
					s[j] = temp;
				}
			}
		}
	}
}
