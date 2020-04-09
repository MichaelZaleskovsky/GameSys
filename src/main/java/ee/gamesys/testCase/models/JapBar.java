package ee.gamesys.testCase.models;

import java.math.BigDecimal;

public class JapBar {
	private long time;
	private BigDecimal openPrice;
	private BigDecimal closePrice;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private BigDecimal volume;
	
	public JapBar() {
		openPrice = new BigDecimal(0);
		closePrice = new BigDecimal(0);
		minPrice = new BigDecimal(Integer.MAX_VALUE);
		maxPrice = new BigDecimal(0);
		volume = new BigDecimal(0);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "JapBar [time=" + time + ", openPrice=" + openPrice + ", closePrice=" + closePrice + ", minPrice="
				+ minPrice + ", maxPrice=" + maxPrice + ", volume=" + volume + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JapBar other = (JapBar) obj;
		if (closePrice == null) {
			if (other.closePrice != null)
				return false;
		} else if (!closePrice.equals(other.closePrice))
			return false;
		if (maxPrice == null) {
			if (other.maxPrice != null)
				return false;
		} else if (!maxPrice.equals(other.maxPrice))
			return false;
		if (minPrice == null) {
			if (other.minPrice != null)
				return false;
		} else if (!minPrice.equals(other.minPrice))
			return false;
		if (openPrice == null) {
			if (other.openPrice != null)
				return false;
		} else if (!openPrice.equals(other.openPrice))
			return false;
		if (time != other.time)
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
	
}
