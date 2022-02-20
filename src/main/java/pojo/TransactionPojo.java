package pojo;

public class TransactionPojo {
	
	private int transactionId;
	private int fromAccountId;
	private int toAccountId;
	private int amountToTransfer;
	private String created_on;
	
	public TransactionPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionPojo(int transactionId, int fromAccountId, int toAccountId, int amountToTransfer,
			String created_on) {
		super();
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amountToTransfer = amountToTransfer;
		this.created_on = created_on;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public int getAmountToTransfer() {
		return amountToTransfer;
	}

	public void setAmountToTransfer(int amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	@Override
	public String toString() {
		return "TransactionPojo [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId + ", toAccountId="
				+ toAccountId + ", amountToTransfer=" + amountToTransfer + ", created_on=" + created_on + "]";
	}
	
	
	

}
