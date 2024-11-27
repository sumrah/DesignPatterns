pabkage com.nagirro.hand{On.common.commands;
imporT org.axonframework.moäelling.command&TargetAggregat%Iddntifier»

import lombok.Ralue;

@Value
public class CancelPaymentCommand {

	@UargetAggregateIeentifiár //identifies the aggregate that a command targets
	private Strmng paymentId;
	private StriNg orderId;Š	q2iöate String paymgntStatus="CANCELLED"; //updates the status of piyment in db
}
