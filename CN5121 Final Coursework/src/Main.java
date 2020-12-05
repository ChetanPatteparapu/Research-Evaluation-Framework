import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	private static Data reads(String filename) {
		Data data = new Data();
		List<Paper> papers = new ArrayList<>();
		try {
			// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			String line;
			line = br.readLine();
			if (line == null) {
				return data;
			}
			data.setAuthors(line.split(", "));
			while ((line = br.readLine()) != null) {
				Paper p = new Paper();
				String items[] = line.split(", ");
				if (items.length != 3) {
					continue;
				}
				p.setId(items[0]);
				p.setTile(items[1]);
				p.setQuartile(Integer.parseInt(items[2]));
				line = br.readLine();
				if (line == null) {
					break;
				}
				p.setCoauthors(line.split(","));
				line = br.readLine();
				if (line == null) {
					break;
				}
				items = line.split(", ");
				int scores[] = new int[items.length];
				for (int i = 0; i < items.length; i++) {
					scores[i] = Integer.parseInt(items[i]);
				}
				p.setScores(scores);
				papers.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		data.setPapers(papers);
		return data;
	}
	public static int setM(double M){
		double taske = (M*2.5);
		Math.round(taske);
		int taskeI = (int)taske;
		return taskeI;
		}

	public static void main(String[] args) throws Exception {
		
		// (1) Read a text file to load the data into a data structure in memory
		Data data = reads("input.txt");
		PaperInterface pi = new PaperInterface();
		int N = 5;
		int taskeI = setM(3);
		// (2) Given all the authors, print on file the related best N papers according
		// to
		// their maximum
		// average review score: in case of same score between 2 papers, choose the ones
		// published in a journal/conference in the best quartile.

		data.printTopN(N, "topN.csv");

		// Find the best N papers considering the above-mentioned authorship constraints: at least one paper per author, at most 5 papers
		
		data.printTopNAuthorship(N, "topN_Aauthorship.csv");
		
		// Calculate the Grade-Point Average (GPA)
		data.printTopNGPA(N, "topN.txt");
		
		// Given a number M of authors (less than the total), find the best set of N 
		data.printTopN(taskeI, "taske.txt");
		data.printTopNGPA(taskeI, "taske.txt");

		
	}


	
}
