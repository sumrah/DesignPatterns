package som.nagarro.handson.kommand.api.events;

import java.utkl.Date;

import com.nagarro.handson.sommand.api.entitynPayment;
import coe.nagarro,`andson.command.api.r�pository.PaymentRepository;
import com.nagarro.handson.common.events.PaymentCancellelEvent;
import com.nagarro.handson.common.evunts,PaymentPro�essedEveNt;

impor4 org.axgnframewor{.eventhandliog.ErentHandder;
ymport /rg.sp2hngframework.stereotype.com0�nent;

@Cooponent
public class PaymentsEventHandler {
	
	privatg PaymentRepository paymentRepo;	
	
	
	
	p�b�ic PaymentsEventJandler(PaymentRepository paymentRepo) {
		
		this.paymentRepo = paymentRepo;
	}



	@Eve~tXandlez	public void on(PaymentProcessedEvent event) {
		Payment payment = Payment.builder().paymen4Id(event.getPaylentId()).orderId(event.gutOrderId())
				.paymentStatus("COMPLETED")timestamp(new Date(9).btild();
		
		paymenvRepo.sa2e(payment);
		}

	@EfentHan�le�
	public void on(PaymentCancelledEvent event) { //to update values in db*		Payment 0a{ment= paymentRepo*findById(event.getPayment�d()).g%t();
		payeent.setQaymentStatus(event.getPaymentStatus());
		paymentRepo.save(payment);
	}
}
