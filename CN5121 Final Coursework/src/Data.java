import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Data {
	private List<String> authors;
	private List<Paper> papers;

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = new ArrayList<>();
		if (authors == null) {
			return;
		}
		for (int i = 0; i < authors.length; i++) {
			this.authors.add(authors[i]);
		}
	}

	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public void printTopN(int n, String filename) throws Exception {
		if (papers == null) {
			return;
		}
		Collections.sort(papers);
		Collections.reverse(papers);
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		for (int i = 0; i < n && i < papers.size(); i++) {
			Paper p = papers.get(i);
			writer.write(String.format("%s, %s, %5.2f\n", p.getId(), p.getTile(), p.gpa()));
		}
		writer.close();
	}

	public void printTopNAuthorship(int n, String filename) throws Exception {
		if (papers == null) {
			return;
		}
		List<Paper> authorhip = new ArrayList<>();
		for (int i = 0; i < authors.size(); i++) {
			List<Paper> authorPapers = getPaperByAuthor(authors.get(i));
			if (authorPapers.size() <= 0) {
				continue;
			}
			authorhip.addAll(authorPapers.subList(0, authorPapers.size() > 5 ? 5 : authorPapers.size()));
		}
		Collections.sort(authorhip);
		Collections.reverse(authorhip);
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		for (int i = 0; i < n && i < authorhip.size(); i++) {
			Paper p = authorhip.get(i);
			writer.write(String.format("%s, %s, %5.2f\n", p.getId(), p.getTile(), p.gpa()));
		}
		writer.close();
	}

	public void printTopNGPA(int n, String filename) throws Exception {
		if (papers == null) {
			return;
		}
		Collections.sort(papers, new Comparator<Paper>() {
			@Override
			public int compare(Paper o1, Paper o2) {
				return Double.compare(o1.gpa(), o2.gpa());
			}
		});
		Collections.reverse(papers);
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		double gpa = 0;
		for (int i = 0; i < n && i < papers.size(); i++) {
			Paper p = papers.get(i);
			gpa += p.gpa();
		}
		gpa = gpa / (n > papers.size() ? papers.size() : n);
		writer.write(String.format("GPA = %5.2f\n", gpa));
		for (int i = 0; i < n && i < papers.size(); i++) {
			Paper p = papers.get(i);
			writer.write(String.format("%s, %s, %5.2f\n", p.getId(), p.getTile(), p.gpa()));
		}
		writer.close();
	}

	public List<Paper> getPaperByAuthor(String author) {
		List<Paper> result = new ArrayList<>();
		if (papers == null) {
			return result;
		}
		for (Paper paper : papers) {
			if (paper.getCoauthors().contains(author)) {
				result.add(paper);
			}
		}
		return result;
	}
}
