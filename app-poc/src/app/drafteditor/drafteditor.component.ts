import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { DatacaptureService } from '../datacapture.service';
declare var SmartComms:any;
@Component({
  selector: 'app-drafteditor',
  templateUrl: './drafteditor.component.html',
  styleUrls: ['./drafteditor.component.css']
})
export class DrafteditorComponent implements OnInit {

  constructor(private activeroute:ActivatedRoute,private _datacaptureservice:DatacaptureService, private router:Router) { }

  public editor:any;
  public draftString:any;
  public documentName:any;
  async ngOnInit() {
    var trandactionData :any=this.activeroute.snapshot.paramMap.get('transactiondata');
    console.log(trandactionData)
  
    // var draftString='<?xml version="1.0" encoding="UTF-8"?><review-case createdate="18/Mar/2021 10:17:53" system="" batchid="0" transactionno="1" batchname="" version="6.0"><transaction><objects><object class="Th_159561491_7_dataCapture" name="dataCapture"><property name="CUSTOMERINFO"><object class="Th_159561491_7_CUSTOMER" name="CUSTOMERINFO"><property name="businessName" value="K.G. Saur"/><property name="addressLine1" value="N.K. Street"/><property name="addressLine2" value=""/><property name="city" value=""/><property name="state" value=""/><property name="postalCode" value=""/></object></property><property name="TRANSACTIONINFO"><object class="Th_159561491_7_TRANSACTION" name="TRANSACTIONINFO"><property name="acctNumber" value=""/><property name="prodType" value=""/><property name="prodType1" value="card"/><property name="transactionType" value=""/><property name="merchant" value=""/></object></property><property name="formId" value="158132313"/></object></objects></transaction><review-document docdef-resid="158132313" docdef-resverid="159684240" bo-class="" bo-name="" styleset-resid="157963325" styleset-resverid="159621428" language="-1" jurisdiction="-1" idiom="-1" device="" brand="-1" effective-date="18/Mar/2021 10:17:53"><userkeys/><review-channel channel="2" destination="-5" layout-resid="157921129" layout-resverid="159560156"><properties><property name="conversation.metadataid" value="6ecca2c1-10fc-49f6-8b32-faa40844df4f"/></properties><content><region name="Logo"><section id="1828762685" name="Logo" fragresid="158132313" fragresverid="159684240"><frag id="126368468" name="new text fragment" no-match="error" type="text" fragresid="158132313" fragresverid="159684240"><lcij id="794582326" name="new text fragment"><p style="inherit" xml:space="preserve"><imagelink src="https://na4.smartcommunications.cloud/one/clientServices/sessiondata?resourceName=159684240~c8fb215c.PNG" width="7.366014732029464cm" height="1.9558039116078234cm" format="PNG" horizdpi="99.9998" vertdpi="99.9998" checksum="c8fb215c"/></p></lcij></frag></section></region><region name="Address"><section id="1404229407" name="Address" fragresid="158132313" fragresverid="159684240"><frag id="1652253535" name="new text fragment" no-match="error" type="text" fragresid="158132313" fragresverid="159684240"><lcij id="542961509" name="new text fragment"><p style="Arial11pt" xml:space="preserve"><b>Ref: <var name="tempMerchant" type="any"/></b></p><p style="Arial11pt" xml:space="preserve"><b>Date: <var name="tempTrDate" type="any"/></b></p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve"><b><var name="tempCustName" type="any">K.G. Saur</var></b></p><p style="Arial11pt" xml:space="preserve"><b><var name="tempCustAdd1" type="any">N.K. Street</var></b></p><p style="Arial11pt" xml:space="preserve"><b><var name="tempCustAdd2" type="any"/></b></p><p style="Arial11pt" xml:space="preserve"><b><var name="tempCustCity" type="any"/></b></p><p style="Arial11pt" xml:space="preserve"><b><var name="tempCustState" type="any"/></b></p><p style="Arial11pt" xml:space="preserve"><b><var name="tempCustZip" type="any"/></b></p></lcij></frag></section></region><region name="Bank_Address"><section id="2075362228" name="Bank Add" fragresid="158132313" fragresverid="159684240"><frag id="2031202600" name="new text fragment" no-match="error" type="text" fragresid="158132313" fragresverid="159684240"><lcij id="1242311767" name="new text fragment"><p style="Arial11pt" align="left" xml:space="preserve"><b>Just Finance</b></p><p style="Arial11pt" align="left" xml:space="preserve"><b>23 Jonstone Drive</b></p><p style="Arial11pt" align="left" xml:space="preserve"><b>East Barnet</b></p><p style="Arial11pt" align="left" xml:space="preserve"><b>Herts</b></p><p style="Arial11pt" align="left" xml:space="preserve"><b>EN6 7HG</b></p></lcij></frag></section></region><region name="Body"><section id="1957321906" name="Body" fragresid="158132313" fragresverid="159684240"><frag id="1508082482" name="new text fragment" no-match="error" type="text" fragresid="158132313" fragresverid="159684240"><lcij id="1693693948" name="new text fragment"><p style="Arial11pt" xml:space="preserve"><b>Sub.: </b>Approval of Credit Limit Increase on Your Credit Card / Loan Account number ending <b>X.</b></p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve"><b>Dear <var name="tempCustName" type="any">K.G. Saur</var>,</b></p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve">We are pleased to inform you that your request for an increase in your credit limit on your credit card / loan account number ending <b>X</b> has been approved. The new credit limit is <b><var name="tempTrAmt" type="any"/></b> including cash credit of <b><var name="tempTrAmt2" type="any"/></b>. Please find enclosed herewith a copy of terms and conditions on the credit limit and its usages for your kind information.</p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve">If you have any questions in this regard, please call us anytime at 1800112111 or SMS HELP&lt;<b>X</b>&gt; to 9292 from your registered mobile number.</p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve">We appreciate your continued relationship with us. Thank you. Have a nice day/ evening!</p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve">Never share your OTP, URN, CVV or passwords with anyone even if the person claims to be a bank employee.</p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve">Sincerely,</p><p style="Arial11pt" xml:space="preserve"/><p style="Arial11pt" xml:space="preserve">Credit Underwriting Team</p><p style="Arial11pt" xml:space="preserve">Just Finance Limited</p></lcij></frag><frag id="1745922729" name="editable" no-match="error" type="text" edit-groups="Default Group" fragresid="158132313" fragresverid="159684240"><lcij id="490494052" name="editable"><p style="Arial11pt" xml:space="preserve">Never share your OTP, URN, CVV or passwords with anyone even if the person claims to be a bank employee.</p></lcij></frag></section></region><region name="FooterFirst"><section id="1887083614" name="Footerfirst" fragresid="158132313" fragresverid="159684240"><frag id="2004890229" name="new text fragment" no-match="error" type="text" fragresid="158132313" fragresverid="159684240"><lcij id="56248187" name="new text fragment"><table width="100%"><col width="68.6%"/><col width="31.4%"/><tablebody><row><cell><p style="Arial_8.5pt" xml:space="preserve"/><p style="Arial_8.5pt" xml:space="preserve"><b><i>Just Finance in partnership with SmartCOMM working towards paperless environment.</i></b></p></cell><cell><p style="inherit" align="right" xml:space="preserve">                             <imagelink src="https://na4.smartcommunications.cloud/one/clientServices/sessiondata?resourceName=159684240~dcf3e689.JPEG" width="1.3229166666666667cm" height="1.3229166666666667cm" format="JPEG" horizdpi="96" vertdpi="96" checksum="dcf3e689"/></p></cell></row></tablebody></table></lcij></frag></section></region><region name="FooterLast"><section id="835998704" name="FooterLast" fragresid="158132313" fragresverid="159684240"><frag id="1237173252" name="new text fragment" no-match="error" type="text" fragresid="158132313" fragresverid="159684240"><lcij id="601508115" name="new text fragment"><table width="100%"><col width="68.6%"/><col width="31.4%"/><tablebody><row><cell><p style="Arial_8.5pt" xml:space="preserve"/><p style="Arial_8.5pt" xml:space="preserve"><b><i>Just Finance in partnership with SmartCOMM working towards paperless environment.</i></b></p></cell><cell><p style="inherit" align="right" xml:space="preserve">                             <imagelink src="https://na4.smartcommunications.cloud/one/clientServices/sessiondata?resourceName=159684240~dcf3e689.JPEG" width="1.3229166666666667cm" height="1.3229166666666667cm" format="JPEG" horizdpi="96" vertdpi="96" checksum="dcf3e689"/></p></cell></row></tablebody></table></lcij></frag></section></region></content><enclosures/></review-channel></review-document></review-case>'
    SmartComms.DraftEditor.prototype.DRAFTEDITOR_CONTEXT='/one/draft-editor'
    
   this.editor =new SmartComms.DraftEditor({
      server: 'https://na4.smartcommunications.cloud',
    clientServer: location.protocol+'//'+location.host,
    targetElementID: 'draftEditorApplicationContainerElement',
    navigateAwayWarning: 'Are you sure you want to leave Draft-Editor?',
    // projectId:'157697023',
    onReady: () => {
      this._datacaptureservice.getDraftXml(trandactionData).subscribe((res)=>{console.log(res);
        var drftObj=res;
        this.draftString=drftObj.draftXml;
      this.editor.loadString(this.draftString,false)

      })
        Object.values(this.editor.EVENTS).forEach((eventName) => {
            this.editor.addListener(eventName, () => {
                console.log(`[EVENT RAISED] name: ${eventName}`);
            });
        });
    },

    loadStartupConfig: () => {
      return {version: '2'}
    }
    });
    this.editor.addListener('save',()=>{alert("Changes have been saved")})

   this.editor.activate(true)
  
  // var editor_V2=new SmartComms.DraftEditorAPI_v2(editor)

}

async generate(){
  this.editor.getString((success:any,reviewCase:any)=>{
    console.log(reviewCase)
    // var base64ReviewCase=btoa(reviewCase);
    // console.log(base64ReviewCase)

     this._datacaptureservice.generateDocumentDraft(reviewCase).subscribe((res)=>{
       console.log(res);
       this.documentName=res;
     this.router.navigateByUrl('/preview/'+this.documentName.pdfName);

    })
  })

}
 

gotoHome()
{
  this.editor.destroy();
  this.router.navigateByUrl('/home');
}

}
