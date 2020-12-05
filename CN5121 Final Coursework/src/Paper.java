import java.util.ArrayList;
import java.util.List;

public class Paper implements Comparable<Paper> {
	private List<String> coauthors;
	private String id;
	private String tile;
	private int quartile;
	private int[] scores;

	public List<String> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(String[] coauthors) {
		this.coauthors = new ArrayList<String>();
		for (int i = 0; i < coauthors.length; i++) {
			this.coauthors.add(coauthors[i]);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

	public int getQuartile() {
		return quartile;
	}

	public void setQuartile(int quartile) {
		this.quartile = quartile;
	}

	public double avg() {
		if (scores == null || scores.length <= 0) {
			return 0;
		}
		int total = 0;
		for (int i = 0; i < scores.length; i++) {
			total += scores[i];
		}
		return total * 1.0 / scores.length;
	}

	public double gpa() {
		double score = avg();
		if (score < 1)
			return 0;
		if (score < 4)
			return 1;
		if (score < 7)
			return 2;
		if (score < 10)
			return 3;
		return 4;
	}

	@Override
	public int compareTo(Paper o) {
		if (o.avg() == this.avg()) {
			return -Integer.compare(this.quartile, o.quartile);
		}
		return Double.compare(this.avg(), o.avg());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Paper)) {
			return false;
		}
		Paper p = (Paper) obj;
		return id.equals(p.id);
	}

	@Override
	public int hashCode() {
		return tile.hashCode();
	}

}
