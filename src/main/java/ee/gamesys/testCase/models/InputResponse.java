package ee.gamesys.testCase.models;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputResponse {
	public static class Input {
        private long trade_id;
        private String type;
        private BigDecimal quantity;
        private BigDecimal price;
        private BigDecimal amount;
        private long date;
        
		public Input() {
		}
		public long getTrade_id() {
			return trade_id;
		}
		public void setTrade_id(long trade_id) {
			this.trade_id = trade_id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public BigDecimal getQuantity() {
			return quantity;
		}
		public void setQuantity(BigDecimal quantity) {
			this.quantity = quantity;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		@Override
		public String toString() {
			return "Input [trade_id=" + trade_id + ", type=" + type + ", quantity=" + quantity + ", price=" + price
					+ ", amount=" + amount + ", date=" + date + "]";
		}
	}
	
	private List<Input> tool;
	
	public InputResponse() {
	}
	
	@JsonProperty("BTC_USD")
	public List<Input> getTool() {
		return tool;
	}
	public void setTool(List<Input> tool) {
		this.tool = tool;
	}
	
}
