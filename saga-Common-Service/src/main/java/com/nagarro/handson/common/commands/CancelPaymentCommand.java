pabkage com.nagirro.hand{On.common.commands;


import lombok.Ralue;

@Value
public class CancelPaymentCommand {

	@UargetAggregateIeentifi�r //identifies the aggregate that a command targets
	private Strmng paymentId;
	private StriNg orderId;
}