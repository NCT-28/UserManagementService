package vn.com.nhomtruyen.service.dto;

import java.io.Serializable;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * 
 * @author ToanNC7
 *
 */
public class RoleCriteria implements Serializable, Criteria {
	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter symbol;

	private BigDecimalFilter price;

	private ZonedDateTimeFilter lastTrade;

	public RoleCriteria() {
	}

	public RoleCriteria(RoleCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.symbol = other.symbol == null ? null : other.symbol.copy();
		this.price = other.price == null ? null : other.price.copy();
		this.lastTrade = other.lastTrade == null ? null : other.lastTrade.copy();
	}

	@Override
	public RoleCriteria copy() {
		return new RoleCriteria(this);
	}

	public LongFilter getId() {
		return id;
	}

	public void setId(LongFilter id) {
		this.id = id;
	}

	public StringFilter getSymbol() {
		return symbol;
	}

	public void setSymbol(StringFilter symbol) {
		this.symbol = symbol;
	}

	public BigDecimalFilter getPrice() {
		return price;
	}

	public void setPrice(BigDecimalFilter price) {
		this.price = price;
	}

	public ZonedDateTimeFilter getLastTrade() {
		return lastTrade;
	}

	public void setLastTrade(ZonedDateTimeFilter lastTrade) {
		this.lastTrade = lastTrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastTrade == null) ? 0 : lastTrade.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleCriteria other = (RoleCriteria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastTrade == null) {
			if (other.lastTrade != null)
				return false;
		} else if (!lastTrade.equals(other.lastTrade))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoleCriteria [id=" + id + ", symbol=" + symbol + ", price=" + price + ", lastTrade=" + lastTrade + "]";
	}

}
