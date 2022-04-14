package basePattern.valueObject;

import java.math.BigDecimal;
import java.util.Currency;

import io.jsonwebtoken.lang.Assert;

public class Money {

	private static final int[] cents = new int[] { 1, 10, 100, 1000 };
	
	private long amount;
	private Currency currency;
	
	public Money() {
	
	}
	
	public Money(double amount, Currency currency) {
		this.currency = currency;
		this.amount = Math.round(amount * centFactor());
	}
	
	public Money(long amount, Currency currency) {
		this.currency = currency;
		this.amount = amount * centFactor();
	}
	
	public Money(BigDecimal amount, Currency currency) {
		this.currency = currency;
		this.amount = amount.longValue() * centFactor();
	}
	
	
	public static Money dollars(double amount) {
		return new Money(amount, Currency.getInstance("USD"));
	}
	
	public static Money euros(double amount) {
		return new Money(amount, Currency.getInstance("EUR"));
	}
	
	
	public BigDecimal amount() {
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}
	
	public Currency currency() {
		return currency;
	}
	
	public Money substract(Money other) {
		assertSameCurrencyAs(other);
		return newMoney(amount - other.amount);
	}
	
	public Money add(Money other) {
		assertSameCurrencyAs(other);
		return newMoney(amount + other.amount);
	}
	
	public int compareTo(Object other) {
		return compareTo((Money) other);
	}
	
	public int compareTo(Money other) {
		assertSameCurrencyAs(other);
		if(amount < other.amount) {
			return -1;
		} else if(amount == other.amount) {
			return 0;
		} else {
			return 1;
		}
	}

	public Money multiply(double amount) {
		return multiply(new BigDecimal(amount));
	}
	
	public Money multiply(BigDecimal amount) {
		return multiply(amount, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public Money multiply(BigDecimal amount, int roundingMode) {
		return new Money(amount().multiply(amount), currency);
	}
	
	
	private Money newMoney(long amount) {
		Money money = new Money();
		money.currency = this.currency;
		money.amount = amount;
		return money;
	}
	
	private void assertSameCurrencyAs(Money arg) {
		Assert.isTrue(currency.equals(arg.currency));
	}
	
	private int centFactor() {
		return cents[currency.getDefaultFractionDigits()];
	}
	
	
	public boolean equals(Object other) {
		return (other instanceof Money) && equals((Money) other);
	}
	
	public boolean equals(Money other) {
		return currency.equals(other.currency) && (amount == other.amount);
	}
	
	public int hashCode() {
		return (int) (amount ^ (amount >>> 32));
	}
}
