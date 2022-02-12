package com.xjt.service.impl;

import com.xjt.entity.financial.FinancialData;
import com.xjt.entity.financial.SearchFinancialRequestEntity;
import com.xjt.entity.financial.SearchFinancialResponseEntity;
import com.xjt.entity.order.SearchOrderGood;
import com.xjt.entity.order.SearchOrderRequest;
import com.xjt.entity.order.SearchOrderResponse;
import com.xjt.service.IFinancialService;
import com.xjt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("financialService")
public class FinancialServiceImpl implements IFinancialService {

    private final IOrderService orderService;

    @Autowired
    public FinancialServiceImpl(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public SearchFinancialResponseEntity searchFinancial(SearchFinancialRequestEntity searchFinancialRequestEntity) {
        SearchFinancialResponseEntity result = new SearchFinancialResponseEntity();
        SearchOrderRequest searchOrderRequest = new SearchOrderRequest();
        searchOrderRequest.setStartTime(searchFinancialRequestEntity.getStartTime());
        searchOrderRequest.setEndTime(searchFinancialRequestEntity.getEndTime());
        List<SearchOrderResponse> searchOrderResponses = orderService.search(searchOrderRequest);

        FinancialData financialOfficeEquipment = new FinancialData();
        financialOfficeEquipment.setGoodTypeName("办公设备");
        financialOfficeEquipment.setSaleNum(0);
        financialOfficeEquipment.setSaleMoney(new BigDecimal("0.00"));
        financialOfficeEquipment.setPreferentialAmount(new BigDecimal(0));
        financialOfficeEquipment.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialOfficeConsumables = new FinancialData();
        financialOfficeConsumables.setGoodTypeName("办公耗材");
        financialOfficeConsumables.setSaleNum(0);
        financialOfficeConsumables.setSaleMoney(new BigDecimal("0.00"));
        financialOfficeConsumables.setPreferentialAmount(new BigDecimal(0));
        financialOfficeConsumables.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialOfficePaper = new FinancialData();
        financialOfficePaper.setGoodTypeName("办公纸类");
        financialOfficePaper.setSaleNum(0);
        financialOfficePaper.setSaleMoney(new BigDecimal("0.00"));
        financialOfficePaper.setPreferentialAmount(new BigDecimal(0));
        financialOfficePaper.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialPenClass = new FinancialData();
        financialPenClass.setGoodTypeName("笔类");
        financialPenClass.setSaleNum(0);
        financialPenClass.setSaleMoney(new BigDecimal("0.00"));
        financialPenClass.setPreferentialAmount(new BigDecimal(0));
        financialPenClass.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialNotepadClass = new FinancialData();
        financialNotepadClass.setGoodTypeName("记事本类");
        financialNotepadClass.setSaleNum(0);
        financialNotepadClass.setSaleMoney(new BigDecimal("0.00"));
        financialNotepadClass.setPreferentialAmount(new BigDecimal(0));
        financialNotepadClass.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialBindingEquipment = new FinancialData();
        financialBindingEquipment.setGoodTypeName("装订器材");
        financialBindingEquipment.setSaleNum(0);
        financialBindingEquipment.setSaleMoney(new BigDecimal("0.00"));
        financialBindingEquipment.setPreferentialAmount(new BigDecimal(0));
        financialBindingEquipment.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialFolderClass = new FinancialData();
        financialFolderClass.setGoodTypeName("文件夹类");
        financialFolderClass.setSaleNum(0);
        financialFolderClass.setSaleMoney(new BigDecimal("0.00"));
        financialFolderClass.setPreferentialAmount(new BigDecimal(0));
        financialFolderClass.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialOfficeTools = new FinancialData();
        financialOfficeTools.setGoodTypeName("办公刀具");
        financialOfficeTools.setSaleNum(0);
        financialOfficeTools.setSaleMoney(new BigDecimal("0.00"));
        financialOfficeTools.setPreferentialAmount(new BigDecimal(0));
        financialOfficeTools.setRealSaleMoney(new BigDecimal(0));

        FinancialData financialAccountingSupplies = new FinancialData();
        financialAccountingSupplies.setGoodTypeName("财会用品");
        financialAccountingSupplies.setSaleNum(0);
        financialAccountingSupplies.setSaleMoney(new BigDecimal("0.00"));
        financialAccountingSupplies.setPreferentialAmount(new BigDecimal(0));
        financialAccountingSupplies.setRealSaleMoney(new BigDecimal(0));

        if(searchOrderResponses.size()>0){
            BigDecimal totalMoney = new BigDecimal(0);
            BigDecimal totalPreferentialAmount = new BigDecimal(0);
            for (SearchOrderResponse searchOrderRespons : searchOrderResponses) {
                totalMoney = totalMoney.add(searchOrderRespons.getMoney().add(searchOrderRespons.getPreferentialAmount()));
                totalPreferentialAmount=totalPreferentialAmount.add(searchOrderRespons.getPreferentialAmount());
            }
            BigDecimal devideNum = new BigDecimal(1);
            for (SearchOrderResponse searchOrderRespons : searchOrderResponses) {
                for (SearchOrderGood searchOrderGood : searchOrderRespons.getGoodList()) {
                    if(searchOrderGood.getGoodType()==0){

                        FinancialData officeEquipment = result.getOfficeEquipment();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialOfficeEquipment.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialOfficeEquipment.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialOfficeEquipment.setSaleMoney(ConvertNumber(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2),devideNum,2));
                        result.setOfficeEquipment(financialOfficeEquipment);

                    }else if(searchOrderGood.getGoodType()==1){

                        FinancialData officeEquipment = result.getOfficeConsumables();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialOfficeConsumables.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialOfficeConsumables.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialOfficeConsumables.setSaleMoney(ConvertNumber(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2),devideNum,2));
                        result.setOfficeConsumables(financialOfficeConsumables);

                    }else if(searchOrderGood.getGoodType()==2){

                        FinancialData officeEquipment = result.getOfficePaper();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialOfficePaper.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialOfficePaper.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialOfficePaper.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setOfficePaper(financialOfficePaper);

                    }else if(searchOrderGood.getGoodType()==3){

                        FinancialData officeEquipment = result.getPenClass();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialPenClass.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialPenClass.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialPenClass.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setPenClass(financialPenClass);

                    }else if(searchOrderGood.getGoodType()==4){

                        FinancialData officeEquipment = result.getNotepadClass();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialNotepadClass.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialNotepadClass.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialNotepadClass.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setNotepadClass(financialNotepadClass);

                    }else if(searchOrderGood.getGoodType()==5){

                        FinancialData officeEquipment = result.getBindingEquipment();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialBindingEquipment.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialBindingEquipment.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialBindingEquipment.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setBindingEquipment(financialBindingEquipment);

                    }else if(searchOrderGood.getGoodType()==6){

                        FinancialData officeEquipment = result.getFolderClass();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialFolderClass.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialFolderClass.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialFolderClass.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setFolderClass(financialFolderClass);

                    }else if(searchOrderGood.getGoodType()==7){

                        FinancialData officeEquipment = result.getOfficeTools();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialOfficeTools.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialOfficeTools.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialOfficeTools.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setOfficeTools(financialOfficeTools);

                    }else if(searchOrderGood.getGoodType()==8){

                        FinancialData officeEquipment = result.getAccountingSupplies();
                        //销量
                        Integer saleNum =0;
                        if(officeEquipment==null){
                            saleNum = 0;
                        }else{
                            saleNum = officeEquipment.getSaleNum();
                        }
                        if(searchOrderGood.getGoodNum()!=null){
                            financialAccountingSupplies.setSaleNum(saleNum+searchOrderGood.getGoodNum());
                        }else {
                            financialAccountingSupplies.setSaleNum(saleNum);
                        }
                        //总销售额
                        BigDecimal saleMoney = new BigDecimal(0);
                        if(officeEquipment==null){
                            saleMoney = new BigDecimal(0);
                        }else{
                            saleMoney = officeEquipment.getSaleMoney();
                        }
                        BigDecimal goodNum = new BigDecimal(searchOrderGood.getGoodNum());
                        BigDecimal finalSaleMoney = searchOrderGood.getGoodPrice().multiply(goodNum);
                        financialAccountingSupplies.setSaleMoney(ConvertNumber(finalSaleMoney.add(saleMoney),devideNum,2));
                        result.setAccountingSupplies(financialAccountingSupplies);
                    }
                }
            }

            result.setOfficeEquipment(financialOfficeEquipment);
            result.setOfficeConsumables(financialOfficeConsumables);
            result.setOfficePaper(financialOfficePaper);
            result.setPenClass(financialPenClass);
            result.setNotepadClass(financialNotepadClass);
            result.setBindingEquipment(financialBindingEquipment);
            result.setFolderClass(financialFolderClass);
            result.setOfficeTools(financialOfficeTools);
            result.setAccountingSupplies(financialAccountingSupplies);

            BigDecimal proportionOfficeEquipment = ConvertNumber(financialOfficeEquipment.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountOfficeEquipment = ConvertNumber(totalPreferentialAmount.multiply(proportionOfficeEquipment),new BigDecimal(1),2) ;
            result.getOfficeEquipment().setPreferentialAmount(preferentialAmountOfficeEquipment);
            result.getOfficeEquipment().setRealSaleMoney(financialOfficeEquipment.getSaleMoney().subtract(preferentialAmountOfficeEquipment));

            BigDecimal proportionOfficeConsumables = ConvertNumber(financialOfficeConsumables.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountOfficeConsumables = ConvertNumber(totalPreferentialAmount.multiply(proportionOfficeConsumables),new BigDecimal(1),2) ;
            result.getOfficeConsumables().setPreferentialAmount(preferentialAmountOfficeConsumables);
            result.getOfficeConsumables().setRealSaleMoney(financialOfficeConsumables.getSaleMoney().subtract(preferentialAmountOfficeConsumables));

            BigDecimal proportionOfficePaper = ConvertNumber(financialOfficePaper.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountOfficePaper = ConvertNumber(totalPreferentialAmount.multiply(proportionOfficePaper),new BigDecimal(1),2) ;
            result.getOfficePaper().setPreferentialAmount(preferentialAmountOfficePaper);
            result.getOfficePaper().setRealSaleMoney(financialOfficePaper.getSaleMoney().subtract(preferentialAmountOfficePaper));


            BigDecimal proportionPenClass = ConvertNumber(financialPenClass.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountPenClass = ConvertNumber(totalPreferentialAmount.multiply(proportionPenClass),new BigDecimal(1),2) ;
            result.getPenClass().setPreferentialAmount(preferentialAmountPenClass);
            result.getPenClass().setRealSaleMoney(financialPenClass.getSaleMoney().subtract(preferentialAmountPenClass));

            BigDecimal proportionNotepadClass = ConvertNumber(financialNotepadClass.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountNotepadClass = ConvertNumber(totalPreferentialAmount.multiply(proportionNotepadClass),new BigDecimal(1),2) ;
            result.getNotepadClass().setPreferentialAmount(preferentialAmountNotepadClass);
            result.getNotepadClass().setRealSaleMoney(financialNotepadClass.getSaleMoney().subtract(preferentialAmountNotepadClass));

            BigDecimal proportionBindingEquipment = ConvertNumber(financialBindingEquipment.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountBindingEquipment = ConvertNumber(totalPreferentialAmount.multiply(proportionBindingEquipment),new BigDecimal(1),2) ;
            result.getBindingEquipment().setPreferentialAmount(preferentialAmountBindingEquipment);
            result.getBindingEquipment().setRealSaleMoney(financialBindingEquipment.getSaleMoney().subtract(preferentialAmountBindingEquipment));

            BigDecimal proportionFolderClass = ConvertNumber(financialFolderClass.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountFolderClass = ConvertNumber(totalPreferentialAmount.multiply(proportionFolderClass),new BigDecimal(1),2) ;
            result.getFolderClass().setPreferentialAmount(preferentialAmountFolderClass);
            result.getFolderClass().setRealSaleMoney(financialFolderClass.getSaleMoney().subtract(preferentialAmountFolderClass));

            BigDecimal proportionOfficeTools = ConvertNumber(financialOfficeTools.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountOfficeTools = ConvertNumber(totalPreferentialAmount.multiply(proportionOfficeTools),new BigDecimal(1),2) ;
            result.getOfficeTools().setPreferentialAmount(preferentialAmountOfficeTools);
            result.getOfficeTools().setRealSaleMoney(financialOfficeTools.getSaleMoney().subtract(preferentialAmountOfficeTools));

            BigDecimal proportionAccountingSupplies = ConvertNumber(financialAccountingSupplies.getSaleMoney(),totalMoney,20);
            BigDecimal preferentialAmountAccountingSupplies = ConvertNumber(totalPreferentialAmount.multiply(proportionAccountingSupplies),new BigDecimal(1),2) ;
            result.getAccountingSupplies().setPreferentialAmount(preferentialAmountAccountingSupplies);
            result.getAccountingSupplies().setRealSaleMoney(financialAccountingSupplies.getSaleMoney().subtract(preferentialAmountAccountingSupplies));
        }else{
            result.setOfficeEquipment(financialOfficeEquipment);
            result.setOfficeConsumables(financialOfficeConsumables);
            result.setOfficePaper(financialOfficePaper);
            result.setPenClass(financialPenClass);
            result.setNotepadClass(financialNotepadClass);
            result.setBindingEquipment(financialBindingEquipment);
            result.setFolderClass(financialFolderClass);
            result.setOfficeTools(financialOfficeTools);
            result.setAccountingSupplies(financialAccountingSupplies);
        }
        return result;
    }
 //保留小数
    BigDecimal ConvertNumber(BigDecimal bigDecimal,BigDecimal divnum,int num){
        return bigDecimal.divide(divnum,num,BigDecimal.ROUND_HALF_UP);
    }
}
