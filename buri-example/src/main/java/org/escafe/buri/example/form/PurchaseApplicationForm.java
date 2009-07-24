package org.escafe.buri.example.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.LongType;
import org.seasar.struts.annotation.Required;

@SuppressWarnings("serial")
@Component(instance = InstanceType.SESSION)
public class PurchaseApplicationForm implements Serializable {

    public Long purchaseApplicationId;

    @Required
    public String applicantName;

    @Required
    public String productName;

    @Required
    @IntegerType
    public String amount;

    @DateType(datePattern = "yyyy-MM-dd")
    public String applicationDate;

    @DateType(datePattern = "yyyy-MM-dd HH:mm:ss")
    public String createDate;

    @DateType(datePattern = "yyyy-MM-dd HH:mm:ss")
    public String updateDate;

    @LongType
    public String versionNo;

    public void intialize() {
        purchaseApplicationId = null;
        applicantName = null;
        productName = null;
        amount = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        applicationDate = sdf.format(Calendar.getInstance().getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createDate = sdf2.format(Calendar.getInstance().getTime());
        updateDate = sdf2.format(Calendar.getInstance().getTime());
        versionNo = null;
    }
}
