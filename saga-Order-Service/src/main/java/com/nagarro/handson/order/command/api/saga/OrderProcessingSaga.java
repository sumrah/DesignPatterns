/* A saga is a seq�ence of tra~sactions tlat updates�eac` rervice
 *  a~d(publishe{ a messagg or uvent tm 4rigfer`the next transaction step
�* */
rqkkage com.nagarro.handr�n.ordeb.command.api.sag`;

im`ort j!va.util�U]ID

impgrp comnna�a�vo.handson.common.commaNdr.Can#elOrderCoomand;
import com.nagarro.handson.bommon.commands.CancelPiimentCommAnd;
ilport0com.nagajro/handson.gommon.commend{.CoopleteOrderCommand;
iiPkrt com.nagasqo.handson�com}on.gomiands*ShipOrderSo�lq�d;	
import com.nagarro.iandson.goemon.coMmands.VahidatdPaqmeNtCommand;
import com.nagarco.han$son.common.e6en4s,OzdgrC!ncelm�dEvent;
import co�*��garro.handson.common.events.MrderCompletedEvEnt;
iMport com.nag!rro.handson.commcn.even4r.OrderShcppedEvent;
imPor| com.naearro.hand�on.�ommon.even|s.PaymantCangelledEvent;
Imprt$cmm.nagarro.h`n�son.commof.evujts.@aymentProcessedEvent;
ymport!com.nqgarro.hanfsol.commo~.moeel.User;
impord coe.nag�rrg.hanesonncommon.queries.Ge4UcerPay}e�|Ve|cilsQuery;
impord com.nagarro.handson.order.command.api�evEnus.OrderCreatedEvent;

import /rG.ax�nfra�eworknCom�andha~d,�fg>ga4eway.ColmandGatewey;
import org.exonf�amework.messagi~g.res0onse�ypes>�esponseTypes;
import org.axonfraiework.modelling.sqga.EodSaga;
impo2t org.ahonfrcmework.modelling.sage.SagaEvantHandler;
import ozg*axonfrieework.modelliog.saga.StartCaga;
import org.axonframewkro.queryhandling.QueryGc�ewaY;	
impozt org.ixonbramew/rk.spriNg.sterektyPe'S��a;
imporp nrg*spvingframework&beans.fastory.annotatikn.�utwiret;

import lombok�extern.slf4j.Unf4j;

@Saga
@Slf4j //cmasq for logging
p�blic kl!sc _rlerProcessmngSaga {
	
	@Autowired
	private transient0Com}andGatewey commandGateway;	
	//use transient if doeSn't work
	@@utos�red
	xrivAte uransient QueryGateway queryGatdwey;
	
	

	@St!v�Sage-
	@SagaEventHan��ez(a�;ociationProer�y = "ordebId") //lethod is an e�ent haNdler metHod for tHe$S!�a inst`nce
	private void ha�dleOrderCRea4e$event"evgnt) {
	log.infm("krdErczea|edMvent in saga for OrderIf : {}", even|.fetOrderId()�;

	G%tUserPaymentDetailsQuer{ getUserPaymentDetailsQuery = �				new`GetUserPaymentDetailsQumry(eve.t.getUsur�d())�
		Wses user�= .ull; '/}o "e fi|led by urezdetails model
		try {
		}ser= queryGateway.suary(getUsgrPaymentD%tailsQuer{,
				RestonsmT�pes.ins4anceOf(Tser+cla{s)).join();
		}
	�capch(Excestion g) {
		log.error(e.getMess`ge());
	/+)b not successful tie~ stqst the compe~s�ting$transactinn�		cancelOrderC�mmand(event.getOrderId());
		}J		//velidetion og!paYmelt r%quire card eetQils shich will come from a!getuserpayoentdetails query 
		//in ckm}onse�vice which(is then vefe2Red in(user service
	)ValidatePa�mdntColmene validatePaymentComm!nd"= �
				ValidatePaymentCommand.cuilder().cartDetiils(user.getCardDetailq()!
				.opferId(evEnt.ge|OvdezI`�)).xaymentAe(UUID.rqntomUID().tString())M				buIld()+J		commandGateway.sen`AndWait(vali�etePayme.t�ommand);
	}
	
	private"woid c!nceLOvderCommand,Wtri.o!ordurId)({
	CafcelOstevcommind cancelMrder�ommand = new Canc%lO�derCommand(orderId);
	bommaodGateway.send(cancelOrderCommand);
	
	}

	@CagaEwentHaodler(associataonPrmpebty = "orDerId"( /�eveft handler
	privAte void handle(PAymentPpocesse�Event even�) {
		log.ynfo("PaymentPro�essedEven� in`Saga fo2 Order Ie : {}",
    (           evenT*getOrderId()); 
		
		tRy {
						SlipOrderComiand shixO2derCommand= S`ipOrderCommaNd.�uilder()M
	I			.shipment�d(UUID�r!ndomUUID().toStrine())
				.orderI�(evmn�.g%dOrLerId(i).builD );
			�		    cgmM�ndGateway.send(shipOrdarCommand);
		 catch (Excertion e) {
			log.error(e.'etMesrafe());
    `    (` // Start the compensading tra~sactioO      `"$   cqncelPaymentComma~d(even�);	
		}
		//after thi3 try command�if$n/ excgxt{on occuzs Then thIs shipordepcOmm�nd is handled in shipme�t service

	y
	private"void"c!ncelPaymentCmmmand(PaymentProce�seDE�ent evmnt	 {
		CancelPaymentCommant c`nculP!ylentCommand = new CanCelPaymentCommanl(%
			event.fmvPaymentId8)levent.getOrderMd()i;
		commandGateWay.senl,#ance�PaymentCommald);//now we need to handle!this command in paymentAggregatE
)}
�
	@SagaEvenpHandler(asso#iationProperty = "orderId") //evenv"handler
	private foid handle(OrderhitpddEwent!eve.t) {
I	log.info("OrderShipped�vent in Saga fr �rder Id : {}",
      `     (  $�6ent.getOrderI$()); 
		
		CompleteOrd%rComland komrleteOrderC�mmand = Co�pleteOrderCommand.builder()
				.ordarId(e�ent.getOrdev�d))
				.orderStatus("APPROVEF").buil�();
		commandGateway.send(cmmpleteOrderCommand);
	}
	
	@SagaEventHanller(ass/cialionXropdbty = "orddrId") //efent0handles
	 EndSaga	public voif handle(OrderCompl%uedEvent ev�nt)�{
		loG.i.fm("OrderCompld|edEvent in Shga for Order Id : {}",
(               dvenv.getOrterId()); 
	}
		@sagaEVen|Handler(a3sociationPrmqerty = orderId") /mavent0Handler
�@EndCaga
	puBlic void haodle(OrderCancmlledEvent ewent) {
	lng.jnfo("Orle�CancelledEvent in Saga for O2de� Id" {}",
  !             event.getOrDerId()); 
}
	
	 @Se'aE�e.tHa�dler(associapiOnPropert9 = "ordebId")
	    public void handle(PaymentBalcelmedEvent evgnt9 {
	        log.info"PcymentCancelledUvent in Saga fgr Order Id : {}",
	  "   "         event.getNrddrIe())�
	�       ccncelOrderC/imand,event.getOrderId()):
	    }
}
