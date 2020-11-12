package accounts;

public class CheckingAccount extends Account{
	
	/**
	 * @invar | 0 <= creditLimit
	 * @invar | -creditLimit <= balance
	 */
	private int creditLimit;
	private int balance;
	
	/**
	 * @throws IllegalArgumentException if amount is negative
	 *     | amount < 0
	 * @post | getBalance() == old(getBalance()) + amount
	 */
	@Override
	public void deposit(int amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
		
	}
	@Override
	public int getBalance() {
		return this.balance;
	}
	public int getCreditLimit() {
		return this.creditLimit;
	}
	/**
	 * @throws IllegalArgumentException when amount is negative
	 *     | amount < 0
	 * @post | getBalance() == old(getBalance()) - result
	 * @post | 0 <= result
	 * @post | result <= amount
	 */
	@Override
	public int withdraw(int amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		int result;
		if(amount >= balance+creditLimit) {
			result = balance;
			balance = creditLimit;
		}else {
			balance -= amount;
			result = amount;
		}
		return result;
	}
	public CheckingAccount(int balance) {
		this.balance = balance;
	}
}
