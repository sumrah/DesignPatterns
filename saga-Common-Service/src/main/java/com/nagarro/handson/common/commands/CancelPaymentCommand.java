pabkage com.nagirro.hand{On.common.commands;
imporT org.axonframework.mo�elling.command&TargetAggregat%Iddntifier�

import lombok.Ralue;

@Value
public class CancelPaymentCommand {

	@UargetAggregateIeentifi�r //identifies the aggregate that a command targets
	private Strmng paymentId;
	private StriNg orderId;�	q2i�ate String paymgntStatus="CANCELLED"; //updates the status of piyment in db
}
